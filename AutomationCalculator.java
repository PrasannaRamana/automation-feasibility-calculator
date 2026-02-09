public class AutomationCalculator {
    public static final int WEEKS_PER_YEAR = 52;

    public static double ManualCostPerYear(double timeperTask,int weeklyTasks,double costPerHour){
        double hoursPerTask = timeperTask / 60.0;
        return hoursPerTask*weeklyTasks*WEEKS_PER_YEAR*costPerHour;
    }

    public static double AutomationCost(double devCost, double Mainteinance){
        return devCost + Mainteinance;
    }

    public static double Savings(double ManualCostPerYear, double AutomationCost){
        return ManualCostPerYear - AutomationCost;
    }
    

}