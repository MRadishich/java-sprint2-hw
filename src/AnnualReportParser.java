import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AnnualReportParser {
    private final String PATH;
    private final ArrayList<PartAnnualReport> PARTS_ANNUAL_REPORT = new ArrayList<>();
    private AnnualReport annualReport;

    public AnnualReportParser(String path) {
        this.PATH = path;
    }

    public void parseAnnualReport() {

        List<String> lines = readFileContents(PATH);
        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split(",");
            int month = Integer.parseInt(line[0]);
            int amount = Integer.parseInt(line[1]);
            boolean isExpense = Boolean.parseBoolean(line[2]);

            savePartAnnualReport(month, amount, isExpense);
        }
        saveAnnualReport(PARTS_ANNUAL_REPORT);
        System.out.println("Годовой отчет считан.");
    }

    private List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    private void savePartAnnualReport(int month, int amount, boolean isExpense) {
        PartAnnualReport partAnnualReport = new PartAnnualReport(month, amount, isExpense);
        PARTS_ANNUAL_REPORT.add(partAnnualReport);
    }

    private void saveAnnualReport(ArrayList<PartAnnualReport> partsAnnualReport) {
        annualReport = new AnnualReport(findYear(PATH));
        for (PartAnnualReport partAnnualReport : partsAnnualReport) {
            HashMap<Boolean, Integer> monthlyExpensesOrIncome = annualReport.getAnnualReport().get(partAnnualReport.MONTH);
            if (monthlyExpensesOrIncome == null) {
                monthlyExpensesOrIncome = new HashMap<>();
            }
            monthlyExpensesOrIncome.put(partAnnualReport.isExpense(), partAnnualReport.getAmount());
            annualReport.getAnnualReport().put(partAnnualReport.getMonth(), monthlyExpensesOrIncome);
        }
    }

    public AnnualReport getAnnualReport() {
        return annualReport;
    }

    public int findYear(String path) {
        int year = 0;
        try {
            year = Integer.parseInt(path.substring(path.length() - 8, path.length() - 4));
        } catch (NumberFormatException e) {
             System.out.println("Не удалось определить год отчета, за который необходимо считать данные. " +
                    "Название файла должно соответствовать формату \"y.ГОД.csv\"");
        }
        return year;
    }
}
