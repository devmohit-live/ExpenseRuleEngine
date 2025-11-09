package com.mohit.lld.expenserulesystem.repository;

import com.mohit.lld.expenserulesystem.model.Rule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class InMemoryRulesRepository implements IRuleRepository {

    private final List<Rule> rules = new ArrayList<>();

    @Override
    public List<Rule> getAllRules() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

}
