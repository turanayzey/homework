package com.example.car_rental;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CarsController {
    private  static final Logger logger = Logger.getLogger(CarsController.class.getName());
    @FXML
    private Button clear;
    @FXML
    private Button close;
    @FXML
    private TextField car_no;
    @FXML
    private TextField car_type;
    @FXML
    private TextField car_brand;
    @FXML
    private TextField car_model;
    @FXML
    private TextField car_color;

    @FXML
    void close(javafx.event.ActionEvent event) {Platform.exit();}
    @FXML
    void clear(javafx.event.ActionEvent event) {
        car_no.setText("");
        car_brand.setText("");
        car_model.setText("");
        car_color.setText("");
        car_type.setText("");
    }
    @FXML
    void GetCarNo(javafx.event.ActionEvent event){
        if(car_no.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Vehicle number!");
            alert.showAndWait();
        }
        else{
            int car_no=Integer.parseInt(this.car_no.getText());
            CarsDAO carsDAO=new CarsDAO();
            try {
                Cars car=CarsDAO.findById(car_no);
                if(car==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Vehicle not found!");
                    alert.showAndWait();
                }else {
                    car_brand.setText(car.getCar_brand());
                    car_model.setText(car.getCar_model());
                    car_color.setText(car.getCar_color());
                    car_type.setText(car.getCar_type());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Vehicle found successfully!");
                    alert.showAndWait();

                }




            } catch (SQLException e) {
                logger.log(Level.SEVERE,e.getMessage());
            }

        }


    }
    @FXML
    void save(javafx.event.ActionEvent event) {
        Integer carno=0;
        String carbrand=null,carmodel=null,carcolor=null,cartype=null;
        if (car_no.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle number!");
            alert.show();
        }else{
            carno=Integer.parseInt(car_no.getText());
            CarsDAO carsDAO=new CarsDAO();
            try {
                if(carsDAO.findById(carno)!=null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Vehicle already exists!");
                    alert.show();
                    return;
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE,e.getMessage());
            }
        }
        if(car_brand.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle brand!");
            alert.show();
        } else {
            carbrand = car_brand.getText();
        }
        if(car_model.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle model!");
            alert.show();
        } else {
            carmodel = car_model.getText();
        }if(car_color.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle color!");
            alert.show();
        } else {
            carcolor = car_color.getText();
        }if(car_type.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle type!");
            alert.show();
        } else {
            cartype = car_type.getText();
        }
        CarsDAO carsDAO=new CarsDAO();
        Cars car=new Cars(carno,carbrand,carmodel,carcolor,cartype);
        try {
            int id=carsDAO.save(car);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vehicle saved successfully!");
            alert.show();
            logger.log(Level.INFO,"vehicle saved,id"+id);
        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
    @FXML
    void delete(javafx.event.ActionEvent event){
        int carno;
        if (car_no.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle number!");
            alert.show();
        }else {
            carno = Integer.parseInt(car_no.getText());
            CarsDAO carsDAO = new CarsDAO();
            try {
                carsDAO.delete(carno);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Vehicle deleted successfully!!");
                alert.show();
                this.clear(null);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
    @FXML
    void update(javafx.event.ActionEvent event){
        Integer carno=0;
        String carbrand=null,carmodel=null,carcolor=null,cartype=null;


        if(car_no.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle number!");
            alert.show();
        } else {
            carno = Integer.parseInt(car_no.getText());
        }
        if(car_brand.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle brand!");
            alert.show();
        } else {
            carbrand = car_brand.getText();
        }
        if(car_model.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle model!");
            alert.show();
        } else {
            carmodel = car_model.getText();
        }
        if(car_color.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle color!");
            alert.show();
        } else {
            carcolor = car_color.getText();
        }
        if(car_type.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter vehicle type!");
            alert.show();
        } else {
            cartype = car_type.getText();
        }
        CarsDAO carsDAO = new CarsDAO();
        Cars car = new Cars(carno,carbrand,carmodel, carcolor, cartype);
        try {
            int rc = carsDAO.update(car);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vehicle updated successfully!!");
            alert.show();
            if(rc > 0) {
                logger.log(Level.INFO, "Updated row count="+rc);
            } else if(rc == 0){
                logger.log(Level.SEVERE, "No update occurred.");
            }
        } catch (SQLException e){
            logger.log(Level.SEVERE, e.getMessage());
        }

    }
}
