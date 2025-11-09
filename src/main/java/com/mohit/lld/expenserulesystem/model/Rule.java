package com.mohit.lld.expenserulesystem.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Rule {
   @NotNull(message = "Created date cannot be null")
   private Date createdAt;
   
   @NotNull(message = "Rule ID cannot be null")
   private Integer id;
   
   @NotNull(message = "Rule type cannot be null")
   private RuleType ruleType;
   
   @NotNull(message = "Rule data cannot be null")
   private Object ruleData;
}
