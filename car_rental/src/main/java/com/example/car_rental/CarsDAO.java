package com.example.car_rental;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarsDAO {
    private static final Logger logger = Logger.getLogger(CarsDAO.class.getName());
    public static Cars findById(int car_no)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Cars car=null;
        try {
            conn=Db.getConnection();
            String query="SELECT * FROM cars WHERE car_no=?";
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,car_no);
            rs=pstmt.executeQuery();
            while (rs.next()){
                car=new Cars();
                car.setCar_no(rs.getInt(1));
                car.setCar_model(rs.getString(2));
                car.setCar_brand(rs.getString(3));
                car.setCar_color(rs.getString(4));
                car.setCar_type(rs.getString(5));


            }
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }finally{
            if(rs!=null)rs.close();
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
        }
        return car;

    }
    public int save(Cars car)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            conn=Db.getConnection();
            conn.setAutoCommit(false);
            String query="INSERT INTO cars(car_no,car_model,car_brand,car_color,car_type) VALUES(?,?,?,?,?)";
            pstmt=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int i=1;
            pstmt.setInt(i++,car.getCar_no());
            pstmt.setString(i++,car.getCar_brand());
            pstmt.setString(i++,car.getCar_model());
            pstmt.setString(i++,car.getCar_color());
            pstmt.setString(i++,car.getCar_type());
            pstmt.executeUpdate();
            conn.commit();
            rs=pstmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
            else{
                return 0;
            }

        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
            if(conn!=null)conn.rollback();
        }finally {
            if(rs!=null)rs.close();
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
        }
        return 0;
    }
    public int update(Cars car)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            conn=Db.getConnection();
            conn.setAutoCommit(false);
            String query="UPDATE cars SET car_brand=?,car_model=?,car_color=?,car_type=? WHERE car_no=?";
            pstmt=conn.prepareStatement(query);
            int i=1;
            pstmt.setString(i++,car.getCar_model());
            pstmt.setString(i++,car.getCar_brand());
            pstmt.setString(i++,car.getCar_color());
            pstmt.setString(i++,car.getCar_type());
            pstmt.setInt(i++,car.getCar_no());
            int rc=pstmt.executeUpdate();
            conn.commit();
            return rc;
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
            if(conn!=null)conn.rollback();
        }finally {
            if(rs!=null)rs.close();
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
        }
        return 0;
    }
    public void delete(int car_no)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;

        try {
            conn=Db.getConnection();

            String query="DELETE FROM cars WHERE car_no=?";
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,car_no);
            if(pstmt.executeUpdate()>0){
                logger.log(Level.INFO,"Record deleted: " + car_no);

            }
            else {
                logger.log(Level.INFO,"Record not deleted: " + car_no);
            }


        }
        catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }

    }
}
