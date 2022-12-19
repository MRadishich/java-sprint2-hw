import java.util.HashMap;

public class AnnualReport {
    private final int YEAR;
    private HashMap<Integer, HashMap<Boolean, Integer>> annualReport = new HashMap<>();

    public AnnualReport(int year) {
        this.YEAR = year;
    }

    public int getYear() {
        return this.YEAR;
    }

    public HashMap<Integer, HashMap<Boolean, Integer>> getAnnualReport() {
        return annualReport;
    }

    public void setAnnualReport(HashMap<Integer, HashMap<Boolean, Integer>> annualReport) {
        this.annualReport = annualReport;
    }

    public void printMonthlyProfit() {
        for (Integer month : annualReport.keySet()) {
            HashMap<Boolean, Integer> monthlyExpenseAndIncome = annualReport.get(month);
            System.out.println("    " + Months.getName(month) + ": " +
                    (monthlyExpenseAndIncome.getOrDefault(false, 0) - monthlyExpenseAndIncome.getOrDefault(true, 0)));
        }
    }

    public double getAverageExpense() {
        double sumExpense = 0;
        for (Integer month : annualReport.keySet()) {
            HashMap<Boolean, Integer> monthlyExpenseAndIncome = annualReport.get(month);
            sumExpense += monthlyExpenseAndIncome.getOrDefault(true, 0);
        }
        return sumExpense / annualReport.size();
    }

    public double getAverageIncome() {
        double sumIncome = 0;
        for (Integer month : annualReport.keySet()) {
            HashMap<Boolean, Integer> monthlyExpenseAndIncome = annualReport.get(month);
            sumIncome += monthlyExpenseAndIncome.getOrDefault(false, 0);
        }
        return sumIncome / annualReport.size();
    }
}
