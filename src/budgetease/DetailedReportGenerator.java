package budgetease;

public class DetailedReportGenerator implements ReportGenerator {
    @Override
    public Report generateReport(BudgetManager manager) {
        double[] categoryTotals = new double[Category.values().length];
        double totalIncome = 0, totalExpense = 0;
        int count = manager.getTransactionCount();
        double totalAmount = 0;

        for (int i = 0; i < count; i++) {
            var t = manager.getAllTransactions()[i];
            int catIndex = t.getCategory().ordinal();
            double amt = Math.abs(t.getAmount());
            
            categoryTotals[catIndex] += amt;
            totalAmount += amt;
            
            if ("INCOME".equals(t.getType())) {
                totalIncome += amt;
            } else {
                totalExpense += amt;
            }
        }

        var transCopy = manager.getAllTransactions();
        // Simple detailed report using base Report class
        return new Report(totalIncome, totalExpense, manager.getBalance(), 
                         categoryTotals, transCopy, count);
    }

    @Override
    public String getReportType() { return "Detailed Report"; }
}