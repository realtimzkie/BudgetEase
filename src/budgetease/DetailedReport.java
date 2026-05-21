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

    // Accessors for GUI/reporting
    public double getTotalIncome() { return totalIncome; }
    public double getTotalExpense() { return totalExpense; }
    public double getBalance() { return balance; }
    public double[] getCategoryTotals() { return categoryTotals.clone(); }
    public int getTransactionCountValue() { return transactionCount; }
    public double getAverageTransaction() { return averageTransaction; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("BudgetEase Report\n");
        sb.append("=================\n\n");

        sb.append(String.format("Total Income : $%10.2f\n", totalIncome));
        sb.append(String.format("Total Expense: $%10.2f\n", totalExpense));
        sb.append(String.format("Balance      : $%10.2f\n", balance));
        sb.append(String.format("Transactions : %d\n\n", transactionCount));

        sb.append("Categories:\n");
        double sum = 0;
        for (double v : categoryTotals) sum += v;
        if (sum <= 0) {
            sb.append("  No transactions yet\n\n");
        } else {
            Category[] categories = Category.values();
            Integer[] idx = new Integer[categories.length];
            for (int i = 0; i < idx.length; i++) idx[i] = i;
            // sort by totals desc
            for (int i = 0; i < idx.length - 1; i++) {
                for (int j = 0; j < idx.length - i - 1; j++) {
                    if (categoryTotals[idx[j]] < categoryTotals[idx[j+1]]) {
                        int t = idx[j]; idx[j] = idx[j+1]; idx[j+1] = t;
                    }
                }
            }
            for (int i : idx) {
                double v = categoryTotals[i];
                if (v <= 0) continue;
                double pct = (sum > 0) ? (v / sum * 100.0) : 0.0;
                sb.append(String.format("  %-15s $%10.2f   (%5.1f%%)\n", categories[i].getDisplayName(), v, pct));
            }
            sb.append('\n');
        }

        sb.append("Analytics:\n");
        sb.append(String.format("  Average transaction : $%10.2f\n", averageTransaction));
        sb.append(String.format("  Categories used      : %d\n", countUsedCategories()));
        sb.append('\n');

        return sb.toString();
    }
}