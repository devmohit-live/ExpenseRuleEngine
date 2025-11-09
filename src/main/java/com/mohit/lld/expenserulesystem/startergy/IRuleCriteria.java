package com.mohit.lld.expenserulesystem.startergy;

import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;

public interface IRuleCriteria {
    boolean isValid(RuleType ruleType);
    boolean doesViolates(Expense expense, Rule rule);
}
