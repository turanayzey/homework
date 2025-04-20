package com.example.car_rental;

public class Cars {
    private int car_no;
    private String car_brand;
    private String car_model;
    private String car_color;
    private String car_type;
    public Cars(){}
    public  Cars(int car_no,String car_brand,String car_model,String car_color,String car_type){
        this.car_no=car_no;
        this.car_brand=car_brand;
        this.car_model=car_model;
        this.car_color=car_color;
        this.car_type=car_type;
    }
    public int getCar_no() {
        return car_no;
    }
    public void setCar_no(int car_no) {this.car_no = car_no;}
    public String getCar_brand() {
        return car_brand;
    }
    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }
    public String getCar_model() {
        return car_model;
    }
    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }
    public String getCar_color() {
        return car_color;
    }
    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }
    public String getCar_type() {
        return car_type;
    }
    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }
    @Override
    public String toString() {
        return "Cars{" +
                "car_no=" + car_no +
                ", car_brand='" + car_brand + '\'' +
                ", car_model='" + car_model + '\'' +
                ", car_color='" + car_color + '\'' +
                ", car_type='" + car_type + '\'' +
                '}';
    }
}

