package budgetease;

public class StrictValidator implements TransactionValidator {
    @Override
    public boolean isValid(Transaction transaction) {
        return transaction.getAmount() > 1.0 &&
               transaction.getAmount() <= 1000000 &&
               transaction.getDescription() != null && 
               !transaction.getDescription().trim().isEmpty() &&
               transaction.getDescription().length() >= 3;
    }

    @Override
    public String getValidationRule() {
        return "Amount > $1 & ≤ $1M, Description ≥ 3 chars";
    }
}