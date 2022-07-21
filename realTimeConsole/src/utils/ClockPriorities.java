package utils;

import java.util.Arrays;
import java.util.List;

public class ClockPriorities {
    public static List<Integer> getPriorityOrder(int second, boolean fixed) {
        return fixed ?
                Arrays.asList(0, 1, 2, 3)
                : MockValues.getPriorityTest().get(second);
    }
}
