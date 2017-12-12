package BeFinanced;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Accounts extends RecursiveTreeObject<Accounts> {
    StringProperty id;
    StringProperty accountName;
    StringProperty grossBalance;
    StringProperty netBalance;
    StringProperty ccBalance;
    StringProperty umBalance;
    StringProperty benefitsBalance;

    public Accounts (String id, String accountName, String grossBalance, String netBalance, String ccBalance,
                     String umBalance, String benefitsBalance)
    {
        this.id = new SimpleStringProperty(id);
        this.accountName = new SimpleStringProperty(accountName);
        this.grossBalance = new SimpleStringProperty(grossBalance);
        this.netBalance = new SimpleStringProperty(netBalance);
        this.ccBalance = new SimpleStringProperty(ccBalance);
        this.umBalance = new SimpleStringProperty(umBalance);
        this.benefitsBalance = new SimpleStringProperty(benefitsBalance);
    }
}
