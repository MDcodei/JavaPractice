package advanced.finalpracticeOBJ2submission;

import java.util.Map;
import java.util.HashMap;


public class Balance {
    // Base values
    public static final int BASE_SUCCESS = 30;
    public static final int POLICE_BASE_CATCH = 30;

    // Modifiers
    public static final Map<Difficulty, Integer> DIFF_BONUS = new HashMap<>();
    public static final Map<EscapeMethod, Integer> ESCAPE_CATCH_MOD = new HashMap<>();
    public static final Map<String, Integer> MENTOR_SUCCESS_BONUS = new HashMap<>();


    static {

        DIFF_BONUS.put(Difficulty.EASY, 20);
        DIFF_BONUS.put(Difficulty.MEDIUM, 0);
        DIFF_BONUS.put(Difficulty.HARD, -15);


        ESCAPE_CATCH_MOD.put(EscapeMethod.CAR, -10);
        ESCAPE_CATCH_MOD.put(EscapeMethod.BIKE, 0);
        ESCAPE_CATCH_MOD.put(EscapeMethod.BOAT, -5);
        ESCAPE_CATCH_MOD.put(EscapeMethod.ON_FOOT, 10);


        MENTOR_SUCCESS_BONUS.put("Rob", 10);
        MENTOR_SUCCESS_BONUS.put("Bobby", 5);
    }

    // Private constructor to prevent instantiation
    private Balance() {
        // Utility class
    }
}
