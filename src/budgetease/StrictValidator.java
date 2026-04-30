package budgetease;

public class StrictValidator implements TransactionValidator {
    @Override
    public boolean isValid(Transaction transaction) {
        return transaction.getAmount() >= 10.0 &&
               transaction.getAmount() <= 1000000 &&
               transaction.getDescription() != null && 
               !transaction.getDescription().trim().isEmpty() &&
               transaction.getDescription().length() >= 5;
    }

    @Override
    public String getValidationRule() {
        return "Amount ≥ $10 & ≤ $1M, Description ≥ 5 chars";
    }
}