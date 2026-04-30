package com.budgetease.validators;

public class BasicValidator implements TransactionValidator {
    @Override
    public boolean isValid(Transaction transaction) {
        return transaction.getAmount() > 0 && 
               transaction.getAmount() <= 1000000 &&
               transaction.getDescription() != null && 
               !transaction.getDescription().trim().isEmpty();
    }

    @Override
    public String getValidationRule() {
        return "Amount > 0 & ≤ $1M, Description required";
    }
}