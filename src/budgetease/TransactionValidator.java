package budgetease;

public interface TransactionValidator {
    boolean isValid(Transaction transaction);
    String getValidationRule();
}