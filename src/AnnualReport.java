import java.util.HashMap;

public class AnnualReport {
    private final int YEAR;
    private final HashMap<Integer, HashMap<Boolean, Integer>> ANNUAL_REPORT = new HashMap<>();

    public AnnualReport(int year) {
        this.YEAR = year;
    }

    public int getYear() {
        return this.YEAR;
    }

    public HashMap<Integer, HashMap<Boolean, Integer>> getAnnualReport() {
        return ANNUAL_REPORT;
    }

    public void printMonthlyProfit() {
        for (Integer month : ANNUAL_REPORT.keySet()) {
            HashMap<Boolean, Integer> monthlyExpenseAndIncome = ANNUAL_REPORT.get(month);
            System.out.println("    " + Months.getName(month) + ": " +
                    (monthlyExpenseAndIncome.getOrDefault(false, 0) - monthlyExpenseAndIncome.getOrDefault(true, 0)));
        }
    }

    public double getAverageExpense() {
        double sumExpense = 0;
        for (Integer month : ANNUAL_REPORT.keySet()) {
            HashMap<Boolean, Integer> monthlyExpenseAndIncome = ANNUAL_REPORT.get(month);
            sumExpense += monthlyExpenseAndIncome.getOrDefault(true, 0);
        }
        return sumExpense / ANNUAL_REPORT.size();
    }

    public double getAverageIncome() {
        double sumIncome = 0;
        for (Integer month : ANNUAL_REPORT.keySet()) {
            HashMap<Boolean, Integer> monthlyExpenseAndIncome = ANNUAL_REPORT.get(month);
            sumIncome += monthlyExpenseAndIncome.getOrDefault(false, 0);
        }
        return sumIncome / ANNUAL_REPORT.size();
    }
}
