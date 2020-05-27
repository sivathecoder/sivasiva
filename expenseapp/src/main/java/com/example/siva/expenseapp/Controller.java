package com.example.siva.expenseapp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class Controller {
	
	
	@Autowired
	ExpenseImpl expenseImpl;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@GetMapping("/get/{request}")
	public ResponseEntity<List<ExpenseDetails>> getExpense(@PathVariable String request){
		List<ExpenseDetails> response = expenseImpl.getExpense(request);
		return new ResponseEntity<List<ExpenseDetails>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<ExpenseDetails>> getAllExpense(){
		List<ExpenseDetails> response = expenseImpl.getAllExpense();
		return new ResponseEntity<List<ExpenseDetails>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<String>> getcategory(){
		List<String> categories = expenseImpl.getCategory();
		return new ResponseEntity<List<String>>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ExpenseDetails> postExpense(@Valid @RequestBody ExpenseDetails expenseDetails){
		ExpenseDetails response = expenseImpl.postExpense(expenseDetails);
		return new ResponseEntity<ExpenseDetails>(response, HttpStatus.OK);
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<Response> updateExpense(@Valid @RequestBody ExpenseDetails expenseDetails){
//		Response response = expenseImpl.updateExpense(expenseDetails);
//		return new ResponseEntity<Response>(response, HttpStatus.OK);
//	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ExpenseDetails> deleteExpense(@RequestBody ExpenseDetails expenseDetails){
		ExpenseDetails response = expenseImpl.deleteExpense(expenseDetails);
		return new ResponseEntity<ExpenseDetails>(response, HttpStatus.OK);
	}
	
	
}
