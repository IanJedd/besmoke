package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController {
    public Label title;

    public JFXTreeTableView<Accounts> table;

    public JFXTreeTableColumn<Accounts, String> id;
    public JFXTreeTableColumn<Accounts, String> accountName;
    public JFXTreeTableColumn<Accounts, String> grossBalance;
    public JFXTreeTableColumn<Accounts, String> netBalance;
    public JFXTreeTableColumn<Accounts, String> ccBalance;
    public JFXTreeTableColumn<Accounts, String> umBalance;
    public JFXTreeTableColumn<Accounts, String> benefitsBalance;

    public JFXButton transactionsButton;
    public JFXButton deleteAccountButton;
    public JFXButton newButton;
    public JFXButton detailsButton;
    public JFXButton backButton;

    {
        JFXTreeTableColumn<Accounts, String> id = new JFXTreeTableColumn<>("ID");
        id.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
                {
                    if (id.validateValue(param)) {
                        return param.getValue().getValue().id;
                    } else {
                        return id.getComputedValue(param);
                    }
                });

        JFXTreeTableColumn<Accounts, String> accountName = new JFXTreeTableColumn<>("Account Name");
        accountName.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
                {
                    if (accountName.validateValue(param)) {
                        return param.getValue().getValue().accountName;
                    } else {
                        return accountName.getComputedValue(param);
                    }
                });

        JFXTreeTableColumn<Accounts, String> grossBalance = new JFXTreeTableColumn<>("Gross Balance");
        id.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
                {
                    if (grossBalance.validateValue(param)) {
                        return param.getValue().getValue().grossBalance;
                    } else {
                        return grossBalance.getComputedValue(param);
                    }
                });
        JFXTreeTableColumn<Accounts, String> netBalance = new JFXTreeTableColumn<>("Net Balance");
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
        {
            if (netBalance.validateValue(param)) {
                return param.getValue().getValue().netBalance;
            } else {
                return netBalance.getComputedValue(param);
            }
        });
        JFXTreeTableColumn<Accounts, String> ccBalance = new JFXTreeTableColumn<>("CC Balance");
        id.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
                {
                    if (ccBalance.validateValue(param)) {
                        return param.getValue().getValue().ccBalance;
                    } else {
                        return ccBalance.getComputedValue(param);
                    }
                });
        JFXTreeTableColumn<Accounts, String> umBalance = new JFXTreeTableColumn<>("UM Balance");
        umBalance.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
                {
                    if (umBalance.validateValue(param)) {
                        return param.getValue().getValue().umBalance;
                    } else {
                        return umBalance.getComputedValue(param);
                    }
                });
        JFXTreeTableColumn<Accounts, String> benefitsBalance = new JFXTreeTableColumn<>("Benefits Balance");
        umBalance.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Accounts, String> param) ->
                {
                    if (id.validateValue(param)) {
                        return param.getValue().getValue().id;
                    } else {
                        return id.getComputedValue(param);
                    }
                });
        ObservableList<Accounts> accounts = FXCollections.observableArrayList();
        accounts.add(new Accounts("0", "Test Account","$100", "50", "4",
                "8", "25"));

        final TreeItem<Accounts> root = new RecursiveTreeItem<>(accounts, RecursiveTreeObject::getChildren);

        JFXTreeTableView<Accounts> treeView = new JFXTreeTableView<>(root);
        treeView.setShowRoot(false);
        treeView.setEditable(false);
        treeView.getColumns().setAll(id, accountName, grossBalance, netBalance, ccBalance, umBalance, benefitsBalance);

        FlowPane main = new FlowPane();
        main.setPadding(new Insets(10));
        main.getChildren().add(treeView);
    }

    public void back() throws Exception {
        View.update("Login");
    }

    public void newAccount() throws Exception {
        View.update("AccountDetails");
    }

    public void viewAccount() throws Exception {
        View.update("AccountDetails");
    }

    public void deleteAccount() throws Exception {
        View.save("Accounts");
        View.update("Delete");
    }

    public void viewTransactions() throws Exception {
        View.update("Transactions");
    }

}
