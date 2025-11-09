package com.mohit.lld.expenserulesystem.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Price {
    @NotNull(message = "Currency cannot be null")
    private Currency currency;
    
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Integer amount;

    public boolean isGreaterThan(Price other) {
        if (this.currency != other.currency) {
            throw new IllegalArgumentException("Cannot compare prices with different currencies");
        }
        return this.amount > other.amount;
    }
    public boolean isLessThan(Price other) {
        return !isGreaterThan(other);
    }
}

