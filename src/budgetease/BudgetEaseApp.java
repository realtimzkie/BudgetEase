package budgetease;

import java.util.*;

public class BudgetEaseApp {
    private final BudgetManager manager;
    private final Scanner scanner = new Scanner(System.in);

    public BudgetEaseApp() {
        StrictValidator validator = new StrictValidator();
        DetailedReportGenerator reportGen = new DetailedReportGenerator();
        this.manager = new BudgetManager(validator, reportGen);
    }

    public static void main(String[] args) {
        new BudgetEaseApp().run();
    }

    private void run() {
        while (true) {
            displayMenu();
            try {
                int choice = getInt("Choice: ");
                handleChoice(choice);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            }
        }
    }

    private void displayMenu() {
        String capacityInfo = manager.getTransactionCount() == 100 ? 
            "⚠️  MAX CAPACITY (100/100)" : "";
        
        System.out.println(String.format("""
                ┌─────────────────────────────────────┐
                │           BudgetEase Menu           │
                ├─────────────────────────────────────┤
                │ 1.   Add Expense                    │
                │ 2.   Add Income                     │
                │ 3.   View Balance                   │
                │ 4.   Generate Report                │
                │ 5.   Transactions by Category       │
                │ 6.   All Transactions               │
<<<<<<< HEAD
                │ 7.   Get Budget Tip                 │
                │ 8.   Exit                           │
=======
                │ 7.   Exit                           │
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
                └─────────────────────────────────────┘
                %s
                """, capacityInfo));
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addExpense();
            case 2 -> addIncome();
            case 3 -> System.out.println(" Balance: $" + String.format("%.2f", manager.getBalance()));
            case 4 -> System.out.println("\n" + manager.generateReport());
            case 5 -> showTransactionsByCategory();
            case 6 -> showAllTransactions();
<<<<<<< HEAD
            case 7 -> System.out.println("💡 " + manager.getRandomBudgetTip());
            case 8 -> {
=======
            case 7 -> {
>>>>>>> 3c933d9d1f28a1ba9c935108126be1f91dd2cabf
                System.out.println(" Thank you for using BudgetEase!");
                System.exit(0);
            }
            default -> System.out.println(" Invalid option");
        }
        System.out.println();
    }

    private void addExpense() {
        String desc = getString("Description: ");
        double amount = getDouble("Amount ($): ");
        Category cat = selectExpenseCategory();  
        manager.addExpense(desc, amount, cat);
        System.out.println(" Expense added (Category: " + cat.getDisplayName() + ")");
    }

    private void addIncome() {
        String desc = getString("Description: ");
        double amount = getDouble("Amount ($): ");
        manager.addIncome(desc, amount, Category.INCOME);
        System.out.printf(" Income added: $%.2f (INCOME category)%n", amount);
    }

    private Category selectExpenseCategory() {
        System.out.println("Expense Category:");
        Category[] expenses = {Category.FOOD, Category.TRANSPORT, Category.ENTERTAINMENT,
                              Category.UTILITIES, Category.SHOPPING, Category.OTHER};
        for (int i = 0; i < expenses.length; i++) {
            System.out.println((i + 1) + ". " + expenses[i].getDisplayName());
        }
        return expenses[getInt("Select (1-6): ") - 1];
    }

    private void showTransactionsByCategory() {
        Category cat = selectExpenseCategory();  // Only expense categories
        Transaction[] transactions = manager.getTransactionsByCategory(cat);
        
        if (transactions.length == 0) {
            System.out.println("📭 No " + cat.getDisplayName() + " transactions");
            return;
        }
        
        System.out.println("\n📋 " + cat.getDisplayName() + " Transactions (" + transactions.length + "):");
        System.out.println("ID     | Type     | Description           | Amount  | Date");
        System.out.println("-------|----------|----------------------|---------|----------");
        for (Transaction t : transactions) {
            System.out.printf("%-6s | %-8s | %-20s | $%7.2f | %s%n",
                t.getId(), t.getType(), t.getDescription(), t.getAmount(), t.getDate());
        }
    }

    private void showAllTransactions() {
        Transaction[] transactions = manager.getAllTransactions();
        
        if (transactions.length == 0) {
            System.out.println("📭 No transactions yet");
            return;
        }
        
        System.out.println("\n📋 All Transactions (" + transactions.length + "):");
        System.out.println("ID     | Type     | Description           | Amount  | Date");
        System.out.println("-------|----------|----------------------|---------|----------");
        for (Transaction t : transactions) {
            System.out.printf("%-6s | %-8s | %-20s | $%7.2f | %s%n",
                t.getId(), t.getType(), t.getDescription(), t.getAmount(), t.getDate());
        }
    }

    private String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(getString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private double getDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(getString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount");
            }
        }
    }
}