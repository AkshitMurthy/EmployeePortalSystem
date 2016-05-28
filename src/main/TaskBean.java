/*
 * @Task.java	@Jun 25, 2015
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

public class TaskBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String eid,task,done;
	public TaskBean() {
		System.out.println("in Task()");
	}
	public void setEid(String eid){
		this.eid=eid;
	}
	public void setTask(String task){
		this.task=task;
	}
	public void setDone(String done){
		this.done=done;
	}
	public String getEid(){return eid;}
	public String getTask(){return task;}
	public String getDone(){return done;}
	
}
