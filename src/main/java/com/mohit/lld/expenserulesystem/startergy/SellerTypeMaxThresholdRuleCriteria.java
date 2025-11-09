package com.mohit.lld.expenserulesystem.startergy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.model.RuleType;
import com.mohit.lld.expenserulesystem.model.SellerTypeMaxThreshold;
import org.springframework.stereotype.Component;

@Component
public class SellerTypeMaxThresholdRuleCriteria implements IRuleCriteria{
    private static final RuleType RULE_TYPE = RuleType.SELLER_TYPE_MAX_THRESHOLD;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public boolean isValid(RuleType ruleType) {
        return ruleType == RULE_TYPE;
    }

    @Override
    public boolean doesViolates(Expense expense, Rule rule) {
        SellerTypeMaxThreshold sellerTypeMaxThreshold = objectMapper.convertValue(rule.getRuleData(), SellerTypeMaxThreshold.class);

        boolean isSameSeller = expense.getSeller().getSellerType() == sellerTypeMaxThreshold.getSellerType();
        boolean isExceedingMaxThreshold = expense.getPrice().isGreaterThan(sellerTypeMaxThreshold.getMaxThreshold());
        return isSameSeller && isExceedingMaxThreshold;
    }
}
