package budgetease;

public class DetailedReport extends Report {
    private final double averageTransaction;

    public DetailedReport(double totalIncome, double totalExpense, double balance,
                         double[] categoryTotals, Transaction[] transactions, 
                         int transactionCount, double averageTransaction) {
        super(totalIncome, totalExpense, balance, categoryTotals, transactions, transactionCount);
        this.averageTransaction = averageTransaction;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("""
                ╔══════════════════════════════════════════════════════╗
                ║                      ANALYTICS                        ║
                ╠══════════════════════════════════════════════════════╣
                ║  Average Transaction: $%9.2f   Total Categories Used: %d ║
                ╚══════════════════════════════════════════════════════╝
                """, averageTransaction, countUsedCategories());
    }

    private int countUsedCategories() {
        int count = 0;
        for (double total : categoryTotals) {
            if (total > 0) count++;
        }
        return count;
    }
}