package com.mohit.lld.expenserulesystem.startergy;

import com.mohit.lld.expenserulesystem.model.BlockedExpense;
import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;
import org.springframework.stereotype.Component;

@Component
public class BlockedExpenseRuleCriteria implements IRuleCriteria {
    private static final RuleType RULE_TYPE = RuleType.BLOCKED_EXPENSE;

    @Override
    public boolean isValid(RuleType ruleType) {
       return ruleType == RULE_TYPE;
    }

    @Override
    public boolean doesViolates(Expense expense, Rule rule) {
        BlockedExpense blockedExpense = MAPPER.convertValue(rule.getRuleData(), BlockedExpense.class);
        return expense.getExpenseType() == blockedExpense.getExpenseType();
    }
}
