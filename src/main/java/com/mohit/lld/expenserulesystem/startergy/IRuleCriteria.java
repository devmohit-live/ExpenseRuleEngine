package com.mohit.lld.expenserulesystem.startergy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;

public interface IRuleCriteria {
    ObjectMapper MAPPER = new ObjectMapper();
    
    boolean isValid(RuleType ruleType);
    boolean doesViolates(Expense expense, Rule rule);
}
