package utils;

import models.ExecutionLevels;

public class CPUUtilization {
    private static final double UtilLevel1 = 0.7;
    private static final double UtilLevel2 = 0.9;

    private static double getUtilization(int second, boolean test) {
//        ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class).getCpuLoad();
        int[] testUtil = MockValues.getUtilizationTest();
        return (double) testUtil[second] / 100;
    }

    public static ExecutionLevels getExecutionPlan(int second, boolean test) {
        double cpuUtil = getUtilization(second, test);
        if (cpuUtil >= UtilLevel2) {
            return ExecutionLevels.HighestPriorityOnly;
        } else if (cpuUtil >= UtilLevel1) {
            return ExecutionLevels.TopPriorities;
        }
        return ExecutionLevels.AvailableForAll;
    }
}
