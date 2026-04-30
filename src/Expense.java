package src;

public class Expense extends Transaction {
    public Expense(String id, String description, double amount, String date, Category category) {
        super(id, description, amount, date, category);
    }

    @Override
    public String getType() { return "EXPENSE"; }
    @Override
    public double getBalanceImpact() { return -getAmount(); }
}