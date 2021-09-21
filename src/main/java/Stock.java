public class Stock {

    private final String symbol;
    private final double currentPrice;

    public Stock(String symbol, double currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getCurrentPrice() {
        return this.currentPrice;
    }

    public double calculatePriceForQuantity(Integer quantity) {
        return currentPrice * quantity;
    }
}