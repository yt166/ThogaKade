package Controller;

import db.DBConnection;
import dto.Customerdto;
import dto.tm.Customertm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerFormController {
    public TextField txtname;
    public TextField txtid;
    public TextField txtadd;
    public TextField txtsalary;
    public Button btnSave;
    public Button btnUpdate;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn coladd;
    public TableColumn colsalary;
    public Button btnReload;
    public Button btnBack;
    public TableView tblCustomer;
    public TableColumn coloption;

    public void initialize(){
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colname.setCellValueFactory(new PropertyValueFactory<>("name"));
            coladd.setCellValueFactory(new PropertyValueFactory<>("address"));
            colsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
            coloption.setCellValueFactory(new PropertyValueFactory<>("btn"));
            
            loadCustomer();
            tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
                setData((Customertm)newValue);
            } ));

    }

    private void setData(Customertm newValue) {
        if(newValue!=null) {
            txtid.setEditable(false);
            txtid.setText(newValue.getId());
            txtname.setText(newValue.getName());
            txtadd.setText(newValue.getAddress());
            txtsalary.setText(String.valueOf(newValue.getSalary()));
        }
    }

    private void loadCustomer() {
        ObservableList<Customertm> tmlist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Customer";

        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Button btn = new Button("Delete");
                Customertm cus = new Customertm(result.getString(1), result.getString(2), result.getString(3),result.getDouble(4),btn );

                btn.setOnAction(actionEvent -> {
                    deleteCustomer(cus.getId());
                });
                tmlist.add(cus);
            }
           tblCustomer.setItems(tmlist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteCustomer(String id) {
        String sql ="DELATE FROM Customer WHERE id='"+id+"'";
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            int result = statement.executeUpdate(sql);
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully");
            }else{
                new Alert(Alert.AlertType.WARNING,"Something went wrong");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!(txtid.getText().equals(null) && txtname.getText().equals(null) && txtadd.getText().equals(null) && txtsalary.getText().equals(null))) {
            Customerdto customerdto = new Customerdto(txtid.getText(), txtname.getText(), txtadd.getText(), Double.parseDouble(txtsalary.getText()));
            String sql = "INSERT INTO Customer VALUES (?,?,?,?)";

            try {
                PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, customerdto.getId());
                preparedStatement.setString(2, customerdto.getName());
                preparedStatement.setString(3, customerdto.getAddress());
                preparedStatement.setDouble(4, customerdto.getSalary());

                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully .....!").show();

                }else{
                    new Alert(Alert.AlertType.INFORMATION,"Somethig wents wrong...");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.INFORMATION, "Dulplicate..").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"EMPTY FEILDS..").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Customerdto customerdto = new Customerdto(txtid.getText(), txtname.getText(), txtadd.getText(), Double.parseDouble(txtsalary.getText()));
        String sql = "UPDATE Customer SET name ='"+customerdto.getName()+"', address ='"+customerdto.getAddress()+"', salary ='"+customerdto.getSalary()+"' WHERE id= '"+customerdto.getId()+"'";

        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            int result = statement.executeUpdate(sql);

            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated Successfully.. ").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnReloadOnAction(ActionEvent actionEvent) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }
}
