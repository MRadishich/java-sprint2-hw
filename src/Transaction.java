public class Transaction {
    int month;
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public Transaction(int month, String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.month = month;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
