package com.mohit.lld.expenserulesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FailedRulesExpenses {
    private Expense expense;
    private Rule rule;
}
