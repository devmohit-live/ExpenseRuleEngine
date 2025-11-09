package com.mohit.lld.expenserulesystem.startergy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.lld.expenserulesystem.model.BlockedExpense;
import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;
import org.springframework.stereotype.Component;

@Component
public class BlockedExpenseRuleCriteria implements IRuleCriteria {
    private static final RuleType RULE_TYPE = RuleType.BLOCKED_EXPENSE;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean isValid(RuleType ruleType) {
       return ruleType == RULE_TYPE;
    }

    @Override
    public boolean doesViolates(Expense expense, Rule rule) {
        BlockedExpense blockedExpense = objectMapper.convertValue(rule.getRuleData(), BlockedExpense.class);
        return expense.getExpenseType() == blockedExpense.getExpenseType();
    }


}
