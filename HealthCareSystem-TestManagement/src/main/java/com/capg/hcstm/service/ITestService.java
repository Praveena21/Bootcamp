package com.capg.hcstm.service;
import java.util.List;
import com.capg.hcstm.entity.TestManagement;
import com.capg.hcstm.exception.NoTestIsAvailableException;
import com.capg.hcstm.exception.TestIdAlreadyExistsException;
import com.capg.hcstm.exception.TestIdDoesNotExistException;
public interface ITestService {
	
	public TestManagement addTest(TestManagement test)throws TestIdAlreadyExistsException;
	
	public boolean deleteTestById(String testId) throws TestIdDoesNotExistException;
	
	public boolean deleteAllTests()throws NoTestIsAvailableException ;
	
	public List<TestManagement>  findAllTests() throws NoTestIsAvailableException;
	
	public TestManagement findTestById(String testId) throws TestIdDoesNotExistException;
	
	public TestManagement updateTest(TestManagement test)throws TestIdDoesNotExistException;


	
}
