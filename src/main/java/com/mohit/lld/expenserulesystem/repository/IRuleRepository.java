package com.mohit.lld.expenserulesystem.repository;


import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.startergy.IRuleCriteria;

import java.util.List;

public interface IRuleRepository {
    List<Rule>  getAllRules();
    void addRule(Rule rule);
}
