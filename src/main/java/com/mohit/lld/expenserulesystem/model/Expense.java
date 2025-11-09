package com.mohit.lld.expenserulesystem.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Expense {
    @NotBlank(message = "Expense ID cannot be blank")
    private String id;
    
    @NotBlank(message = "Item ID cannot be blank")
    private String itemId;
    
    @NotNull(message = "Expense type cannot be null")
    private ExpenseType expenseType;
    
    @NotNull(message = "Price cannot be null")
    @Valid
    private Price price;
    
    @NotNull(message = "Seller cannot be null")
    @Valid
    private Seller seller;
}
