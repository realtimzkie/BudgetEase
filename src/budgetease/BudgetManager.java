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
        checkCapacity();
        String id = "EXP" + idCounter++;
        String date = LocalDate.now().toString();
        Expense expense = new Expense(id, description, amount, date, category);
        
        validateTransaction(expense);
        transactions[transactionCount] = expense;
        transactionCount++;
        balance += expense.getBalanceImpact();
    }

    public void addIncome(String description, double amount, Category category) {
        checkCapacity();
        String id = "INC" + idCounter++;
        String date = LocalDate.now().toString();
        Income income = new Income(id, description, amount, date, category);
        
        validateTransaction(income);
        transactions[transactionCount] = income;
        transactionCount++;
        balance += income.getBalanceImpact();
    }

    public DetailedReport generateReport() {
        return reportGenerator.generateReport(this);
    }

    public String getRandomBudgetTip() {
        return BudgetAdviceProvider.getRandomTip();
    }

    public double getBalance() { return balance; }

    public Transaction[] getTransactionsByCategory(Category category) {
        int count = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getCategory() == category) count++;
        }
        
        Transaction[] result = new Transaction[count];
        int resultIndex = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getCategory() == category) {
                result[resultIndex++] = transactions[i];
            }
        }
        return result;
    }

    public Transaction[] getAllTransactions() {
        Transaction[] copy = new Transaction[transactionCount];
        System.arraycopy(transactions, 0, copy, 0, transactionCount);
        return copy;
    }

    public int getTransactionCount() { return transactionCount; }

    private void checkCapacity() {
        if (transactionCount >= MAX_TRANSACTIONS) {
            throw new RuntimeException("⚠️  MAX CAPACITY REACHED: 100 transactions");
        }
    }

    private void validateTransaction(Transaction transaction) {
        if (!validator.isValid(transaction)) {
            throw new IllegalArgumentException("❌ Invalid: " + validator.getValidationRule());
        }
    }
}