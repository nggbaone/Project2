package com.javaweb.customexception;
// Co the thay RuntimeException cho Exception -> RuntimeException rong hon Exception
public class FieldRequiredException extends RuntimeException{
	
	public FieldRequiredException(String s) {
		super(s);
	}
	
}
