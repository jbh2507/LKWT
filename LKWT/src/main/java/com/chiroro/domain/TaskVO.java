package com.chiroro.domain;

public class TaskVO extends FileBoxVO{
	
	public TaskVO() {
		super.setTag('T');
	}
	
	@Deprecated
	public void setTag(char tag) {
		
	}
}
