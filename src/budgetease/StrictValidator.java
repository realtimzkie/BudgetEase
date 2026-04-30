package budgetease;

public class StrictValidator implements TransactionValidator {
    @Override
    public boolean isValid(Transaction transaction) {
        return new BasicValidator().isValid(transaction) &&
               transaction.getDescription().length() >= 5 &&
               transaction.getAmount() >= 10.0;
    }

    @Override
    public String getValidationRule() {
        return "Amount ≥ $10 & ≤ $1M, Description ≥ 5 chars";
    }
}