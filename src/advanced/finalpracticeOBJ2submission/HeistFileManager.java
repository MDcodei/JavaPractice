package advanced.finalpracticeOBJ2submission;

import java.io.*;
import java.util.ArrayList;

public class HeistFileManager {

    private static final String FILE_PATH = "src/advanced/finalpracticeOBJ2submission/heists.txt";


    public static void saveHeistsToFile(ArrayList<Heist> heists, int heat, int streak, double totalEscapedLoot) {
        try {

            FileWriter fileWriter = new FileWriter(FILE_PATH, false);


            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            for (Heist heist : heists) {
                bufferedWriter.write("TARGET:" + heist.getTarget().getName() + "\n");
                bufferedWriter.write("DIFFICULTY:" + heist.getDifficulty().getName() + "\n");
                bufferedWriter.write("ESCAPE:" + heist.getEscape().getName() + "\n");
                bufferedWriter.write("MENTOR:" + heist.getMentorName() + "\n");


                bufferedWriter.write("TOOLS:");
                ArrayList<Item> tools = heist.getTools();
                for (int i = 0; i < tools.size(); i++) {
                    Item tool = tools.get(i);
                    bufferedWriter.write(tool.getName() + "(" + tool.getValue() + ")");
                    if (i < tools.size() - 1) {
                        bufferedWriter.write(",");
                    }
                }
                bufferedWriter.write("\n");

                bufferedWriter.write("NOTE:" + heist.getNote() + "\n");
                bufferedWriter.write("---\n");
            }


            // Write session footer
            bufferedWriter.write("HEAT=" + heat + "\n");
            bufferedWriter.write("STREAK=" + streak + "\n");
            bufferedWriter.write("TOTAL_ESCAPED_LOOT=" + totalEscapedLoot + "\n");


            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Heists saved to " + FILE_PATH);

        } catch (IOException e) {
            // IOException: Handles file-related errors
            System.out.println("Error saving heists: " + e.getMessage());
        }
    }


    public static ArrayList<Heist> loadHeistsFromFile() {
        ArrayList<Heist> loadedHeists = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(FILE_PATH);


            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            Heist currentHeist = null;
            String target = "", difficulty = "", escape = "", mentor = "", note = "";
            ArrayList<Item> tools = new ArrayList<>();


            while ((line = bufferedReader.readLine()) != null) {

                if (line.equals("---")) {

                    if (!target.isEmpty() && !difficulty.isEmpty() && !escape.isEmpty() && !mentor.isEmpty()) {
                        Target targetEnum = Target.fromChoice(getTargetChoice(target));
                        Difficulty diffEnum = Difficulty.fromChoice(getDifficultyChoice(difficulty));
                        EscapeMethod escapeEnum = EscapeMethod.fromChoice(getEscapeChoice(escape));

                        if (targetEnum != null && diffEnum != null && escapeEnum != null) {
                            currentHeist = new Heist(targetEnum, diffEnum, escapeEnum, mentor);


                            for (Item tool : tools) {
                                currentHeist.addTool(tool);
                            }

                            if (!note.isEmpty()) {
                                currentHeist.setNote(note);
                            }

                            loadedHeists.add(currentHeist);
                        }
                    }


                    target = "";
                    difficulty = "";
                    escape = "";
                    mentor = "";
                    note = "";
                    tools = new ArrayList<>();

                } else if (line.startsWith("TARGET:")) {
                    target = line.substring("TARGET:".length());

                } else if (line.startsWith("DIFFICULTY:")) {
                    difficulty = line.substring("DIFFICULTY:".length());

                } else if (line.startsWith("ESCAPE:")) {
                    escape = line.substring("ESCAPE:".length());

                } else if (line.startsWith("MENTOR:")) {
                    mentor = line.substring("MENTOR:".length());

                } else if (line.startsWith("TOOLS:")) {
                    String toolsString = line.substring("TOOLS:".length());
                    if (!toolsString.isEmpty()) {
                        String[] toolArray = toolsString.split(",");
                        for (String toolData : toolArray) {

                            int openParen = toolData.indexOf('(');
                            int closeParen = toolData.indexOf(')');
                            if (openParen != -1 && closeParen != -1) {
                                String toolName = toolData.substring(0, openParen);
                                String toolValue = toolData.substring(openParen + 1, closeParen);
                                try {
                                    double value = Double.parseDouble(toolValue);
                                    tools.add(new Item(toolName, value));
                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing tool value: " + toolValue);
                                }
                            }
                        }
                    }

                } else if (line.startsWith("NOTE:")) {
                    note = line.substring("NOTE:".length());
                }
            }

            // Close resources
            bufferedReader.close();
            fileReader.close();

            System.out.println("Loaded " + loadedHeists.size() + " heists from " + FILE_PATH);

        } catch (FileNotFoundException e) {

            System.out.println("No saved heists found. Starting fresh.");

        } catch (IOException e) {

            System.out.println("Error loading heists: " + e.getMessage());
        }

        return loadedHeists;
    }


    private static int getTargetChoice(String targetName) {
        switch (targetName) {
            case "Bank": return 1;
            case "Mansion": return 2;
            case "Post Office": return 3;
            case "Supermarket": return 4;
            default: return 0;
        }
    }

    private static int getDifficultyChoice(String diffName) {
        switch (diffName) {
            case "EASY": return 1;
            case "MEDIUM": return 2;
            case "HARD": return 3;
            default: return 0;
        }
    }

    private static int getEscapeChoice(String escapeName) {
        switch (escapeName) {
            case "CAR": return 1;
            case "BIKE": return 2;
            case "BOAT": return 3;
            case "ON_FOOT": return 4;
            default: return 0;
        }
    }

    private static int getRobberChoice(String robberName) {
        switch (robberName) {
            case "Rob": return 1;
            case "Bobby": return 2;
            default: return 0;
        }
    }

    /**
     * Delete the heist file (clear all saved heists)
     */
    public static void deleteHeistFile() {
        try {
            File file = new File(FILE_PATH);
            System.out.println("Attempting to delete: " + file.getAbsolutePath());
            System.out.println("File exists: " + file.exists());
            System.out.println("File can write: " + file.canWrite());
            System.out.println("File can read: " + file.canRead());

            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("All heists cleared from file!");
                } else {
                    System.out.println("Failed to delete heists file.");
                    System.out.println("File is locked or in use by another process.");
                }
            } else {
                System.out.println("No heists file to delete.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting heists: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
