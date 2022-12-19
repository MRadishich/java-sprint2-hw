public class PartAnnualReport {
    final int MONTH;
    final int AMOUNT;
    final boolean IS_EXPENSE;

    public int getMonth() {
        return MONTH;
    }

    public int getAmount() {
        return AMOUNT;
    }

    public boolean isExpense() {
        return IS_EXPENSE;
    }

    public PartAnnualReport(int month, int amount, boolean isExpense) {
        this.MONTH = month;
        this.AMOUNT = amount;
        this.IS_EXPENSE = isExpense;
    }
}
