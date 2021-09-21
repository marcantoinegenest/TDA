import java.util.Map;

public class Broker {

    private static final double SMALL_FEES = 0.05;
    private static final double BIG_FEES = 0.15;

    private final Map<String, Wallet> wallets;

    public Broker(Map<String, Wallet> wallets) {
        this.wallets = wallets;
    }

    public void buyStock(String customerNo, Stock stock, Integer quantity) {
        Wallet wallet = wallets.get(customerNo);

        double price = getPrice(stock, quantity);
        wallet.buyStock(stock, price, quantity);
    }

    private double getPrice(Stock stock, Integer quantity) {
        double fees = calculateFees(quantity);
        return stock.calculatePriceForQuantity(quantity) + fees;
    }

    public void sellStock(String customerNo, Stock stock, int quantity) {
        Wallet wallet = wallets.get(customerNo);

        double profit = getProfit(stock, quantity);
        wallet.sellStocks(stock, profit, quantity);
    }

    private double getProfit(Stock stock, int quantity) {
        double fees = calculateFees(quantity);
        return stock.getCurrentPrice() * quantity - fees;
    }

    private double calculateFees(Integer quantity) {
        if (quantity < 100) {
            return BIG_FEES * quantity;
        } else {
            return SMALL_FEES * quantity;
        }
    }
}