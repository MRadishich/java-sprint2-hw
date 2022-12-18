import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthlyReportParser {

    MonthlyReports monthlyReports = new MonthlyReports();
    private ArrayList<Transaction> transactions;
    public void loadFile(int month, String path) {
        transactions = new ArrayList<>();
        List<String> lines = readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split(",");
            String itemName = line[0];
            boolean isExpense = Boolean.parseBoolean(line[1]);
            int quantity = Integer.parseInt(line[2]);
            int sumOfOne = Integer.parseInt(line[3]);

            saveTransaction(month, itemName, isExpense, quantity, sumOfOne);
        }
        saveMonthlyReport(month, transactions);
        System.out.println("Отчет за " + month + " месяц считан.");
    }

    private List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    private void saveTransaction(int month, String itemName, boolean isExpense, int quantity, int sumOfOne) {
        Transaction transaction = new Transaction(month, itemName, isExpense, quantity, sumOfOne);
        transactions.add(transaction);
    }

    private void saveMonthlyReport(int month, ArrayList<Transaction> transactions) {
        MonthlyReport monthlyReport = new MonthlyReport(month, transactions);
        monthlyReports.addMonthlyReport(monthlyReport);
    }

    public MonthlyReports getMonthlyReports() {
        return monthlyReports;
    }

}
