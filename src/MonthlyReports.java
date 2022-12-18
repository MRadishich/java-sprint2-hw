import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReports {

    private final ArrayList<MonthlyReport> MONTHLY_REPORTS = new ArrayList<>();

    public ArrayList<MonthlyReport> getMonthlyReports() {
        return MONTHLY_REPORTS;
    }

    public void addMonthlyReport(MonthlyReport monthlyReport) {
        MONTHLY_REPORTS.add(monthlyReport);
    }

    public HashMap<Integer, HashMap<Boolean, Integer>> getExpensesAndIncomeByMonthlyReports() {
        HashMap<Integer, HashMap<Boolean, Integer>> groupMonthlyTransactions = new HashMap<>();

        for (MonthlyReport monthlyReport : MONTHLY_REPORTS) {
            if (!groupMonthlyTransactions.containsKey(monthlyReport.getMonth())) {
                groupMonthlyTransactions.put(monthlyReport.getMonth(), new HashMap<>());
            }
            int sumExpenses = monthlyReport.getSumExpenses();
            int sumIncome = monthlyReport.getSumIncome();
            HashMap<Boolean, Integer> amountMonthlyExpensesAndIncome = groupMonthlyTransactions.get(monthlyReport.getMonth());
            amountMonthlyExpensesAndIncome.put(true, sumExpenses);
            amountMonthlyExpensesAndIncome.put(false, sumIncome);
        }
        return groupMonthlyTransactions;
    }
}
