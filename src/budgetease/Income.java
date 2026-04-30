package budgetease;

public class Income extends Transaction {
    public Income(String id, String description, double amount, String date, Category category) {
        super(id, description, amount, date, category);
    }

    @Override
    public String getType() { return "INCOME"; }
    @Override
    public double getBalanceImpact() { 
        return getAmount(); 
    }
}