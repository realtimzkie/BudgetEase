package budgetease;

public enum Category {
    FOOD("Food"), TRANSPORT("Transport"), ENTERTAINMENT("Entertainment"),
    SALARY("Salary"), INVESTMENT("Investment"), UTILITIES("Utilities"),
    SHOPPING("Shopping"), OTHER("Other");

    private final String displayName;
    Category(String displayName) { this.displayName = displayName; }
    public String getDisplayName() { return displayName; }
}