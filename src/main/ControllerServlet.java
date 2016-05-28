package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	Model rm=new Model();
	HttpSession s=null;
	private static final long serialVersionUID = 1L;
    public ControllerServlet() {
        System.out.println("inside servlet");
    }
	public void init(ServletConfig config) throws ServletException {
		System.out.println("servlet initialised");
	}
	public void destroy() {
		System.out.println("servlet destroyed");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		String uri=request.getRequestURI();
		if(uri.contains("/openLoginView"))
		{
			rd=request.getRequestDispatcher("LoginView.jsp");
			System.out.println("Forwarded to LoginView.jsp");
			rd.forward(request, response);
			
		}
		if(uri.contains("/Login"))
		{
			System.out.println("Inside Login of servlet");
			LogBean lb=(LogBean) request.getAttribute("log");
			System.out.println("log Bean created");
			String result=rm.login(lb);
			System.out.println("Result="+result);
			if(result.equals("Success"))
			{
				System.out.println("Login succeeded");
				request.setAttribute("message", "Login Successful.");
				s= request.getSession(true);
				String birthday="";
				birthday=rm.checkBirthday();
				s.setAttribute("USER", lb);
				s.setAttribute("birthday", birthday);
				
				rd = request.getRequestDispatcher("menu.jsp");
				rd.forward(request, response);
			}
			else
			{
				System.out.println("Login failed");
				request.setAttribute("errorMessage",result);
				rd=request.getRequestDispatcher("LoginView.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/openRegisterView"))
		{
			rd=request.getRequestDispatcher("RegisterView.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/Register"))
		{
			System.out.println("Inside register of servlet");
			RegBean rb=(RegBean) request.getAttribute("reg");
			System.out.println("Bean created");
			String result=rm.register(rb);
			System.out.println("Result="+result);
			if(result.equals("Success"))
			{
				System.out.println("registration is succeeded");
				request.setAttribute("message", "Registration has succeeded! You can now login!!");
				rd = request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
			}
			else
			{
				System.out.println("Registration failed");
				request.setAttribute("errorMessage",result);
				rd=request.getRequestDispatcher("RegisterView.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/Logout"))
		{
			s=request.getSession();
			if(s!=null)
			{
				s.removeAttribute("USER");
				s.invalidate();
				request.setAttribute("message", "Logged out successfully");
				System.out.println("Logged out forwarding to index.jpg ");
				rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else
			{
				s.setAttribute("message", "Already Logged out");
				rd=request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/Home"))
		{
			rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/Menu"))
		{
			rd=request.getRequestDispatcher("menu.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/taskDone"))
		{
			String task=request.getParameter("task");
			String done=request.getParameter("done");
			String res=rm.toggleTask(task,done);
			if(res.equals("Success"))
			{
				s.setAttribute("message", "Updated successfully");
				rd=request.getRequestDispatcher("viewCheckTask.do");
				rd.forward(request, response);
			}
			else
			{
				s.setAttribute("message", "Unsuccessful");
				rd=request.getRequestDispatcher("viewCheckTask.do");
				rd.forward(request, response);
			}
		}

		if(uri.contains("/viewEditAccount"))
		{
			s=request.getSession(false);
			LogBean user=null;
			if(s!=null)
			{
				user=(LogBean)s.getAttribute("USER");
				if(user!=null)
				{
					RegBean found=rm.authenticate(user.getEid());
					if(found!=null)
					{
						System.out.println("Current user id="+found.getEid());
						s.setAttribute("RBUSER", found);
						s.setAttribute("USER",user);
						rd=request.getRequestDispatcher("EditAccount.jsp");
						rd.forward(request, response);
					}
					else
					{
						System.out.println("User not found");
						request.setAttribute("message", "Kindly login");
						rd=request.getRequestDispatcher("Success.jsp");
						rd.forward(request, response);
					}
				}
			}
		}
		if(uri.contains("/EditAccountInt")){
			rd=request.getRequestDispatcher("EditAccountInt.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/updateRegister")){
			s=request.getSession();
			if(s!=null && s.getAttribute("USER")!=null)
			{
				LogBean lb=(LogBean)s.getAttribute("USER");
				String id=lb.getEid();
				RegBean rb=(RegBean)request.getAttribute("updateBean");
				String res=rm.update(rb,id);
				if(res.equals("Success"))
				{
					System.out.println("Update successfull");
					request.setAttribute("message", "Update successfull");
					rd=request.getRequestDispatcher("updateSuccess.jsp");
					rd.forward(request, response);
				}
				else
				{
					System.out.println("Update unsuccessfull");
					request.setAttribute("errorMessage", res);
					rd=request.getRequestDispatcher("EditAccount.jsp");
					rd.forward(request, response);
					
				}
				
				
			}
			else
			{
				System.out.println("Session is not available to this person");
				request.setAttribute("message", "Dear user there is no Session to continue Kindly Login <br>");
				rd=request.getRequestDispatcher("Success.jsp");
				System.out.println("Forwording to Success.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/viewSearch"))
		{
			rd=request.getRequestDispatcher("Search.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/search"))
		{
			HttpSession s=request.getSession();
			if(s!=null && s.getAttribute("USER")!=null)
			{
				LogBean user=(LogBean)s.getAttribute("USER");
				System.out.println("Present user is "+user.getEid());
				String searchBy=request.getParameter("searchParam");
				String value=request.getParameter("param");
				System.out.println("Search parameter:"+searchBy+" value:"+value);
				ArrayList<RegBean> searched=rm.searchList(searchBy,value);
				String res=rm.checkSearch(searched);
				if(res.equals("Success"))
				{
					request.setAttribute("searched", searched);
					System.out.println("Search gave results");
					rd=request.getRequestDispatcher("Search.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("errorMessage", res);
					System.out.println("Search gave no results");
					rd=request.getRequestDispatcher("Search.jsp");
					rd.forward(request, response);
				}
			}
			else
			{
				System.out.println("Session is not avialable to this person");
				request.setAttribute("message", "Dear user there is no Session to continue Kindly Login <br>");
				rd=request.getRequestDispatcher("Success.jsp");
				System.out.println("Forwording to Success.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/viewNewTask"))
		{
			HttpSession s=request.getSession();
			if(s!=null && s.getAttribute("USER")!=null)
			{
				String auth=rm.isManager((LogBean)s.getAttribute("USER"));
				if(auth.equals("Yes"))
				{
					rd=request.getRequestDispatcher("NewTask.jsp");
					System.out.println("Forwarding to NewTask.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("message", "Dear user, you do not yet have authority to assign tasks");
					rd=request.getRequestDispatcher("NewTask.jsp");
					System.out.println("Forwarding to NewTask.jsp");
					rd.forward(request, response);
				}
			}			
			
		}
		if(uri.contains("/allotTask"))
		{
			System.out.println("Inside allottask() of servlet");
			TaskBean tb=(TaskBean) request.getAttribute("task");
			System.out.println("TaskBean created");
			String result=rm.allot(tb);
			System.out.println("Result="+result);
			if(result.equals("Success"))
			{
				System.out.println("Allocation successfull");
				request.setAttribute("message", "Task allocation successfull");
				rd = request.getRequestDispatcher("updateSuccess.jsp");
				rd.forward(request, response);
			}
			else
			{
				System.out.println("Allocation failed");
				request.setAttribute("errorMessage",result);
				rd=request.getRequestDispatcher("NewTask.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/viewCheckTask"))
		{
			System.out.println("inside viewCheckTask()");
			LogBean user=(LogBean)s.getAttribute("USER");
			List<TaskBean> l=rm.getTasks(user.getEid());
			if(l.size()>0 && l!=null)
			{
				s.setAttribute("list",l);
				s.setAttribute("message","Tasks present");
				rd=request.getRequestDispatcher("Taskstatus.jsp");
				rd.forward(request, response);
			}
			else
			{
				s.setAttribute("message","");
				rd=request.getRequestDispatcher("Taskstatus.jsp");
				rd.forward(request, response);
			}
			
		}
		if(uri.contains("/viewAllTasks"))
		{
			s=request.getSession();
			if(s!=null && s.getAttribute("USER")!=null)
			{
				List<TaskBean> l=rm.getAllTasks();
				if(l.size()>0)
				{
					s.setAttribute("tasks", l);
					s.setAttribute("message", "there are tasks");
					rd=request.getRequestDispatcher("ViewTasks.jsp");
					rd.forward(request, response);
				}
				else
				{
					s.setAttribute("message", "");
					rd=request.getRequestDispatcher("ViewTasks.jsp");
					rd.forward(request, response);
				}
				
			}
			else
			{
				System.out.println("Session is not avialable to this person");
				request.setAttribute("message", "Dear user there is no Session to continue Kindly Login <br>");
				rd=request.getRequestDispatcher("Success.jsp");
				System.out.println("Forwording to Success.jsp");
				rd.forward(request, response);
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}

}
