/*
 * @LogBean.java	@Jun 22, 2015
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

public class LogBean implements Serializable{
	/**
	 * @serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	String eid,pass,name,dob;
	public LogBean() {
		// TODO Auto-generated constructor stub
	}
	public String getEid(){return eid;}
	public String getPass(){return pass;}
	public String getName(){return name;}
	public String getDob(){return dob;}
	public void setEid(String eid)
	{
		this.eid=eid;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setDob(String dob)
	{
		this.dob=dob;
	}
	public String validate()
	{
		String msg = "";
		if(eid==null || eid.trim().equals(""))
			msg+="Dear user kindly enter a Proper id & should not be empty or null <br>";
		if(pass==null || pass.trim().equals(""))
			msg+="Dear user kindly enter a Proper password & should not be empty or null <br>";
		if(msg=="")
			return "Success";
		else return msg;
		
	
	}

}
