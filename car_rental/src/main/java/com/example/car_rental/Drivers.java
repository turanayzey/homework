package com.example.car_rental;

public class Drivers {
    private int D_id;
    private String D_name;
    private String D_phone;
    private String D_address;
    public Drivers(){}
    public Drivers(int D_id, String D_name, String D_phone, String D_address) {
        this.D_id = D_id;
        this.D_name = D_name;
        this.D_phone = D_phone;
        this.D_address = D_address;
    }
    public int getD_id() {
        return D_id;
    }
    public void setD_id(int D_id) {
        this.D_id = D_id;
    }
    public String getD_name() {
        return D_name;
    }
    public void setD_name(String D_name) {
        this.D_name = D_name;
    }
    public String getD_phone() {
        return D_phone;
    }
    public void setD_phone(String D_phone) {
        this.D_phone = D_phone;
    }
    public String getD_address() {
        return D_address;
    }
    public void setD_address(String D_address) {
        this.D_address = D_address;
    }
    @Override
    public String toString() {
        return "Drivers{" +
                "D_id=" + D_id +
                ", D_name='" + D_name + '\'' +
                ", D_address='" + D_address + '\'' +
                ", D_phone='" + D_phone + '\'' +
                '}';
    }

}
