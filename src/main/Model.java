/*
 * @RegisterModel.java	@Jun 19, 2015
 *
 * Copyright (c) 2012 HappiestMinds Tech Pvt LTD. 
 * All rights reserved. 
 * 
 * No part of this document may be reproduced or transmitted in any form or by 
 * any means, electronic or mechanical, whether now known or later invented, 
 * for any purpose without the prior and express written consent of HappiestMinds 
 * 
 */
package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import main.RegBean;
import main.JDBCHelper;



public class Model {
	public Model()
	{
		System.out.println("Inside constructor of RegisterModel");
		
	}
	@SuppressWarnings("resource")
	public String register(RegBean bean)
	{
		String result=bean.validate();
		System.out.println(result);
		if(result.equals("Success"))
		{
			System.out.println("Writing to db");
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
					return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					ps_sel = con.prepareStatement("select * from employee where Employee_id = ?");
					ps_sel.setString(1, bean.getEid());
					ps_sel.execute();
					rs = ps_sel.getResultSet();
				}
				
				if(rs.next())
				{
					// if there is at least 1 row
					return "Dear user this id is already registered Kindly give an unique  id";
				}
				else
					{
						ps_sel = con.prepareStatement("select * from employee where Employee_email = ?");
						ps_sel.setString(1, bean.getEmail());
						ps_sel.execute();
						rs = ps_sel.getResultSet();
					}
					
					if(rs.next())
					{
						// if there is at least 1 row
						return "Dear user this email is already registered Kindly give an unique email id";
					}
				else
				{
					// no duplicate, id is unique insert
					ps_ins = con.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
					ps_ins.setString(1, bean.getEid());
					ps_ins.setString(2, bean.getName());
					ps_ins.setString(3, bean.getAddress());
					ps_ins.setString(4, bean.getEmail());
					ps_ins.setString(5, bean.getPass());
					ps_ins.setString(6, bean.getPhone());
					ps_ins.setString(7, bean.getDob());
					ps_ins.setString(8, bean.getDesig());
					ps_ins.setString(9, bean.getLname());
					ps_ins.execute();
					
					return "Success";
				}
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}			
		
		}else
		{
			return result;
		}
	
		
		
	}
	public String login(LogBean lb)
	{
		System.out.println("Inside login()");
		String result=lb.validate();
		if(result.equals("Success"))
		{
			System.out.println("Logbean validation sucecceded writing data into the DB");
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			try
			{
				con=JDBCHelper.getConnection();
				if(con==null)
				throw new IllegalArgumentException(" Dear user problem occured in DB Connection Kindly contatct in your admin");
				else
				{
					ps_sel = con.prepareStatement("SELECT * from employee where Employee_id = '"+lb.getEid()+"'");
					ps_sel.execute();
					rs=ps_sel.getResultSet();
					//System.out.println("RS+"+rs);
					String dbEid=null;
					String dbPass=null;
					String dbName=null;
					
					while(rs.next())
					{
						 dbEid = rs.getString("Employee_id");
						 dbPass = rs.getString("Employee_pass");
						 dbName = rs.getString("Employee_name");
						 
						 //System.out.println("Name = "+dbName+"  "+"email = "+dbEmail+"  "+"pass = "+dbPass);				
					}
					/*dbEid = rs.getString("Employee_id");
					 dbPass = rs.getString("Employee_pass");*/
					System.out.println("Id="+dbEid+" pass = "+dbPass);
					if(lb.getEid().equals(dbEid))
					{
						if(lb.getPass().equals(dbPass)){
							lb.setName(dbName);
							
							return "Success";
						}
						
						else
						return "<b>Dear user password is not matching try once again or contact admin <b> <br>";
					}else
					{
						return "<b>Dear user ID id INCORRECT, Kindly enter proper ID <b> <br>";
					}
					
				}		
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return " Dear user problem occured in DB Connection Kindly contatct in your admin [ cause is "+e.getMessage()+" ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}
			
		}else
		{
			return result;
		}		
	}
	public List<RegBean> getUsers() throws IllegalArgumentException
	{
		System.out.println("In getUsers()");
		Connection con=null;
		PreparedStatement ps_sel=null,ps_ins=null;
		ResultSet rs=null;
		try
		{
			con=JDBCHelper.getConnection();

			if(con==null)
			{
				throw new IllegalArgumentException("Error contacting with db");
				
			}
			else
			{
				ps_sel=con.prepareStatement("SELECT * FROM employee");
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				List<RegBean> l=new ArrayList<RegBean>();
				RegBean rb=null;
				while(rs.next())
				{
					rb=new RegBean();
					rb.setAddress(rs.getString("Employee_addr"));
					rb.setName(rs.getString("Employee_name"));
					rb.setLname(rs.getString("l_name"));
					rb.setEid(rs.getString("Employee_id"));
					rb.setEmail(rs.getString("Employee_email"));
					rb.setPass(rs.getString("Employee_pass"));
					rb.setPhone(rs.getString("Employee_phone"));
					rb.setDob(rs.getString("Employee_dob"));
					rb.setDesig(rs.getString("Designation"));
					l.add(rb);
				}
				return l;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Error contacting with db");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_ins);
		}
	}
	public List<RegBean> getUsersWoPass() throws IllegalArgumentException
	{
		System.out.println("In getUsersWoPass()");
		Connection con=null;
		PreparedStatement ps_sel=null,ps_ins=null;
		ResultSet rs=null;
		try
		{
			con=JDBCHelper.getConnection();

			if(con==null)
			{
				throw new IllegalArgumentException("Error contacting with db");
				
			}
			else
			{
				ps_sel=con.prepareStatement("SELECT * FROM employee");
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				List<RegBean> l=new ArrayList<RegBean>();
				RegBean rb=null;
				while(rs.next())
				{
					rb=new RegBean();
					rb.setAddress(rs.getString("Employee_addr"));
					rb.setName(rs.getString("Employee_name"));
					rb.setLname(rs.getString("l_name"));
					rb.setEid(rs.getString("Employee_id"));
					rb.setEmail(rs.getString("Employee_email"));
					//rb.setPass(rs.getString("Employee_pass"));
					rb.setPhone(rs.getString("Employee_phone"));
					rb.setDob(rs.getString("Employee_dob"));
					rb.setDesig(rs.getString("Designation"));
					l.add(rb);
				}
				return l;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Error contacting with db");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_ins);
		}
	}
	public RegBean authenticate(String user) throws IllegalArgumentException
	{
		System.out.println("Inside authenticate()");
		List<RegBean> l=getUsers();
		ArrayList<RegBean> al=(ArrayList<RegBean>) l;
		RegBean found = null;
		for(RegBean rb:al)
		{
			if(rb.getEid().equals(user))
				found=rb;
		}
		return found;
	}
	public String update(RegBean rb,String id)
	{
		System.out.println("inside update() ");
		String res=rb.validate();
		System.out.println(res);
		if(res.equals("Success"))
		{
			System.out.println("Validated,Writing to db");
			
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					
					
					PreparedStatement ps_upd = con.prepareStatement("update employee set Employee_id=?,Employee_name=?,l_name=?,Employee_pass=?,Employee_email=?,Employee_addr=?,Employee_phone=?,Employee_dob=?,Designation=? where Employee_id = '"+id+"'");
					ps_upd.setString(1, rb.getEid());
					ps_upd.setString(2, rb.getName());	
					ps_upd.setString(3, rb.getLname());
					ps_upd.setString(4, rb.getPass());
					ps_upd.setString(5, rb.getEmail());
					//ps_upd.setString(4, rb.getEid());
					ps_upd.setString(6, rb.getAddress());
					ps_upd.setString(7, rb.getPhone());
					ps_upd.setString(8, rb.getDob());
					ps_upd.setString(9, rb.getDesig());
					
					ps_upd.executeUpdate();
					return "Success";
				}			
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}		
		}else
		{
			return res;
		}		
	}
	public ArrayList<RegBean> searchList(String searchBy, String value)
	{
		System.out.println("In searchList() of Register Model");
		List<RegBean> l=getUsersWoPass();
		ArrayList<RegBean> al=(ArrayList<RegBean>)l;
		ArrayList<RegBean> SearchedList=new ArrayList<RegBean>();
		if(searchBy.equals("name"))
		{
			for(RegBean rb:l)
			{
				if(rb.getName().equals(value))
				{
					SearchedList.add(rb);
				}
			}
			
		}
		
		if(searchBy.equals("id"))
		{
			for(RegBean rb:l)
			{
				if(rb.getEid().equals(value))
				{
					SearchedList.add(rb);
				}
			}
			
		}
		
		if(searchBy.equals("phone"))
		{
			for(RegBean rb:l)
			{
				if(rb.getPhone().equals(value))
				{
					SearchedList.add(rb);
				}
			}
			
		}
		
		if(searchBy.equals("email"))
		{
			for(RegBean rb:l)
			{
				if(rb.getEmail().equals(value))
				{
					SearchedList.add(rb);
				}
			}
			
		}
		return SearchedList;
		
	}
	public String checkSearch(ArrayList<RegBean> al)
	{
		if(al.size()>0)return "Success";
		else return "<h3>Oops</h3> <br> <h3> Given value is not matching in any contact</h3>" ;
	}
	public String checkBirthday()
	{
		String ans=new String("No birthdays today. :(");
		Date d = new Date(); 
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(d);
	    int year = cal.get(Calendar.YEAR);
	    int month = (cal.get(Calendar.MONTH)+1);
	    int day =(cal.get(Calendar.DAY_OF_MONTH));
	    System.out.println(day+" "+month+" "+year);
	    List<RegBean> l=getUsersWoPass();
	    ArrayList<RegBean> al=(ArrayList<RegBean>)l;
	    for(RegBean rb:al)
	    {
	    	String dob[]=rb.getDob().split("/");
	    	int date=Integer.parseInt(dob[0]);
	    	int mt=Integer.parseInt(dob[1]);
	    	if(date==day && mt==month)
	    	{
	    		if(ans.equals("No birthdays today. :("))
	    			ans="Today's birthday(s):"+rb.getName();
	    		else
	    			ans+=","+rb.getName();
	    	}
	    	
	    }
	    return ans;
	}
	public String allot(TaskBean tb)
	{
		String msg=taskValid(tb);
		if(msg.equals("Success"))
		{
			System.out.println("Inside allot().Writing to db");
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
					return "DB Connection has been failed Kindly contact your admin ";
				
				else
				{
					
					ps_ins = con.prepareStatement("insert into task(Employee_id,Task,Done) values(?,?,?)");
					ps_ins.setString(1, tb.getEid());
					ps_ins.setString(2, tb.getTask());
					ps_ins.setString(3, tb.getDone());
					ps_ins.execute();
					
					return "Success";
				}
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_ins);
			}
		}
		else return msg;
	}
	public String taskValid(TaskBean tb)
	{
		List<RegBean> l=getUsersWoPass();
		ArrayList<RegBean> al=(ArrayList<RegBean>)l;
		for(RegBean rb:al)
		{
			if(rb.getEid().equals(tb.getEid()))
				return "Success";
		}
		return "The id is invalid";
	}
	public List<TaskBean> getTasks(String id)
	{
		List<TaskBean> l=new ArrayList<TaskBean>();
		System.out.println("Inside getTasks().Searching in db");
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps_sel=null;
		
		try
		{
			con = JDBCHelper.getConnection();
			if(con==null)
				return l;
			
			else
			{
				
				ps_sel = con.prepareStatement("select * from task where Employee_id=?");
				ps_sel.setString(1, id);
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				TaskBean tb=null;
				while(rs.next())
				{
					tb=new TaskBean();
					tb.setTask(rs.getString("Task"));
					tb.setDone(rs.getString("Done"));
					l.add(tb);
				
				}
				return l;
				
			
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return new ArrayList<TaskBean>();
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
		}
	}
	public List<TaskBean> getAllTasks()
	{
		System.out.println("In getAllTasks()");
		Connection con=null;
		PreparedStatement ps_sel=null,ps_ins=null;
		ResultSet rs=null;
		try
		{
			con=JDBCHelper.getConnection();

			if(con==null)
			{
				throw new IllegalArgumentException("Error contacting with db");
				
			}
			else
			{
				ps_sel=con.prepareStatement("SELECT * FROM task order by Done");
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				List<TaskBean> l=new ArrayList<TaskBean>();
				TaskBean rb=null;
				while(rs.next())
				{
					rb=new TaskBean();
					rb.setEid(rs.getString("Employee_id"));
					rb.setTask(rs.getString("Task"));
					rb.setDone(rs.getString("Done"));
					l.add(rb);
				}
				return l;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Error contacting with db");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_ins);
		}
	}
	public String toggleTask(String newtask,String newdone)
	{
		System.out.println("In toggleTask()");
		Connection con=null;
		PreparedStatement ps_sel=null,ps_ins=null;
		ResultSet rs=null;
		try
		{
			con=JDBCHelper.getConnection();

			if(con==null)
			{
				throw new IllegalArgumentException("Error contacting with db");
				
			}
			else
			{
				ps_sel=con.prepareStatement("update task set Done=? where Task=?");
				ps_sel.setString(1, newdone);
				ps_sel.setString(2, newtask);
				ps_sel.execute();
				return "Success";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Error contacting with db");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_ins);
		}
	}
	
	public String isManager(LogBean lb)
	{
		System.out.println("In isManager()");
		String id=lb.getEid();
		Connection con=null;
		PreparedStatement ps_sel=null;
		ResultSet rs=null;
		try
		{
			con=JDBCHelper.getConnection();

			if(con==null)
			{
				throw new IllegalArgumentException("Error contacting with db");
				
			}
			else
			{
				ps_sel=con.prepareStatement("select * from employee where Employee_id=?;");
				ps_sel.setString(1, id);				
				ps_sel.execute();
				rs=ps_sel.getResultSet();				
				if(rs.next())
					if(rs.getString("Designation").equals("Manager"))
					{
						System.out.println("User="+rs.getString(1)+" Desig="+rs.getString("Designation"));
						return "Yes";
					}
					else
						return "No";
				else
					return "No";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Error contacting with db");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			
		}
	}
		
}
	
		


