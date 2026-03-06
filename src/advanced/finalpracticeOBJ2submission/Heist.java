package advanced.finalpracticeOBJ2submission;

import java.util.ArrayList;

public class Heist {
    private Target target;
    private Difficulty difficulty;
    private EscapeMethod escape;
    private ArrayList<Item> tools;
    private String note;
    private String mentorName;

    public Heist(Target target, Difficulty difficulty, EscapeMethod escape, String mentorName) {
        this.target = target;
        this.difficulty = difficulty;
        this.escape = escape;
        this.tools = new ArrayList<>();
        this.note = "";
        this.mentorName = mentorName;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Target getTarget() {
        return target;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public EscapeMethod getEscape() {
        return escape;
    }

    public ArrayList<Item> getTools() {
        return tools;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void addTool(Item tool) {
        tools.add(tool);
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Item tool : tools) {
            subtotal += tool.getValue();
        }
        return subtotal;
    }

    public void printSummary(int heistNumber) {
        StringBuilder summary = new StringBuilder();

        summary.append("\n========================================\n");
        summary.append("          HEIST SUMMARY (").append(heistNumber).append(")\n");
        summary.append("========================================\n");
        summary.append("Target: ").append(target.getName()).append("\n");
        summary.append("Difficulty: ").append(difficulty.getName()).append("\n");
        summary.append("Escape: ").append(escape.getName()).append("\n");
        summary.append("Mentor: ").append(mentorName).append("\n");
        summary.append("Tools:\n");

        for (Item tool : tools) {
            summary.append(" - ").append(tool.getName()).append(" ($").append(String.format("%.2f", tool.getValue())).append(")\n");
        }

        summary.append("Subtotal: $").append(String.format("%.2f", calculateSubtotal())).append("\n");

        if (!note.isEmpty()) {
            summary.append("Note: ").append(note).append("\n");
        }

        summary.append("----------------------------------------\n");

        System.out.print(summary.toString());
    }
}
