package com.capg.hcstm.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hcstm.entity.TestManagement;
import com.capg.hcstm.exception.NoTestIsAvailableException;
import com.capg.hcstm.exception.TestIdAlreadyExistsException;
import com.capg.hcstm.exception.TestIdDoesNotExistException;
import com.capg.hcstm.repository.TestRepo;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	TestRepo testRepo;
	
	
	
	
	
	
	
	@Override
	public TestManagement addTest(TestManagement test) throws TestIdAlreadyExistsException {
		if(testRepo.existsById(test.getTestId()))
		{
		throw new TestIdAlreadyExistsException("Test with testId" +test.getTestId()+" alreadyExists");	
		}
		TestManagement  addtest = testRepo.save(test);
	     return addtest;
	}

	@Override
	public boolean deleteTestById(String testId) throws TestIdDoesNotExistException {
       TestManagement deletetest =null;
		if( testRepo.existsById(testId))
		{
			 deletetest = testRepo.findById(testId).get();
			 testRepo.deleteById(testId);
			 return true;
		}
		else
		{
			throw new TestIdDoesNotExistException ("TestId does Not Exists");
		}
		
	}

	@Override
	public List<TestManagement> findAllTests() throws NoTestIsAvailableException {
		List<TestManagement> listOfTests = testRepo.findAll();
			System.out.println(listOfTests);
		if(listOfTests.isEmpty())
		{
			
			throw new NoTestIsAvailableException("No Test Is Present");
		}
		testRepo.findAll();
		return listOfTests;
	}
	

	@Override
	public TestManagement findTestById(String testId) throws TestIdDoesNotExistException {
		if( ! testRepo.existsById(testId))
		{
			throw new TestIdDoesNotExistException("TestId does Not Exists");
		}
		return testRepo.findById(testId).get();
	}


	@Override
	public TestManagement updateTest(TestManagement test) throws TestIdDoesNotExistException {
		String testId =test.getTestId();
		if(testRepo.existsById(testId))
		{
			TestManagement upadateTest=testRepo.findById(testId).get();
			testRepo.saveAndFlush(test);
		}
		else
		{
		throw new TestIdDoesNotExistException("Test with testId" +test.getTestId()+" doesNotExists");
		}
		return test;
	}

	@Override
	public boolean deleteAllTests() throws NoTestIsAvailableException {
		List<TestManagement> listOfTests=testRepo.findAll();
		System.out.println(listOfTests);
		if(listOfTests.isEmpty())
		{
			
			throw new NoTestIsAvailableException("No Test Is Present");
		}
		testRepo.deleteAll();
		return true;
	}


}
