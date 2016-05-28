/*
 * @RegBean.java	@Jun 19, 2015
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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegBean implements Serializable{
	/**
	 * @serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	String name,lname,eid,phone,address,email,pass,repass,dob,desig;
	public void setName(String name)
	{
		this.name=name;
	}
	public void setLname(String lname)
	{
		this.lname=lname;
	}
	public void setDesig(String d)
	{
		this.desig=d;
	}
	public void setEid(String eid)
	{
		this.eid=eid;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	public void setRepass(String repass)
	{
		this.repass=repass;
	}
	public void setDob(String dob)
	{
		this.dob=dob;
	}
	public String getName(){return name;}
	public String getLname(){return lname;}
	public String getEid(){return eid;}
	public String getPhone(){return phone;}
	public String getAddress(){return address;}
	public String getEmail(){return email;}
	public String getPass(){return pass;}
	public String getRepass(){return repass;}
	public String getDob(){return dob;}
	public String getDesig(){return desig;}
	
	public String validate()
	{
		String msg = "";
		if(name==null || name.trim().equals(""))
			msg="Dear user kindly enter a Proper name & should not be empty or null <br>";
		if(lname==null || lname.trim().equals(""))
			msg+="Dear user kindly enter a Proper last name & should not be empty or null <br>";
		if(eid==null || eid.trim().equals(""))
			msg+="Dear user kindly enter a Proper id & should not be empty or null <br>";
		if(address==null || address.trim().equals(""))
			msg+="Dear user kindly enter a Proper address & should not be empty or null <br>";
		if(pass==null || pass.trim().equals(""))
			msg+="Dear user kindly enter a Proper password & should not be empty or null <br>";
		if(!pass.equals(repass))
			msg+="dear user passwords do not match";
		if(dob==null || dob.trim().equals(""))
			msg+="Dear user kindly enter a Proper dob & should not be empty or null <br>";
		if(desig==null || desig.trim().equals(""))
			msg+="Dear user kindly enter a Proper designation & should not be empty or null <br>";
		else
		{
			if(!isValidDate(dob))
				msg+="Dear user, please enter a valid date of birth in dd/mm/yyyy format";
		}
		if(email==null || email.trim().equals(""))
			msg+=" Dear user email should be not null kindly enter proper email <br>";
		else
		{
			String mail = email;
			String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	        Boolean b = mail.matches(emailreg);
	        if (b == false) 
	        {
	            msg=msg+"Email is Invalid <br>";
	        }
		}
		int flag=0;
		for(int i=0;i<phone.length();i++)
		{
			if(Character.isDigit(phone.charAt(i))==false)
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
			msg+="Dear user please enter a valid phone number";
		if(msg=="")
			return "Success";
		else return msg;
		
	
	}
	public boolean isValidDate(String dateString) {
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	        df.parse(dateString);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}


}
