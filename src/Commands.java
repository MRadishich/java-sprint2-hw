public enum Commands {
    READ_MONTHLY_REPORTS,
    READ_ANNUAL_REPORT,
    VERIFY_REPORTS,
    DISPLAY_INFO_MONTHLY_REPORTS,
    DISPLAY_INFO_ANNUAL_REPORT,
    EXIT,
    INCORRECT_COMMAND;

    public static Commands findCommand(int index) {
        for (Commands command : values()) {
            if (command.ordinal() == index) {
                return command;
            }
        }
        return Commands.INCORRECT_COMMAND;
    }
}
