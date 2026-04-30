package budgetease;

public class DetailedReport {
    private final double totalIncome;
    private final double totalExpense;
    private final double balance;
    private final double[] categoryTotals;
    private final int transactionCount;
    private final double averageTransaction;

    public DetailedReport(double totalIncome, double totalExpense, double balance,
                         double[] categoryTotals, Transaction[] transactions, 
                         int transactionCount, double averageTransaction) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.categoryTotals = categoryTotals.clone();
        this.transactionCount = transactionCount;
        this.averageTransaction = averageTransaction;
    }

    public String getCategoryBreakdown() {
        StringBuilder sb = new StringBuilder();
        Category[] categories = Category.values();
        
        Integer[] indices = new Integer[categories.length];
        for (int i = 0; i < indices.length; i++) indices[i] = i;
        
        for (int i = 0; i < indices.length - 1; i++) {
            for (int j = 0; j < indices.length - i - 1; j++) {
                if (categoryTotals[indices[j]] < categoryTotals[indices[j + 1]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }
        
        int displayed = 0;
        for (int idx : indices) {
            if (categoryTotals[idx] > 0 && displayed < 8) {
                sb.append(String.format("  %-12s $%9.2f%n", 
                    categories[idx].getDisplayName(), categoryTotals[idx]));
                displayed++;
            }
        }
        if (displayed == 0) sb.append("  No transactions yet");
        return sb.toString();
    }

    public int countUsedCategories() {
        int count = 0;
        for (double total : categoryTotals) {
            if (total > 0) count++;
        }
        return count;
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
                ╠══════════════════════════════════════════════════════╣
                ║                      ANALYTICS                        ║
                ║  Average Transaction: $%9.2f   Categories Used: %2d ║
                ╚══════════════════════════════════════════════════════╝
                """, totalIncome, totalExpense, balance, transactionCount, 
                getCategoryBreakdown(), averageTransaction, countUsedCategories());
    }
}