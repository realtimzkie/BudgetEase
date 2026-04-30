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
                │ 1. ➕ Add Expense                    │
                │ 2. 💵 Add Income                     │
                │ 3. 💰 View Balance                   │
                │ 4. 📊 Generate Report                │
                │ 5. 🔍 Transactions by Category       │
                │ 6. ❌ Exit                           │
                └─────────────────────────────────────┘
                %s
                """, capacityInfo));
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addExpense();
            case 2 -> addIncome();
            case 3 -> System.out.println("💰 Balance: $" + String.format("%.2f", manager.getBalance()));
            case 4 -> System.out.println("\n" + manager.generateReport());
            case 5 -> showTransactionsByCategory();
            case 6 -> {
                System.out.println("👋 Thank you for using BudgetEase!");
                System.exit(0);
            }
            default -> System.out.println("❌ Invalid option");
        }
        System.out.println();
    }

    private void addExpense() {
        String desc = getString("Description: ");
        double amount = getDouble("Amount ($): ");
        Category cat = selectCategory("Expense Category");
        manager.addExpense(desc, amount, cat);
        System.out.println("✅ Expense added successfully");
    }

    private void addIncome() {
        String desc = getString("Description: ");
        double amount = getDouble("Amount ($): ");
        Category cat = selectCategory("Income Category");
        manager.addIncome(desc, amount, cat);
        System.out.println("✅ Income added successfully");
    }

    private void showTransactionsByCategory() {
        Category cat = selectCategory("View Category");
        Transaction[] transactions = manager.getTransactionsByCategory(cat);
        
        if (transactions.length == 0) {
            System.out.println("📭 No transactions for " + cat.getDisplayName());
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

    private Category selectCategory(String title) {
        System.out.println(title);
        Category[] cats = Category.values();
        for (int i = 0; i < cats.length; i++) {
            System.out.println((i + 1) + ". " + cats[i].getDisplayName());
        }
        return cats[getInt("Select (1-" + cats.length + "): ") - 1];
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