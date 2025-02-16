package OOP;

public class logistic {
    private int logistiu_ID;
    private String item;
    private int quantity;
    private int cost;

    public logistic(int logistiu_ID, String item, int quantity, int cost) {
        this.logistiu_ID = logistiu_ID;
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getLogistiu_ID() {
        return logistiu_ID;
    }

    public void setLogistiu_ID(int logistiu_ID) {
        this.logistiu_ID = logistiu_ID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
