package com.mohit.lld.expenserulesystem.controller;

import com.mohit.lld.expenserulesystem.model.Expense;
import com.mohit.lld.expenserulesystem.model.FailedRulesExpenses;
import com.mohit.lld.expenserulesystem.model.Rule;
import com.mohit.lld.expenserulesystem.service.ExpenseRuleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Client {

    @Autowired
    private ExpenseRuleService expenseRuleService;

    @GetMapping("/health")
    public String test(){
        return "Hello Expense Rule System";
    }

    @PostMapping("/apply")
    public List<FailedRulesExpenses> applyRulesToExpenses(@RequestBody @Valid ExpenseRuleRequest request) {
        return expenseRuleService.apply(request.getRules(), request.getExpenses());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpenseRuleRequest {
        @Valid
        @NotNull(message = "Rules list cannot be null")
        private List<Rule> rules;
        
        @Valid
        @NotNull(message = "Expenses list cannot be null")
        private List<Expense> expenses;
    }
}
