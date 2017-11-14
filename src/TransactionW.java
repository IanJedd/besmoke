package besmoke.src;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
public class TransactionW {

    private StringProperty sType;
    private DoubleProperty amount;
    private boolean isDeposit;

    public TransactionW(Transaction t) {
        sType = new SimpleStringProperty(t.getStringType());
        amount = new SimpleDoubleProperty(t.getAmount());
    }

    public boolean isDeposit() {
        return this.isDeposit;
    }

    public StringProperty sTypeProperty() { return sType; }
    public String getSType() { return sTypeProperty().get(); }

    public DoubleProperty amountProperty() { return amount; }
    public double getAmount() { return amountProperty().doubleValue(); }
}
