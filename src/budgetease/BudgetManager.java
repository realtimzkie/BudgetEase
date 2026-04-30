package budgetease;
import java.time.LocalDate;

public class BudgetManager {
    private static final int MAX_TRANSACTIONS = 100;
    private Transaction[] transactions;
    private int transactionCount;
    private double balance;
    private static int idCounter = 1;
    private final TransactionValidator validator;
    private final ReportGenerator reportGenerator;

    public BudgetManager(TransactionValidator validator, ReportGenerator reportGenerator) {
        this.transactions = new Transaction[MAX_TRANSACTIONS];
        this.transactionCount = 0;
        this.balance = 0.0;
        this.validator = validator;
        this.reportGenerator = reportGenerator;
    }

    public void addExpense(String description, double amount, Category category) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            throw new RuntimeException("Maximum transactions reached: " + MAX_TRANSACTIONS);
        }
        
        String id = "EXP" + idCounter++;
        String date = LocalDate.now().toString();
        Expense expense = new Expense(id, description, amount, date, category);
        
        validateTransaction(expense);
        transactions[transactionCount] = expense;
        transactionCount++;
        balance += expense.getBalanceImpact();
    }

    public void addIncome(String description, double amount, Category category) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            throw new RuntimeException("Maximum transactions reached: " + MAX_TRANSACTIONS);
        }
        
        String id = "INC" + idCounter++;
        String date = LocalDate.now().toString();
        Income income = new Income(id, description, amount, date, category);
        
        validateTransaction(income);
        transactions[transactionCount] = income;
        transactionCount++;
        balance += income.getBalanceImpact();
    }

    public Report generateReport() {
        double[] categoryTotals = new double[Category.values().length];
        double totalIncome = 0, totalExpense = 0;

        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];
            int catIndex = t.getCategory().ordinal();
            double amt = Math.abs(t.getAmount());
            
            categoryTotals[catIndex] += amt;
            
            if ("INCOME".equals(t.getType())) {
                totalIncome += amt;
            } else {
                totalExpense += amt;
            }
        }

        Transaction[] transCopy = new Transaction[transactionCount];
        System.arraycopy(transactions, 0, transCopy, 0, transactionCount);
        
        return new Report(totalIncome, totalExpense, balance, categoryTotals, transCopy, transactionCount);
    }

    public double getBalance() { return balance; }

    public Transaction[] getTransactionsByCategory(Category category) {
        int catIndex = category.ordinal();
        int count = 0;
        
        // First pass: count matching transactions
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getCategory() == category) {
                count++;
            }
        }
        
        // Second pass: copy matching transactions
        Transaction[] result = new Transaction[count];
        int resultIndex = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getCategory() == category) {
                result[resultIndex++] = transactions[i];
            }
        }
        return result;
    }

    public int getTransactionCount() { return transactionCount; }
    public Transaction[] getAllTransactions() {
        Transaction[] copy = new Transaction[transactionCount];
        System.arraycopy(transactions, 0, copy, 0, transactionCount);
        return copy;
    }

    private void validateTransaction(Transaction transaction) {
        if (!validator.isValid(transaction)) {
            throw new IllegalArgumentException("Invalid: " + validator.getValidationRule());
        }
    }
}