package budgetease;

public abstract class Transaction {
    private String id;
    private String description;
    private double amount;
    private String date;
    private Category category;

    public Transaction(String id, String description, double amount, String date, Category category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public Category getCategory() { return category; }

    public abstract String getType();
    public abstract double getBalanceImpact();
}