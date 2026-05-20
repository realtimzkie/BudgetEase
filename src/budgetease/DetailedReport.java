package budgetease;

import java.util.Map;

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
        StringBuilder sb = new StringBuilder();
        
        sb.append("╔══════════════════════════════════════════════════════╗\n");
        sb.append("║                    BudgetEase Report                 ║\n");
        sb.append("╠══════════════════════════════════════════════════════╣\n");
        
        String leftUpper = String.format("INCOME:    $%9.2f", totalIncome);
        String rightUpper = String.format("EXPENSES: $%9.2f", totalExpense);
        sb.append(String.format("║  %-25s   %-24s║\n", leftUpper, rightUpper));
        
        String leftLower = String.format("BALANCE:   $%9.2f", balance);
        String rightLower = String.format("TRANSACTIONS: %3d", transactionCount);
        sb.append(String.format("║  %-25s   %-24s║\n", leftLower, rightLower));
        
        sb.append("╠══════════════════════════════════════════════════════╣\n");
        sb.append("║                       CATEGORIES                     ║\n");
        
        String breakdown = getCategoryBreakdown();
        if (breakdown != null && !breakdown.isEmpty()) {
            String[] lines = breakdown.split("\\r?\\n");
            for (String line : lines) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    if (trimmedLine.contains("$")) {
                        int dollarIdx = trimmedLine.indexOf("$");
                        String catName = trimmedLine.substring(0, dollarIdx).trim();
                        String amountStr = trimmedLine.substring(dollarIdx).trim();
                        String formattedLine = String.format("%-39s %12s", catName, amountStr);
                        sb.append(String.format("║  %-52s║\n", formattedLine));
                    } else {
                        sb.append(String.format("║  %-52s║\n", trimmedLine));
                    }
                }
            }
        }
        
        sb.append("╠══════════════════════════════════════════════════════╣\n");
        sb.append("║                       ANALYTICS                      ║\n");
        
        String avgString = String.format("Average Transaction: $%9.2f", averageTransaction);
        String catString = String.format("Categories Used: %2d", countUsedCategories());
        sb.append(String.format("║  %-31s   %-18s║\n", avgString, catString));
        
        sb.append("╚══════════════════════════════════════════════════════╝\n");
        
        return sb.toString();
    }
}