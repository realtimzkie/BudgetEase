package budgetease;

public class BasicReportGenerator implements ReportGenerator {
    @Override
    public Report generateReport(BudgetManager manager) {
        double[] categoryTotals = new double[Category.values().length];
        double totalIncome = 0, totalExpense = 0;

        for (int i = 0; i < manager.getTransactionCount(); i++) {
            var t = manager.getAllTransactions()[i];
            int catIndex = t.getCategory().ordinal();
            double amt = Math.abs(t.getAmount());
            
            categoryTotals[catIndex] += amt;
            
            if ("INCOME".equals(t.getType())) {
                totalIncome += amt;
            } else {
                totalExpense += amt;
            }
        }

        var transCopy = manager.getAllTransactions();
        return new Report(totalIncome, totalExpense, manager.getBalance(), 
                         categoryTotals, transCopy, manager.getTransactionCount());
    }

    @Override
    public String getReportType() { return "Basic Report"; }
}