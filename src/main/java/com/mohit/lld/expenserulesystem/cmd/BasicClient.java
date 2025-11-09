package com.mohit.lld.expenserulesystem.cmd;

import com.mohit.lld.expenserulesystem.model.*;
import com.mohit.lld.expenserulesystem.repository.IRuleRepository;
import com.mohit.lld.expenserulesystem.service.ExpenseRuleService;
import com.mohit.lld.expenserulesystem.startergy.IRuleCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BasicClient demonstrates:
 * 1. All 3 types of rules (BLOCKED_EXPENSE, SELLER_TYPE_MAX_THRESHOLD, AMOUNT_MAX_THRESHOLD)
 * 2. Multiple expenses with different scenarios
 * 3. How Spring's Strategy Pattern works with auto-injection
 * 4. At least one expense that passes all rules
 */

/*
@Component
public class BasicClient implements CommandLineRunner {

    @Autowired
    private IRuleRepository ruleRepository;

    @Autowired
    private ExpenseRuleService expenseRuleService;

    // Spring automatically injects ALL implementations of IRuleCriteria here!
    // This demonstrates the Strategy Pattern with Spring's dependency injection
    @Autowired
    private List<IRuleCriteria> allStrategies;

    @Override
    public void run(String... args) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("EXPENSE RULE SYSTEM - TESTING ALL RULE TYPES");
        System.out.println("=".repeat(80) + "\n");

        // First, let's show how Spring's Strategy Pattern works
        demonstrateStrategyPattern();

        // Setup test data
        List<Rule> rules = createTestRules();
        List<Expense> expenses = createTestExpenses();

        // Add rules to repository
        System.out.println("\n📋 SETTING UP RULES");
        System.out.println("-".repeat(80));
        for (Rule rule : rules) {
            ruleRepository.addRule(rule);
            System.out.println("✓ Added Rule: " + rule.getRuleType());
        }

        // Display expenses
        System.out.println("\n💰 TEST EXPENSES");
        System.out.println("-".repeat(80));
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            System.out.println(String.format("Expense %d: %s | %s | %s %d | Seller: %s (%s)",
                    i + 1,
                    expense.getId(),
                    expense.getExpenseType(),
                    expense.getPrice().getCurrency(),
                    expense.getPrice().getAmount(),
                    expense.getSeller().getSellerName(),
                    expense.getSeller().getSellerType()
            ));
        }

        // Apply rules and show results
        System.out.println("\n🔍 RULE VALIDATION RESULTS");
        System.out.println("-".repeat(80));
        
        List<FailedRulesExpenses> failedExpenses = expenseRuleService.apply(rules, expenses);
        
        if (failedExpenses.isEmpty()) {
            System.out.println("✅ All expenses passed all rules!");
        } else {
            System.out.println("❌ " + failedExpenses.size() + " expense(s) failed validation:\n");
            for (FailedRulesExpenses failed : failedExpenses) {
                System.out.println(String.format("  • Expense: %s (%s, %s %d)",
                        failed.getExpense().getId(),
                        failed.getExpense().getExpenseType(),
                        failed.getExpense().getPrice().getCurrency(),
                        failed.getExpense().getPrice().getAmount()
                ));
                System.out.println(String.format("    Violated Rule: %s", failed.getRule().getRuleType()));
                System.out.println();
            }
        }

        // Show which expenses passed
        System.out.println("✅ EXPENSES THAT PASSED ALL RULES:");
        System.out.println("-".repeat(80));
        List<String> failedExpenseIds = failedExpenses.stream()
                .map(f -> f.getExpense().getId())
                .toList();
        
        for (Expense expense : expenses) {
            if (!failedExpenseIds.contains(expense.getId())) {
                System.out.println(String.format("  ✓ %s: %s | %s %d | %s",
                        expense.getId(),
                        expense.getExpenseType(),
                        expense.getPrice().getCurrency(),
                        expense.getPrice().getAmount(),
                        expense.getSeller().getSellerName()
                ));
            }
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("TEST COMPLETED");
        System.out.println("=".repeat(80) + "\n");
    }


    private void demonstrateStrategyPattern() {
        System.out.println("🎯 STRATEGY PATTERN DEMONSTRATION");
        System.out.println("-".repeat(80));
        System.out.println("Spring automatically discovered and injected " + allStrategies.size() + " strategy implementations:");
        
        for (IRuleCriteria strategy : allStrategies) {
            System.out.println("  • " + strategy.getClass().getSimpleName());
        }
        
        System.out.println("\n📚 HOW IT WORKS (STRATEGY PATTERN WITH SPRING):");
        System.out.println("  1. Each strategy implements IRuleCriteria (@Component)");
        System.out.println("  2. Spring scans and creates ONE instance of each strategy");
        System.out.println("  3. @Autowired List<IRuleCriteria> → Spring injects ALL strategies!");
        System.out.println("  4. Rule objects store ONLY data (no strategy reference!)");
        System.out.println("  5. At runtime, we loop through strategies to find matching one");
        System.out.println("  6. Strategy matches based on strategy.isValid(rule.getRuleType())");
        System.out.println("\n💡 KEY BENEFITS:");
        System.out.println("  ✓ Simple & Clean - no extra registry class needed");
        System.out.println("  ✓ Strategies are reusable (one instance for all rules)");
        System.out.println("  ✓ Rule = pure data, Strategy = pure behavior");
        System.out.println("  ✓ Easy to add new rule types - just add @Component strategy!");
    }


    private List<Rule> createTestRules() {
        List<Rule> rules = new ArrayList<>();

        // Rule 1: BLOCKED_EXPENSE - Block all ENTERTAINMENT expenses
        BlockedExpense blockedExpenseData = new BlockedExpense(ExpenseType.ENTERTAINMENT);
        rules.add(new Rule(
                new Date(),
                1,
                RuleType.BLOCKED_EXPENSE,
                blockedExpenseData  // Just the data - strategy looked up automatically!
        ));

        // Rule 2: AMOUNT_MAX_THRESHOLD - No expense should exceed 5000 INR
        AmountMaxThreshold amountThresholdData = new AmountMaxThreshold(
                new Price(Currency.INR, 5000)
        );
        rules.add(new Rule(
                new Date(),
                2,
                RuleType.AMOUNT_MAX_THRESHOLD,
                amountThresholdData  // Just the data - strategy looked up automatically!
        ));

        // Rule 3: SELLER_TYPE_MAX_THRESHOLD - Restaurant expenses should not exceed 3000 INR
        SellerTypeMaxThreshold sellerTypeThresholdData = new SellerTypeMaxThreshold(
                SellerType.RESTAURANT,
                new Price(Currency.INR, 3000)
        );
        rules.add(new Rule(
                new Date(),
                3,
                RuleType.SELLER_TYPE_MAX_THRESHOLD,
                sellerTypeThresholdData  // Just the data - strategy looked up automatically!
        ));

        return rules;
    }


    private List<Expense> createTestExpenses() {
        List<Expense> expenses = new ArrayList<>();

        // Expense 1: Should violate Rule 3 (Restaurant > 3000 INR)
        expenses.add(new Expense(
                "EXP001",
                "ITEM001",
                ExpenseType.FOOD,
                new Price(Currency.INR, 3500),
                new Seller(SellerType.RESTAURANT, "Fine Dining Restaurant")
        ));

        // Expense 2: Should violate Rule 1 (ENTERTAINMENT is blocked)
        expenses.add(new Expense(
                "EXP002",
                "ITEM002",
                ExpenseType.ENTERTAINMENT,
                new Price(Currency.INR, 2000),
                new Seller(SellerType.HOTEL, "Grand Hotel")
        ));

        // Expense 3: Should violate Rule 2 (Amount > 5000 INR)
        expenses.add(new Expense(
                "EXP003",
                "ITEM003",
                ExpenseType.TRAVEL,
                new Price(Currency.INR, 8000),
                new Seller(SellerType.TRAVEL_AGENCY, "Travel World")
        ));

        // Expense 4: ✅ PASSES ALL RULES - Restaurant under limit
        expenses.add(new Expense(
                "EXP004",
                "ITEM004",
                ExpenseType.FOOD,
                new Price(Currency.INR, 2000),
                new Seller(SellerType.RESTAURANT, "Local Cafe")
        ));

        // Expense 5: ✅ PASSES ALL RULES - Learning expense
        expenses.add(new Expense(
                "EXP005",
                "ITEM005",
                ExpenseType.LEARNING_AND_DEVELOPMENT,
                new Price(Currency.INR, 4000),
                new Seller(SellerType.LEARNING_PLATFORM, "Online Course Platform")
        ));

        return expenses;
    }
}

*/
