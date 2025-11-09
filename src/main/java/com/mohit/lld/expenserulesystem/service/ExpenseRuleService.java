package com.mohit.lld.expenserulesystem.service;

import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.FailedRulesExpenses;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.repository.IRuleRepository;
import com.mohit.lld.expenserulesystem.startergy.IRuleCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseRuleService {

    @Autowired
    private IRuleRepository ruleRepository;

    @Autowired
    private List<IRuleCriteria> allStrategies;


    public List<FailedRulesExpenses> apply(List<Rule> rules, List<Expense> expenses) {
        List<FailedRulesExpenses> failedRulesExpensesList = new ArrayList<>();
        
        for (Expense expense : expenses) {
            for (Rule rule : rules) {
                for (IRuleCriteria strategy : allStrategies) {
                    if (strategy.isValid(rule.getRuleType()) && strategy.doesViolates(expense, rule)) {
                        failedRulesExpensesList.add(new FailedRulesExpenses(expense, rule));
                        break;
                    }
                }
            }
        }
        return failedRulesExpensesList;
    }
}
