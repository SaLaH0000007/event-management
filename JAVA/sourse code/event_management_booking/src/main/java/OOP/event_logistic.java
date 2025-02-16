package OOP;

public class event_logistic {
   private int logistiu_ID;
   private int  event_id;
   private int quantity;

    public event_logistic(int logistiu_ID, int event_id, int quantity) {
        this.logistiu_ID = logistiu_ID;
        this.event_id = event_id;
        this.quantity = quantity;
    }

    public int getLogistiu_ID() {
        return logistiu_ID;
    }

    public void setLogistiu_ID(int logistiu_ID) {
        this.logistiu_ID = logistiu_ID;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
