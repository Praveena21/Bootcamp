package com.capg.hcstm.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.hcstm.entity.TestManagement;
import com.capg.hcstm.exception.NoTestIsAvailableException;
import com.capg.hcstm.exception.TestIdAlreadyExistsException;
import com.capg.hcstm.exception.TestIdDoesNotExistException;
import com.capg.hcstm.service.ITestService;
@RestController
@RequestMapping("/test")
public class TestController {
@Autowired
	ITestService  testService;
 @GetMapping("/getAll")
	public ResponseEntity<List<TestManagement>>  findAllTests() throws NoTestIsAvailableException
	{
	  List<TestManagement> list=testService.findAllTests();
		ResponseEntity<List<TestManagement>>  listOfTests = new ResponseEntity<List<TestManagement>>(list,HttpStatus.OK);
		
		System.out.println(list);
		return listOfTests;
		
	}	
	
	@DeleteMapping("/deleteAll")
	public boolean   deleteAllTests() throws NoTestIsAvailableException
	{
	  
		boolean status=testService.deleteAllTests();
		//ResponseEntity<List<TestManagement>>  listOfTests = new ResponseEntity<List<TestManagement>>(status,HttpStatus.OK);
		return status;
		
	}	
	
	@PostMapping("/addTest")
	public ResponseEntity<TestManagement>  addTest(@RequestBody TestManagement test) throws TestIdAlreadyExistsException
	{
		TestManagement addtest=testService.addTest(test);
		ResponseEntity<TestManagement> ResponseEntityadd  = new ResponseEntity<TestManagement>(addtest,HttpStatus.OK);
		return ResponseEntityadd;
	}
	@DeleteMapping("/deleteTest/id/{id}")
	public  boolean deleteTestById(@PathVariable("id") String testId) throws TestIdDoesNotExistException
	{
		
		ResponseEntity<TestManagement>  ResponseEntitydelete = null;
	    boolean test = testService.deleteTestById(testId);
	    ResponseEntitydelete= new ResponseEntity<TestManagement>(HttpStatus.OK);
		return test;
	}
	@GetMapping("/getTest/id/{id}")
	public  ResponseEntity<TestManagement>  findTestById(@PathVariable("id")  String id) throws TestIdDoesNotExistException 
	{
		
		   TestManagement  test = testService.findTestById(id);
		   ResponseEntity<TestManagement>  testbyid= new ResponseEntity<TestManagement>(test,HttpStatus.OK);
		   return testbyid;
	}
	
	
	@PutMapping("/updateTest")
	public ResponseEntity<TestManagement> updateTest(@RequestBody TestManagement test) throws TestIdDoesNotExistException
	{
		ResponseEntity<TestManagement> ResponseEntityupdate =null;
		if(test!=null)
		{
		test=testService.updateTest(test);
		ResponseEntityupdate=new ResponseEntity<TestManagement>(test,HttpStatus.OK);
		}
		else
		{
			ResponseEntityupdate=new ResponseEntity<TestManagement>(test,HttpStatus.NOT_FOUND);
		}
		return ResponseEntityupdate;
	}
}

