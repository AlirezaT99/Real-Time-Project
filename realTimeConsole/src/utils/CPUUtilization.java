package utils;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

import models.ExecutionLevels;

import java.lang.management.ManagementFactory;

public class CPUUtilization {
    private static final double UtilLevel1 = 0.7;
    private static final double UtilLevel2 = 0.9;

    private static double getUtilization(int second, boolean test) {
        int[] testUtil = MockValues.getUtilizationTest();
        return (double) testUtil[second] / 100;
    }

    public static ExecutionLevels getExecutionPlan(int second, boolean test) {
//        double cpuUtil = getUtilization(second, test);
        double cpuUtil = getCpu();
        if (cpuUtil >= UtilLevel2) {
            return ExecutionLevels.HighestPriorityOnly;
        } else if (cpuUtil >= UtilLevel1) {
            return ExecutionLevels.TopPriorities;
        }
        return ExecutionLevels.AvailableForAll;
    }

    public static double getCpu() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getSystemCpuLoad();
    }
}
