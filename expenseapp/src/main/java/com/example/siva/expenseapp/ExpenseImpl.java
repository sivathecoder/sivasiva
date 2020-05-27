package com.example.siva.expenseapp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ExpenseImpl {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public List<ExpenseDetails> getExpense(String value) {
		 Query query  = new Query();
		 query.addCriteria(new Criteria().orOperator(
	                Criteria.where("amount").is(value),
	                Criteria.where("date").is(value),
	                Criteria.where("category").is(value.toLowerCase()),
	                Criteria.where("description").is(value.toLowerCase())
	           ));
			List<ExpenseDetails> details = mongoTemplate.find(query, ExpenseDetails.class);
			return details;
			
	}
	
	public List<ExpenseDetails> getAllExpense() {
			List<ExpenseDetails> details = mongoTemplate.findAll(ExpenseDetails.class);
			return details;
			
	}

	public ExpenseDetails postExpense(ExpenseDetails expenseDetails) {
		String category = expenseDetails.getCategory().toLowerCase();
		String description = expenseDetails.getDescription().toLowerCase();
		expenseDetails.setCategory(category);
		expenseDetails.setDescription(description);
		mongoTemplate.save(expenseDetails);
		return expenseDetails;
	}

//	public Response updateExpense(ExpenseDetails expenseDetails) {
//		return null;
//	}

	public ExpenseDetails deleteExpense(ExpenseDetails expenseDetails) {
        Query query  = new Query();
        query.addCriteria(new Criteria().andOperator(
                Criteria.where("amount").is(expenseDetails.getAmount()),
                Criteria.where("date").is(expenseDetails.getDate()),
                Criteria.where("category").is(expenseDetails.getCategory().toLowerCase()),
                Criteria.where("description").is(expenseDetails.getDescription().toLowerCase())
           ));
		mongoTemplate.remove(query,"Expense");
		return expenseDetails;
	}

	public List<String> getCategory() {
		List<ExpenseDetails> details = mongoTemplate.findAll(ExpenseDetails.class);
		 List<String>categoryList = details.stream().map(expense -> expense.getCategory()).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
		 return categoryList;
		 
	}

}
