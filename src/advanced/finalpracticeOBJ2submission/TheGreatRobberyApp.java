package advanced.finalpracticeOBJ2submission;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class TheGreatRobberyApp {

    private static ArrayList<Heist> plannedHeists = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static City city;
    private static Gang gang;
    private static Police police;

    // Session state tracking fields
    private static int heat = 0; // 0..100
    private static int streak = 0;
    private static double totalEscapedLoot = 0.0;
    private static final Random rng = new Random();

    public static void main(String[] args) {
        city = new City();
        gang = new Gang();
        police = new Police();


        plannedHeists = HeistFileManager.loadHeistsFromFile();

        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = getIntInput("> ");

            if (choice == 1) {
                planNewHeist();
            } else if (choice == 2) {

                HeistFileManager.saveHeistsToFile(plannedHeists, heat, streak, totalEscapedLoot);
                showSummaryAndExit();
                running = false;
            } else if (choice == 3) {
                // Clear all heists and delete file
                plannedHeists.clear();
                HeistFileManager.deleteHeistFile();
            } else if (choice == 4) {
                // Resolve all planned heists
                resolveAllPlannedHeists();
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void showMainMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n");
        menu.append("[1] Plan a new Heist\n");
        menu.append("[2] Show Summary & Save & Exit\n");
        menu.append("[3] Clear All Heists\n");
        menu.append("[4] Resolve All Planned Heists (simulate results)\n");
        System.out.print(menu.toString());
    }

    private static void planNewHeist() {
        System.out.println();
        System.out.println("Choose a target:");
        System.out.println("[1] Bank [2] Mansion [3] Post Office [4] Supermarket");
        int targetChoice = getIntInput("> ");
        Target target = Target.fromChoice(targetChoice);

        if (target == null) {
            System.out.println("Invalid target!");
            return;
        }

        System.out.println("Choose difficulty: [1] EASY [2] MEDIUM [3] HARD");
        int diffChoice = getIntInput("> ");
        Difficulty difficulty = Difficulty.fromChoice(diffChoice);

        if (difficulty == null) {
            System.out.println("Invalid difficulty!");
            return;
        }

        System.out.println("Choose escape method: [1] CAR [2] BIKE [3] BOAT [4] ON_FOOT");
        int escapeChoice = getIntInput("> ");
        EscapeMethod escape = EscapeMethod.fromChoice(escapeChoice);

        if (escape == null) {
            System.out.println("Invalid escape method!");
            return;
        }

        System.out.println("Choose your mentor: [1] Rob (The head) [2] Bobby (The mountain)");
        int mentorChoice = getIntInput("> ");

        // Instantiate temporary Gang to get criminals
        Gang tempGang = new Gang();
        Criminal[] criminals = tempGang.getCriminals();

        if (mentorChoice < 1 || mentorChoice > criminals.length) {
            System.out.println("Invalid mentor!");
            return;
        }

        Criminal selectedMentor = criminals[mentorChoice - 1];
        String mentorName = selectedMentor.getName() + " (" + selectedMentor.getNickname() + ")";

        Heist heist = new Heist(target, difficulty, escape, mentorName);

        // Add mentor's items automatically
        StringBuilder mentorItems = new StringBuilder();
        mentorItems.append("\n").append(selectedMentor.getName()).append(" (").append(selectedMentor.getNickname()).append(") brings:\n");

        for (Item item : selectedMentor.getItems()) {
            heist.addTool(item);
            mentorItems.append(" - ").append(item.getName()).append(" ($").append(String.format("%.2f", item.getValue())).append(")\n");
        }

        System.out.print(mentorItems.toString());


        Item[] additionalTools = {
            new Item("gun", 1.5),
            new Item("knife", 2.5),
            new Item("bat", 1.5),
            new Item("scissors", 2.5)
        };

        System.out.println("\nAdd additional tools (0 to finish): [1] gun [2] knife [3] bat [4] scissors");
        while (true) {
            int toolChoice = getIntInput("> ");
            if (toolChoice == 0) {
                break;
            }
            if (toolChoice >= 1 && toolChoice <= 4) {
                Item selectedTool = additionalTools[toolChoice - 1];
                heist.addTool(selectedTool);
                System.out.println("Added: " + selectedTool.getName());
            } else {
                System.out.println("Invalid tool choice!");
            }
        }


        plannedHeists.add(heist);
        System.out.println("Heist planned successfully! Returning to main menu...");
    }

    private static void showSummaryAndExit() {
        StringBuilder summary = new StringBuilder();
        summary.append("\n========================================\n");
        summary.append("          EXIT SUMMARY\n");
        summary.append("========================================\n");

        if (plannedHeists.isEmpty()) {
            summary.append("No heists planned yet!\n");
        } else {
            for (int i = 0; i < plannedHeists.size(); i++) {
                plannedHeists.get(i).printSummary(i + 1);
            }
            summary.append("----------------------------------------\n");
            summary.append("Total heists planned: ").append(plannedHeists.size()).append("\n");
        }

        summary.append("----------------------------------------\n");
        System.out.print(summary.toString());
    }

    private static int getIntInput(String prompt) {
        try {
            System.out.print(prompt);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return getIntInput(prompt);
        }
    }

    // Getters for session state fields
    public static int getHeat() {
        return heat;
    }

    public static int getStreak() {
        return streak;
    }

    public static double getTotalEscapedLoot() {
        return totalEscapedLoot;
    }

    public static Random getRng() {
        return rng;
    }

    /**
     * Resolves a planned heist and returns the outcome
     */
    private static HeistOutcome resolveHeist(Heist heist, Police police, Gang gang) {
        List<String> eventsApplied = new ArrayList<>();
        int successMod = 0;
        int catchMod = 0;

        // Random events (20% chance each)
        if (rng.nextInt(100) < 20) {
            int eventRoll = rng.nextInt(3);
            switch (eventRoll) {
                case 0:
                    eventsApplied.add("Guard yawned");
                    successMod += 5;
                    break;
                case 1:
                    eventsApplied.add("Camera rebooted");
                    successMod += 5;
                    break;
                case 2:
                    eventsApplied.add("Rainstorm");
                    catchMod += 10;
                    break;
            }
        }


        int successChance = Balance.BASE_SUCCESS;
        successChance += Balance.DIFF_BONUS.get(heist.getDifficulty());


        String mentorFirstName = heist.getMentorName().split(" ")[0];
        successChance += Balance.MENTOR_SUCCESS_BONUS.get(mentorFirstName);

        successChance += successMod;
        successChance = Math.max(5, Math.min(95, successChance)); // Clamp to [5, 95]


        int successRoll = rng.nextInt(1, 101);
        boolean success = successRoll <= successChance;

        double lootValue = 0.0;
        if (success) {
            // Compute loot value
            Building targetBuilding = null;
            // Find the target building from city
            for (Building building : city.getBuildings()) {
                if (building.getName().equals(heist.getTarget().getName())) {
                    targetBuilding = building;
                    break;
                }
            }

            if (targetBuilding != null) {
                for (Item item : targetBuilding.getItems()) {
                    lootValue += item.getValue();
                }

                // +10% bonus if tools include both gun and knife
                boolean hasGun = false;
                boolean hasKnife = false;
                for (Item tool : heist.getTools()) {
                    if (tool.getName().equals("gun")) hasGun = true;
                    if (tool.getName().equals("knife")) hasKnife = true;
                }
                if (hasGun && hasKnife) {
                    lootValue *= 1.1; // +10%
                }
            }
        }


        int catchChance = Balance.POLICE_BASE_CATCH;
        catchChance += Balance.ESCAPE_CATCH_MOD.get(heist.getEscape());
        catchChance += heat / 10; // heat effect
        catchChance += catchMod;
        catchChance = Math.max(5, Math.min(95, catchChance)); // Clamp to [5, 95]


        int chaseRoll = rng.nextInt(1, 101);
        boolean caughtByPolice = success && (chaseRoll <= catchChance);


        String narrative;
        if (success) {
            if (caughtByPolice) {
                narrative = "Blue lights flood the alley; bags recovered.";
            } else {
                narrative = "The crew slips away with the goods.";
            }
        } else {
            narrative = "A misstep at the skylight forces a retreat.";
        }

        return new HeistOutcome(success, caughtByPolice, lootValue, successRoll, chaseRoll, successChance, catchChance, eventsApplied, narrative);
    }


    private static void applyOutcomeToState(HeistOutcome out) {
        if (out.isSuccess() && !out.isCaughtByPolice()) {
            // Successful heist, escaped with loot
            streak++;
            heat = Math.max(0, heat - 5);
            totalEscapedLoot += out.getLootValue();
        } else if (out.isSuccess() && out.isCaughtByPolice()) {
            // Successful heist but caught by police
            streak = 0;
            heat = Math.min(100, heat + 10);
        } else if (!out.isSuccess()) {
            // Failed heist
            streak = 0;
            heat = Math.min(100, heat + 5);
        }
    }

    /**
     * Resolves all planned heists and updates session state
     */
    private static void resolveAllPlannedHeists() {
        if (plannedHeists.isEmpty()) {
            System.out.println("No heists to resolve.");
            return;
        }

        // Create fresh Police and Gang instances for simulation
        Police freshPolice = new Police();
        Gang freshGang = new Gang();

        StringBuilder header = new StringBuilder();
        header.append("\n========================================\n");
        header.append("     RESOLVING ALL PLANNED HEISTS\n");
        header.append("========================================\n");
        System.out.print(header.toString());

        for (int i = 0; i < plannedHeists.size(); i++) {
            Heist heist = plannedHeists.get(i);
            HeistOutcome outcome = resolveHeist(heist, freshPolice, freshGang);

            // Build heist header with StringBuilder
            StringBuilder heistHeader = new StringBuilder();
            heistHeader.append("--- Heist #").append((i + 1)).append(" ---\n");
            heistHeader.append("Target: ").append(heist.getTarget().getName())
                   .append(" | Difficulty: ").append(heist.getDifficulty().getName())
                   .append(" | Escape: ").append(heist.getEscape().getName()).append("\n");
            heistHeader.append("Mentor: ").append(heist.getMentorName().split(" ")[0]).append("\n");
            System.out.print(heistHeader.toString());

            // Build result section with StringBuilder
            StringBuilder result = new StringBuilder();
            result.append("\n========================================\n");
            result.append("              HEIST RESULT\n");
            result.append("========================================\n");
            result.append("Executed Heist: ").append(outcome.isSuccess() ? "YES" : "NO").append("\n");
            result.append("Escaped With Loot: ").append((outcome.isSuccess() && !outcome.isCaughtByPolice()) ? "YES" : "NO").append("\n");

            if (outcome.isSuccess()) {
                result.append("Police Caught You: ").append(outcome.isCaughtByPolice() ? "YES" : "NO").append("\n");
            } else {
                result.append("Police Caught You: Yes (heist failed)\n");
            }

            // Loot lines based on execution and escape status
            if (!outcome.isSuccess()) {
                result.append("Loot Taken: $0.00\n");
            } else if (outcome.isCaughtByPolice()) {
                result.append("Loot Confiscated: $").append(String.format("%.2f", outcome.getLootValue())).append("\n");
            } else {
                result.append("Loot Escaped: $").append(String.format("%.2f", outcome.getLootValue())).append("\n");
            }

            if (!outcome.getEventsApplied().isEmpty()) {
                result.append("Events: ").append(String.join(", ", outcome.getEventsApplied())).append("\n");
            }

            result.append("----------------------------------------\n");
            System.out.print(result.toString());

            // Apply outcome to session state
            applyOutcomeToState(outcome);
        }

        // Clear planned heists after resolving
        plannedHeists.clear();

        // Print session totals with StringBuilder
        StringBuilder totals = new StringBuilder();
        totals.append("\n========================================\n");
        totals.append("          SESSION TOTALS\n");
        totals.append("========================================\n");
        totals.append("Current Streak: ").append(streak).append("\n");
        totals.append("Current Heat: ").append(heat).append("\n");
        totals.append("Total Loot Escaped: $").append(String.format("%.2f", totalEscapedLoot)).append("\n");
        totals.append("----------------------------------------\n");
        totals.append("All planned heists have been resolved and cleared.\n");
        System.out.print(totals.toString());
    }
}
