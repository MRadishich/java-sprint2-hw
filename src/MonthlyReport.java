import java.util.ArrayList;

public class MonthlyReport {
    private final int MONTH;
    private final ArrayList<Transaction> TRANSACTION;

    public int getMonth() {
        return MONTH;
    }

    public MonthlyReport(int month, ArrayList<Transaction> transactions) {
        this.MONTH = month;
        this.TRANSACTION = transactions;
    }

    public int getSumExpenses() {
        int sumExpenses = 0;
        for (Transaction transaction : TRANSACTION) {
            sumExpenses += transaction.isExpense ? transaction.sumOfOne * transaction.quantity : 0;
        }
        return sumExpenses;
    }

    public int getSumIncome() {
        int sumIncome = 0;
        for (Transaction transaction : TRANSACTION) {
            sumIncome += !transaction.isExpense ? transaction.sumOfOne * transaction.quantity : 0;
        }
        return sumIncome;
    }

    public String getMostProfitableProduct() {
        int maxProfit = 0;
        String product = "";
        for (Transaction transaction : TRANSACTION) {
            if (!transaction.isExpense && (transaction.quantity * transaction.sumOfOne > maxProfit)) {
                maxProfit = transaction.quantity * transaction.sumOfOne;
                product = transaction.itemName;
            }
        }
        return product;
    }

    public int getMaxProfit() {
        int maxProfit = 0;
        for (Transaction transaction : TRANSACTION) {
            if (!transaction.isExpense) {
                maxProfit = Math.max(transaction.quantity * transaction.sumOfOne, maxProfit);
            }
        }
        return maxProfit;
    }

    public String getMaxExpenseProductName() {
        int maxExpense = 0;
        String product = "";
        for (Transaction transaction : TRANSACTION) {
            if (transaction.isExpense && (transaction.quantity * transaction.sumOfOne > maxExpense)) {
                maxExpense = transaction.quantity * transaction.sumOfOne;
                product = transaction.itemName;
            }
        }
        return product;
    }

    public int getSumMaxExpense() {
        int maxExpense = 0;
        for (Transaction transaction : TRANSACTION) {
            if (transaction.isExpense) {
                maxExpense = Math.max(transaction.quantity * transaction.sumOfOne, maxExpense);
            }
        }
        return maxExpense;
    }
}
