package com.capg.hcstm.exception;



public class TestIdDoesNotExistException extends Exception {

	public TestIdDoesNotExistException(String message)
	{
		super(message);
	}
	public TestIdDoesNotExistException()
	{
		super();
	}
}