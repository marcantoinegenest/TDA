import java.util.Map;

public class Wallet {

    private final Map<String, Integer> stocks;
    private double balance;

    public Wallet(Map<String, Integer> stocks, double balance) {
        this.stocks = stocks;
        this.balance = balance;
    }

    public void buyStock(Stock stock, double price, Integer quantity) {
        if (price > this.balance) {
            throw new RuntimeException("Not enough funds");
        } else {
            this.balance -= price;
            addStock(stock, quantity);
        }
    }

    public void addStock(Stock stock, Integer quantity) {
        this.stocks.merge(stock.getSymbol(), quantity, Integer::sum);
    }

    public void sellStocks(Stock stock, double profit, int quantity) {
        if (profit < 0 && Math.abs(profit) > this.balance) {
            throw new RuntimeException("Not enough funds");
        } else if (quantity > getStockQuantity(stock)) {
            throw new RuntimeException("Not enough funds");
        } else {
            this.balance += profit;
            removeStock(stock, quantity);
        }
    }

    public Integer getStockQuantity(Stock stock) {
        return this.stocks.get(stock.getSymbol());
    }

    public void removeStock(Stock stock, Integer quantity) {
        this.stocks.put(stock.getSymbol(), this.getStockQuantity(stock) - quantity);
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
