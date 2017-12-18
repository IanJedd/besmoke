package besmoke.src;
import java.util.*;
import java.io.*;
import java.io.Serializable;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
public class AccountW {

    private StringProperty name;
    private DoubleProperty bal;
    private StringProperty desc;
    private StringProperty phone;
    private StringProperty email;
    private DoubleProperty universityFees;
    private DoubleProperty creditCardFees;

    public AccountW(Account a) {
        name = a.accountNameProperty();
        bal = a.balanceProperty();
        desc = a.descriptionProperty();
        phone = a.phoneProperty();
        email = a.emailProperty();
        universityFees = a.universityFeesProperty();
        creditCardFees = a.creditCardFeesProperty();
    }

    public StringProperty nameProperty() { return name; }
    public String getName() { return nameProperty().get(); }

    public DoubleProperty balProperty() { return bal; }
    public double getBal() { return balProperty().doubleValue(); }

    public DoubleProperty universityFeesProperty() { return universityFees; }
    public double getUniversityFees() { return universityFeesProperty().doubleValue(); }

    public DoubleProperty creditCardFeesProperty() { return creditCardFees; }
    public double getCreditCardFees() { return creditCardFeesProperty().doubleValue(); }

    public StringProperty descProperty() { return desc; }
    public String getDesc() { return descProperty().get(); }

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phoneProperty().get(); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return emailProperty().get(); }

}
