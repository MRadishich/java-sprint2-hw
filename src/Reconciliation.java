import java.util.HashMap;

public class Reconciliation {
    private final MonthlyReports MONTHLY_REPORTS;
    private final AnnualReport ANNUAL_REPORT;

    public Reconciliation(MonthlyReports monthlyReports, AnnualReport annualReport) {
        this.MONTHLY_REPORTS = monthlyReports;
        this.ANNUAL_REPORT = annualReport;
    }

    public void compareReports() {

        HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByMonthlyReports = MONTHLY_REPORTS.getExpensesAndIncomeByMonthlyReports();
        HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByAnnualReport = ANNUAL_REPORT.getAnnualReport();
        
        boolean isEquals =  verifyReports(monthlyExpensesAndIncomeByMonthlyReports, monthlyExpensesAndIncomeByAnnualReport);
        System.out.println(isEquals ? "Сверка прошла успешно! Расхождений не выявлено." : "");
    }

    private boolean verifyReports(HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByMonthlyReports,
                                         HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByAnnualReport) {

        boolean allMonthlyReportsInAnnualReport = allMonthlyReportsInAnnualReport(monthlyExpensesAndIncomeByMonthlyReports, monthlyExpensesAndIncomeByAnnualReport);
        boolean eachMonthFromTheAnnualReportHasMonthlyReport = eachMonthFromTheAnnualReportHasMonthlyReport(monthlyExpensesAndIncomeByMonthlyReports, monthlyExpensesAndIncomeByAnnualReport);
        boolean compareAmountExpensesAndIncome = compareAmountExpensesAndIncome(monthlyExpensesAndIncomeByMonthlyReports, monthlyExpensesAndIncomeByAnnualReport);

        return allMonthlyReportsInAnnualReport && eachMonthFromTheAnnualReportHasMonthlyReport && compareAmountExpensesAndIncome;
    }

    private boolean allMonthlyReportsInAnnualReport(HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByMonthlyReports, HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByAnnualReport) {
        boolean isCorrect = true;
        for (Integer month : monthlyExpensesAndIncomeByAnnualReport.keySet()) {
            HashMap<Boolean, Integer> amountTransactionByMonthlyReports = monthlyExpensesAndIncomeByMonthlyReports.get(month);

            if (amountTransactionByMonthlyReports == null) {
                System.out.println("Выявлено расхождение! В годовом отчете есть данные за " + month +
                        " месяц, но ежемесячный отчет отсутствует.");
                isCorrect = false;
            }

        }
        return isCorrect;
    }

    private boolean eachMonthFromTheAnnualReportHasMonthlyReport(HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByMonthlyReports,
                                                                        HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByAnnualReport) {
        boolean isCorrect = true;

        for (Integer month : monthlyExpensesAndIncomeByMonthlyReports.keySet()) {
            HashMap<Boolean, Integer> amountTransactionsByAnnualReport = monthlyExpensesAndIncomeByAnnualReport.get(month);
            
            if (amountTransactionsByAnnualReport == null) {
                System.out.println("Выявлено расхождение! Есть ежемесячный отчет за " + month +
                        " месяц, но в годовом отчете данные отсутствуют.");
                isCorrect = false;
            }
        }
        return isCorrect;
    }

    private boolean compareAmountExpensesAndIncome(HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByMonthlyReports, HashMap<Integer, HashMap<Boolean, Integer>> monthlyExpensesAndIncomeByAnnualReport) {
        boolean firstCheck = true;
        boolean secondCheck = true;

        for (Integer month : monthlyExpensesAndIncomeByAnnualReport.keySet()) {
            HashMap<Boolean, Integer> amountTransactionByAnnualReport = monthlyExpensesAndIncomeByAnnualReport.get(month);
            HashMap<Boolean, Integer> amountTransactionByMonthlyReports = monthlyExpensesAndIncomeByMonthlyReports.get(month);

            firstCheck = compareTransactionsFromAnnualReportWithMonthlyReports(month, amountTransactionByAnnualReport, amountTransactionByMonthlyReports);
            secondCheck = compareTransactionsFromMonthlyReportsWithAnnualReport(month, amountTransactionByAnnualReport, amountTransactionByMonthlyReports);
        }
        return firstCheck && secondCheck;
    }

    private boolean compareTransactionsFromAnnualReportWithMonthlyReports(int month,
                                                                          HashMap<Boolean, Integer> amountTransactionByAnnualReport,
                                                                          HashMap<Boolean, Integer> amountTransactionByMonthlyReports) {
        boolean isCorrect = true;
        try {
            for (Boolean transaction : amountTransactionByAnnualReport.keySet()) {
                int amountFromMonthlyReports = amountTransactionByMonthlyReports.getOrDefault(transaction, 0);
                int amountFromAnnualReports = amountTransactionByAnnualReport.get(transaction);

                if (amountFromMonthlyReports != amountFromAnnualReports) {
                    if (transaction) {
                        System.out.println("Выявлено расхождение! Сумма доходов за " + month +
                                " месяц, по данным ежемесячного отчета, сотавила: " + amountFromMonthlyReports +
                                ", по данным годового отчета: " + amountFromAnnualReports);
                    } else {
                        System.out.println("Выявлено расхождение! Сумма расходов за " + month +
                                " месяц, по данным ежемесячного отчета, сотавила: " + amountFromMonthlyReports +
                                ", по данным годового отчета: " + amountFromAnnualReports);
                    }
                    isCorrect = false;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return isCorrect;
    }

    private boolean compareTransactionsFromMonthlyReportsWithAnnualReport(int month,
                                                                          HashMap<Boolean, Integer> amountTransactionByAnnualReport,
                                                                          HashMap<Boolean, Integer> amountTransactionByMonthlyReports) {
        boolean isCorrect = true;
        try {
            for (Boolean transaction : amountTransactionByMonthlyReports.keySet()) {
                int amountFromMonthlyReports = amountTransactionByMonthlyReports.get(transaction);
                int amountFromAnnualReports = amountTransactionByAnnualReport.getOrDefault(transaction, 0);

                if (amountFromMonthlyReports != amountFromAnnualReports && amountFromAnnualReports == 0) {
                    if (transaction) {
                        System.out.println("Выявлено расхождение! Сумма доходов за " + month +
                                " месяц, по данным ежемесячного отчета, сотавила: " + amountFromMonthlyReports +
                                ", по данным годового отчета: " + amountFromAnnualReports);
                    } else {
                        System.out.println("Выявлено расхождение! Сумма расходов за " + month +
                                " месяц, по данным ежемесячного отчета, сотавила: " + amountFromMonthlyReports +
                                ", по данным годового отчета: " + amountFromAnnualReports);
                    }
                    isCorrect = false;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return isCorrect;
    }
}
