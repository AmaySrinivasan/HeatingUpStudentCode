/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Amay Srinivasan
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     *
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        // If there's no temps, then there's no warming trend
        if (temperatures == null || temperatures.length == 0) {
            return 0;
        }
        // Stores length of longest warming trend ending on day i and stores longest trend found
        int[] trendLength = new int[temperatures.length];
        int answer = 1;
        // Tries to use each day as the ending point of a warming trend (computeTrend works recursively)
        for (int i = 0; i < temperatures.length; i++) {
            answer = Math.max(answer, computeTrend(i, temperatures, trendLength));
        }
        return answer;
    }

    // Uses recursion to compute the longest trend ending on given day
    private static int computeTrend(int day, int[] temps, int[] trendLength) {
        // If we've already computed for this day, then just return it
        if (trendLength[day] != 0) {
            return trendLength[day];
        }
        // At the min, trend is just this day, and then check all the earlier days to see if they come before this day
        int maxSoFar = 1;
        for (int prev = day - 1; prev >= 0; prev--) {
            if (temps[prev] < temps[day]) {
                // Extends warming trend prev by including this day
                maxSoFar = Math.max(maxSoFar, computeTrend(prev, temps, trendLength) + 1);
            }
        }
        // Save the computed result so no need to recompute later
        trendLength[day] = maxSoFar;
        return maxSoFar;
    }
}
