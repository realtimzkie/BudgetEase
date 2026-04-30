package budgetease;

public class Report {
    protected final double totalIncome;
    protected final double totalExpense;
    protected final double balance;
    protected final double[] categoryTotals;
    protected final int transactionCount;

    public Report(double totalIncome, double totalExpense, double balance, 
                  double[] categoryTotals, Transaction[] transactions, int transactionCount) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.categoryTotals = categoryTotals.clone();
        this.transactionCount = transactionCount;
    }

    protected String getCategoryBreakdown() {
        StringBuilder sb = new StringBuilder();
        Category[] categories = Category.values();
        
        // Sort categories by amount (descending)
        Integer[] indices = new Integer[categories.length];
        for (int i = 0; i < indices.length; i++) indices[i] = i;
        
        // Simple bubble sort by amount
        for (int i = 0; i < indices.length - 1; i++) {
            for (int j = 0; j < indices.length - i - 1; j++) {
                if (categoryTotals[indices[j]] < categoryTotals[indices[j + 1]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }
        
        // Display top categories only (non-zero)
        int displayed = 0;
        for (int idx : indices) {
            if (categoryTotals[idx] > 0 && displayed < 6) {
                sb.append(String.format("  %-12s $%9.2f%n", 
                    categories[idx].getDisplayName(), categoryTotals[idx]));
                displayed++;
            }
        }
        if (displayed == 0) sb.append("  No transactions yet");
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("""
                ╔══════════════════════════════════════════════════════╗
                ║                    BudgetEase Report                  ║
                ╠══════════════════════════════════════════════════════╣
                ║  INCOME:     $%9.2f        EXPENSES:  $%9.2f       ║
                ║  BALANCE:    $%9.2f        TRANSACTIONS: %3d        ║
                ╠══════════════════════════════════════════════════════╣
                ║                       CATEGORIES                      ║
                %s
                ╚══════════════════════════════════════════════════════╝
                """, totalIncome, totalExpense, balance, transactionCount, 
                getCategoryBreakdown());
    }
}