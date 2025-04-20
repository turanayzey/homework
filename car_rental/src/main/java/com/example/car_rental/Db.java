package com.example.car_rental;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Db {
    private static final Logger logger = Logger.getLogger(Db.class.getName());
    private static final  String DB_DRIVER="org.postgresql.Driver";
    private static final  String DB_URL="jdbc:postgresql://127.0.0.1:5432/car_rental_db";
    private static final String DB_USER="postgres";
    private static final String DB_PASS="Mkc1508156";

    public Db(){}
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        try {
            conn=DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        return conn;
    }
}
