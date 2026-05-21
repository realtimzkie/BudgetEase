package budgetease;

public class DetailedReportGenerator implements ReportGenerator {
    @Override
    public DetailedReport generateReport(BudgetManager manager) {
        double[] categoryTotals = new double[Category.values().length];
        double totalIncome = 0;
        double totalExpense = 0;
        int transCount = manager.getTransactionCount();
        double totalAmount = 0;

        for (int i = 0; i < transCount; i++) {
            Transaction t = manager.getAllTransactions()[i];
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

        Transaction[] transCopy = manager.getAllTransactions();
        double avgAmount = transCount > 0 ? totalAmount / transCount : 0;
        
        return new DetailedReport(totalIncome, totalExpense, manager.getBalance(), 
                                 categoryTotals, transCopy, transCount, avgAmount);
    }
    @Override
    public String getReportType() { 
        return "Detailed Report"; 
<<<<<<< HEAD
    } 
=======
    }
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
}