package budgetease;

import java.util.concurrent.ThreadLocalRandom;

public final class BudgetAdviceProvider {
    private static final String[] TIPS = {
        "Track every expense for one week to find easy savings.",
        "Set a spending limit for each category and review it monthly.",
        "Use a shopping list to avoid impulse purchases.",
        "Automate savings by transferring money to a separate account.",
        "Compare prices before buying big-ticket items.",
        "Cut subscriptions you no longer use and save the difference.",
        "Cook at home more often instead of ordering takeout.",
        "Review your recurring bills to find opportunities for discounts.",
        "Pay yourself first by saving before you spend.",
        "Keep a buffer for unexpected expenses so you stay on budget."
    };

    private BudgetAdviceProvider() {
        // Utility class, no instances.
    }

    public static String getRandomTip() {
        int index = ThreadLocalRandom.current().nextInt(TIPS.length);
        return TIPS[index];
    }
}
