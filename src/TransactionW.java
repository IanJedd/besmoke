package besmoke.src;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TransactionW {
    private static final Double FACULTY_BEN_PERCENT = 0.25;
    private static final Double STUDENT_BEN_PERCENT = 0.12;

    private StringProperty sType;
    private DoubleProperty amount;
    private DoubleProperty fees;
    private StringProperty desc;
    private StringProperty code;
    private boolean isDeposit;
    private IntegerProperty id;
    private StringProperty date;
    private StringProperty accountName;

    // Benefits Calculator
    private DoubleProperty facultyBen;
    private DoubleProperty studentBen;
    private DoubleProperty facultyNet;
    private DoubleProperty studentNet;

    public TransactionW(Transaction t) {
        sType = new SimpleStringProperty(t.getStringType());
        amount = new SimpleDoubleProperty(t.getAmount());
        fees = new SimpleDoubleProperty(t.getFees());
        desc = new SimpleStringProperty(t.getDescription());
        code = new SimpleStringProperty(t.getCode());
        id = new SimpleIntegerProperty(t.getID());
        date = new SimpleStringProperty(t.getStringDate());
        accountName = new SimpleStringProperty(t.getAccountName());
        
        // Benefits Calculator
        double fb = t.getAmount()*FACULTY_BEN_PERCENT;
        double sb = t.getAmount()*STUDENT_BEN_PERCENT;
        facultyBen = new SimpleDoubleProperty(fb);
        studentBen = new SimpleDoubleProperty(sb);
        facultyNet = new SimpleDoubleProperty(t.getAmount()-fb);
        studentNet = new SimpleDoubleProperty(t.getAmount()-sb);
    }

    public boolean isDeposit() {
        return this.isDeposit;
    }

    public StringProperty accountNameProperty() { return accountName; }
    public String getAccountName() { return accountNameProperty().get(); }

    public StringProperty codeProperty() { return code; }
    public String getCode() { return codeProperty().get(); }

    public StringProperty dateProperty() { return date; }
    public String getDate() { return dateProperty().get(); }

    public StringProperty sTypeProperty() { return sType; }
    public String getSType() { return sTypeProperty().get(); }

    public StringProperty descProperty() { return desc; }
    public String getDesc() { return descProperty().get(); }

    public DoubleProperty feesProperty() { return fees; }
    public Double getFees() { return feesProperty().doubleValue(); }

    public DoubleProperty amountProperty() { return amount; }
    public double getAmount() { return amountProperty().doubleValue(); }

    public IntegerProperty idProperty() { return id; }
    public Integer getId() { return idProperty().get(); }
}
