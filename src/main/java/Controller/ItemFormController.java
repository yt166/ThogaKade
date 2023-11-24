package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import db.DBConnection;
import dto.Itemdto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import dto.tm.Itemtm;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemFormController {

    @FXML
    private BorderPane itemPane;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDes;

    @FXML
    private JFXTextField txtPrize;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    private TreeTableColumn colCode;

    @FXML
    private TreeTableColumn colDes;

    @FXML
    private TreeTableColumn colPrize;

    @FXML
    private TreeTableColumn colQty;

    @FXML
    private TreeTableColumn colOption;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTreeTableView<Itemtm> tblItem;

    public void initialize(){
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDes.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        colPrize.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrize"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadTable();
    }

    @SneakyThrows
    private void loadTable() {
        ObservableList<Itemtm> tmList = FXCollections.observableArrayList();
        String sql ="SELECT * FROM item";

        Statement stm = null;

            stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = stm.executeQuery(sql);

            while(result.next()){
                JFXButton btn = new JFXButton("Delete");
                Itemtm itm = new Itemtm(result.getString(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        btn
                );
                btn.setOnAction(actionEvent -> {
                    try {
                        deleteItem(itm.getCode());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                tmList.add(itm);
            }
        TreeItem<Itemtm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);
    }

    private void deleteItem(String code) throws SQLException {
        String sql = "DELETE FROM item WHERE id =?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1,code);
            int result = preparedStatement.executeUpdate(sql);
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted....!").show();
                loadTable();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnBackOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) itemPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ThogaKadeForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.show();
    }

    public void btnSaveOnAction(javafx.event.ActionEvent actionEvent) {
        Itemdto item = new Itemdto(txtCode.getText(),txtDes.getText(),Double.parseDouble(txtPrize.getText()),Integer.parseInt(txtQty.getText()));
        String sql = "INSERT INTO item VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1,item.getCode());
            preparedStatement.setString(2,item.getDescription());
            preparedStatement.setDouble(3,item.getUnitprize());
            preparedStatement.setInt(4,item.getQty());

            int result = preparedStatement.executeUpdate();
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved...!").show();
            }
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(javafx.event.ActionEvent actionEvent) {

    }
}
