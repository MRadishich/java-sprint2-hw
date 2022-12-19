import java.time.LocalDateTime;
import java.util.Scanner;

public class CommandLineManager {

    private final Scanner in;
    MonthlyReports monthlyReports;
    AnnualReport annualReport;


    public CommandLineManager(Scanner in) {
        this.in = in;
    }

    public void printMenuAndHandleCommandInfinity() {
        sayHello();
        while (true) {
            printMenu();
            String command = in.nextLine();
            if ("exit".equalsIgnoreCase(command)) {
                break;
            } else {
                if (isDigit(command)) {
                    switch (Commands.findCommand(Integer.parseInt(command) - 1)) {
                        case READ_MONTHLY_REPORTS:
                            MonthlyReportParser parseMonthlyReport = new MonthlyReportParser();
                            for (int i = 1; i <= 3; i++ ) {
                                parseMonthlyReport.loadFile(i, "resources/m.20210" + i + ".csv");
                            }
                            monthlyReports = parseMonthlyReport.getMonthlyReports();
                            break;
                        case READ_ANNUAL_REPORT:
                            AnnualReportParser parseAnnualReport = new AnnualReportParser("resources/y.2021.csv");
                            parseAnnualReport.parseAnnualReport();
                            annualReport = parseAnnualReport.getAnnualReport();
                            break;
                        case VERIFY_REPORTS:
                            if (checkMonthlyReportsRead() & checkAnnualReportRead()) {
                                Reconciliation reconciliation = new Reconciliation(monthlyReports, annualReport);
                                reconciliation.compareReports();
                            }
                            break;
                        case DISPLAY_INFO_MONTHLY_REPORTS:
                            if (checkMonthlyReportsRead()) {
                                printMonthlyInfo();
                            }
                            break;
                        case DISPLAY_INFO_ANNUAL_REPORT:
                            if (checkAnnualReportRead()) {
                                printAnnualInfo();
                            }
                            break;
                        default:
                            System.out.println("Такой команды нет");
                    }
                } else {
                    System.out.println("Такой команды нет");
                }
            }
        }
    }

    private void sayHello() {
        Greetings greetings = new Greetings();
        int hour = getLocalHour();
        if (hour < 5) {
            System.out.println(greetings.getGoodNight());
        } else if (hour < 12) {
            System.out.println(greetings.getGoodMorning());
        } else if (hour < 17) {
            System.out.println(greetings.getGoodAfternoon());
        } else if (hour < 21) {
            System.out.println(greetings.getGoodEvening());
        } else {
            System.out.println(greetings.getGoodAfternoon());
        }
        System.out.println("-------------------------");
    }

    private int getLocalHour() {
        return LocalDateTime.now().getHour();
    }

    private void printMenu() {
        System.out.println("Выберите действие, которое хотите совершить:\n" +
                        "1. Считать все месячные отчёты\n" +
                        "2. Считать годовой отчёт\n" +
                        "3. Сверить отчёты\n" +
                        "4. Вывести информацию о всех месячных отчётах\n" +
                        "5. Вывести информацию о годовом отчёте\n" +
                        "Для выхода из программы введите \"exit\"");
    }

    private boolean isDigit(String str) {
        if (str ==  null || str.isEmpty()) {
            return false;
        }

        try {
            int command = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean checkMonthlyReportsRead() {
        boolean monthlyReportsRead = true;
        if (monthlyReports == null) {
            System.out.println("Необходимо считать месячные отчеты.");
            monthlyReportsRead = false;
        }
        return monthlyReportsRead;
    }

    private boolean checkAnnualReportRead() {
        boolean annualReportRead = true;
        if (annualReport == null) {
            System.out.println("Необходимо считать годовой отчет.");
            annualReportRead = false;
        }
        return annualReportRead;
    }

    private void printMonthlyInfo() {
        for (MonthlyReport monthlyReport : monthlyReports.getMonthlyReports()) {
            System.out.println("Месяц: " + Months.getName(monthlyReport.getMonth()) + "\n" +
                                "Самый прибыльный товар: " + "\n" +
                                "   Товар: " + monthlyReport.getMostProfitableProduct() + "\n" +
                                "   Сумма: " + monthlyReport.getMaxProfit() + "\n" +
                                "Самая большая трата: " + "\n" +
                                "   Товар: " + monthlyReport.getMaxExpenseProductName() + "\n" +
                                "   Сумма: " + monthlyReport.getSumMaxExpense() + "\n" +
                                "-------------------------");
        }
    }

    private void printAnnualInfo() {
        System.out.println("Год: " + annualReport.getYear() +
                "\nПрибыль по месяцам:");
        annualReport.printMonthlyProfit();
        System.out.printf("Средняя сумма расходов за год: %.2f" +
                "\nСредняя сумма доходов за год: %.2f\n", annualReport.getAverageExpense(), annualReport.getAverageIncome());
        System.out.println("-------------------------");
    }
}
