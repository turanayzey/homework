package com.example.car_rental;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriversController {
    private  static final Logger logger = Logger.getLogger(DriversController.class.getName());
    @FXML
    private Button clear;
    @FXML
    private Button close;
    @FXML
    private TextField D_id;
    @FXML
    private TextField D_name;
    @FXML
    private TextField D_address;
    @FXML
    private TextField D_phone;
    @FXML
    private Button toVehicles;


    @FXML
    void clear(javafx.event.ActionEvent event) {
        D_id.setText("");
        D_name.setText("");
        D_address.setText("");
        D_phone.setText("");

    }
    @FXML
    void GetDriverNo(javafx.event.ActionEvent event){
        if(D_id.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Vehicle number!");
            alert.showAndWait();
        }
        else{
            int driver_no=Integer.parseInt(this.D_id.getText());
            CarsDAO carsDAO=new CarsDAO();
            try {
                Drivers drivers= DriversDAO.findById(driver_no);
                if (drivers==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Driver not found!");
                    alert.showAndWait();
                }else {
                    D_name.setText(drivers.getD_name());
                    D_address.setText(drivers.getD_address());
                    D_phone.setText(drivers.getD_phone());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Driver found successfully!");
                    alert.showAndWait();

                }
            }
            catch (SQLException e){
                logger.log(Level.SEVERE,e.getMessage());

            }
        }
    }
    @FXML
    void save(javafx.event.ActionEvent event) {
        Integer driverno=0;
        String drivername=null,driveraddress=null,driverphone=null;
        if (D_id.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter drivers number!");
            alert.show();
        }else{
            driverno=Integer.parseInt(D_id.getText());
            DriversDAO driversDAO=new DriversDAO();
            try{
                if (driversDAO.findById(driverno)!=null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Driver already exists!");
                    alert.show();
                    return;
                }
            }
            catch (SQLException e){
                logger.log(Level.SEVERE,e.getMessage());
            }
        }
        if (D_name.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver name!");
            alert.show();
        }else{
            drivername=D_name.getText();
        }
        if (D_address.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver address!");
        }
        else{
            driveraddress=D_address.getText();
        }
        if (D_phone.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver phone!");
            alert.show();
        }
        else{
            driverphone=D_phone.getText();
        }
        Drivers drivers=new Drivers(driverno,drivername,driveraddress,driverphone);
        DriversDAO driversDAO=new DriversDAO();
        try {
            int id=driversDAO.save(drivers);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Driver saved successfully!");
            alert.show();
            logger.log(Level.INFO,"driver saved,id"+id);
        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
    @FXML
    void delete(javafx.event.ActionEvent event){
        int driverno;
        if (D_id.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver number!");
            alert.show();
        }else {
            driverno = Integer.parseInt(D_id.getText());
            DriversDAO driversDAO = new DriversDAO();
            try {
                driversDAO.delete(driverno);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Driver deleted successfully!!");
                alert.show();
                this.clear(null);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
    @FXML
    void update(javafx.event.ActionEvent event){
        Integer driverno=0;
        String drivername=null,driveraddress=null,driverphone=null;
        if(D_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver number!");
            alert.show();
        }
        else{
            driverno = Integer.parseInt(D_id.getText());
        }
        if(D_name.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver name!");
            alert.show();
        }
        else{
            drivername = D_name.getText();
        }
        if(D_address.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver address!");
            alert.show();
        }
        else{
            driveraddress = D_address.getText();
        }
        if(D_phone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter driver phone!");
            alert.show();
        }
        else {
            driverphone = D_phone.getText();
        }
        DriversDAO driversDAO = new DriversDAO();
        Drivers drivers = new Drivers(driverno,drivername,driveraddress,driverphone);
        try {
            int rc = driversDAO.update(drivers);
            if(rc > 0) {
                logger.log(Level.INFO, "Updated row count="+rc);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Driver updated successfully!");
                alert.show();
            } else if(rc == 0){
                logger.log(Level.SEVERE, "No update occurred.");
            }
        } catch (SQLException e){
            logger.log(Level.SEVERE, e.getMessage());
        }


    }
    @FXML
    public void toVehicles() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/car_rental/vehicles-view.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 720, 400);


        Stage stage = (Stage) toVehicles.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();

    }

}
