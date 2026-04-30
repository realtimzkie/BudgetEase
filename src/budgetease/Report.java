package budgetease;

public class Report {
    private final double totalIncome;
    private final double totalExpense;
    private final double balance;
    private final double[] categoryTotals;
    private final Transaction[] transactions;
    private final int transactionCount;

    public Report(double totalIncome, double totalExpense, double balance, 
                  double[] categoryTotals, Transaction[] transactions, int transactionCount) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.categoryTotals = categoryTotals.clone();
        this.transactions = transactions.clone();
        this.transactionCount = transactionCount;
    }

    public double getTotalIncome() { return totalIncome; }
    public double getTotalExpense() { return totalExpense; }
    public double getBalance() { return balance; }

    public String getCategoryBreakdown() {
        StringBuilder sb = new StringBuilder();
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            sb.append(String.format("  %-12s $%.2f%n", 
                categories[i].getDisplayName(), categoryTotals[i]));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("""
                === BudgetEase Report ===
                Total Income:  $%.2f
                Total Expense: $%.2f
                Balance:       $%.2f

                Category Breakdown:
                %s
                """, totalIncome, totalExpense, balance, getCategoryBreakdown());
    }
}