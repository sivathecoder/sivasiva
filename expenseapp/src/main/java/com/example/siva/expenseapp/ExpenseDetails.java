package com.example.siva.expenseapp;



import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Expense" )
public class ExpenseDetails {
    
	@NotNull
	private String amount;
	
	@NotNull
	private String date;
	
	@NotNull
	private String category;
	
	@NotNull
	private String description;
	
	}
