package com.mohit.lld.expenserulesystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Seller {
    @NotNull(message = "Seller type cannot be null")
    private SellerType sellerType;
    
    @NotBlank(message = "Seller name cannot be blank")
    private String sellerName;
}
