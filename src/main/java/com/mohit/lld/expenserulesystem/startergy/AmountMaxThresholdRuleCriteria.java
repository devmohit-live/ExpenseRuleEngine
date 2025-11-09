package com.mohit.lld.expenserulesystem.startergy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.lld.expenserulesystem.model.AmountMaxThreshold;
import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;
import org.springframework.stereotype.Component;

@Component
public class AmountMaxThresholdRuleCriteria implements IRuleCriteria {
    private static final RuleType RULE_TYPE = RuleType.AMOUNT_MAX_THRESHOLD;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean isValid(RuleType ruleType) {
        return ruleType == RULE_TYPE;
    }

    @Override
    public boolean doesViolates(Expense expense, Rule rule) {
        AmountMaxThreshold amountMaxThreshold = objectMapper.convertValue(rule.getRuleData(), AmountMaxThreshold.class);
        return expense.getPrice().isGreaterThan(amountMaxThreshold.getMaxThreshold());
    }
}
