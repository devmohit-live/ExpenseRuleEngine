package com.mohit.lld.expenserulesystem.startergy;

import com.mohit.lld.expenserulesystem.model.AmountMaxThreshold;
import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;
import org.springframework.stereotype.Component;

@Component
public class AmountMaxThresholdRuleCriteria implements IRuleCriteria {
    private static final RuleType RULE_TYPE = RuleType.AMOUNT_MAX_THRESHOLD;

    @Override
    public boolean isValid(RuleType ruleType) {
        return ruleType == RULE_TYPE;
    }

    @Override
    public boolean doesViolates(Expense expense, Rule rule) {
        AmountMaxThreshold amountMaxThreshold = MAPPER.convertValue(rule.getRuleData(), AmountMaxThreshold.class);
        return expense.getPrice().isGreaterThan(amountMaxThreshold.getMaxThreshold());
    }
}
