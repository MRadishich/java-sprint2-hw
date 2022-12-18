import java.util.HashMap;

public enum Months {
    JANUARY("Январь", 1),
    FEBRUARY("Февраль", 2),
    MARCH("Март", 3),
    APRIL("Апрель", 4),
    MAY("Май", 5),
    JUNE("Июнь", 6),
    JULY("Июль", 7),
    AUGUST("Август", 8),
    SEPTEMBER("Сентябрь", 9),
    OCTOBER("Октябрь", 10),
    NOVEMBER("Ноябрь", 11),
    DECEMBER("Декабрь", 12);

    private final String NAME;
    private final int NUMBER;
    private static final HashMap<Integer, String> months = new HashMap<>();

    Months(String name, int number) {
        this.NAME = name;
        this.NUMBER = number;
    }

    static {
        for (Months month : values()) {
            months.put(month.NUMBER, month.NAME);
        }
    }

    public static String getName(int number) {
        return months.get(number);
    }
}
