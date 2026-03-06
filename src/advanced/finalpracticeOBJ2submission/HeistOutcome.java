package advanced.finalpracticeOBJ2submission;

import java.util.List;
import java.util.ArrayList;

/**
 * POJO representing the outcome of a heist attempt
 */
public class HeistOutcome {
    private boolean success;
    private boolean caughtByPolice;
    private double lootValue;
    private int successRoll;
    private int chaseRoll;
    private int successChance; // Add success chance
    private int catchChance;   // Add catch chance
    private List<String> eventsApplied;
    private String narrative;

    /**
     * Full constructor for HeistOutcome
     */
    public HeistOutcome(boolean success, boolean caughtByPolice, double lootValue,
                       int successRoll, int chaseRoll, List<String> eventsApplied, String narrative) {
        this.success = success;
        this.caughtByPolice = caughtByPolice;
        this.lootValue = lootValue;
        this.successRoll = successRoll;
        this.chaseRoll = chaseRoll;
        this.eventsApplied = new ArrayList<>(eventsApplied); // Defensive copy
        this.narrative = narrative;
    }

    // Add new constructor with chances
    public HeistOutcome(boolean success, boolean caughtByPolice, double lootValue,
                       int successRoll, int chaseRoll, int successChance, int catchChance,
                       List<String> eventsApplied, String narrative) {
        this.success = success;
        this.caughtByPolice = caughtByPolice;
        this.lootValue = lootValue;
        this.successRoll = successRoll;
        this.chaseRoll = chaseRoll;
        this.successChance = successChance;
        this.catchChance = catchChance;
        this.eventsApplied = new ArrayList<>(eventsApplied); // Defensive copy
        this.narrative = narrative;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public boolean isCaughtByPolice() {
        return caughtByPolice;
    }

    public double getLootValue() {
        return lootValue;
    }

    public int getSuccessRoll() {
        return successRoll;
    }

    public int getChaseRoll() {
        return chaseRoll;
    }

    public int getSuccessChance() {
        return successChance;
    }

    public int getCatchChance() {
        return catchChance;
    }

    public List<String> getEventsApplied() {
        return new ArrayList<>(eventsApplied); // Defensive copy
    }

    public String getNarrative() {
        return narrative;
    }

    /**
     * Multi-line summary suitable for terminal output
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== HEIST OUTCOME ===\n");
        sb.append("Success: ").append(success ? "YES" : "NO").append("\n");
        sb.append("Caught by Police: ").append(caughtByPolice ? "YES" : "NO").append("\n");
        sb.append("Loot Value: $").append(String.format("%.2f", lootValue)).append("\n");
        sb.append("Success Roll: ").append(successRoll).append("\n");
        sb.append("Chase Roll: ").append(chaseRoll).append("\n");
        sb.append("Success Chance: ").append(successChance).append("%\n");
        sb.append("Catch Chance: ").append(catchChance).append("%\n");
        sb.append("Events Applied:\n");
        for (String event : eventsApplied) {
            sb.append("  - ").append(event).append("\n");
        }
        sb.append("Narrative: ").append(narrative).append("\n");
        sb.append("=====================");
        return sb.toString();
    }
}
