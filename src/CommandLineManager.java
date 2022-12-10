import java.time.LocalDateTime;
import java.util.Scanner;

public class CommandLineManager {

    private final Scanner in;

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
                            System.out.println("READ_MONTHLY_REPORTS");
                            break;
                        case READ_ANNUAL_REPORT:
                            System.out.println("READ_ANNUAL_REPORT");
                            break;
                        case VERIFY_REPORTS:
                            System.out.println("VERIFY_REPORTS");
                            break;
                        case DISPLAY_INFO_MONTHLY_REPORTS:
                            System.out.println("DISPLAY_INFO_MONTHLY_REPORTS");
                            break;
                        case DISPLAY_INFO_ANNUAL_REPORT:
                            System.out.println("DISPLAY_INFO_ANNUAL_REPORT");
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
        int hour = getLocalHour();
        if (hour >= 5 && hour <= 12) {
            System.out.println(Greetings.GOOD_MORNING.message);
        } else if (hour < 17) {
            System.out.println(Greetings.GOOD_AFTERNOON.message);
        } else if (hour < 21) {
            System.out.println(Greetings.GOOD_EVENING.message);
        } else {
            System.out.println(Greetings.GOOD_NIGHT.message);
        }
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
}
