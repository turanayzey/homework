package com.example.car_rental;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DriversDAO {
    private static final Logger logger = Logger.getLogger(DriversDAO.class.getName());
    public static Drivers findById (int D_id) throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Drivers driver=null;
        try {
            conn=Db.getConnection();
            String query="SELECT * FROM drivers WHERE D_id=?";
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,D_id);
            rs=pstmt.executeQuery();
            while (rs.next()){
                driver=new Drivers();
                driver.setD_id(rs.getInt(1));
                driver.setD_name(rs.getString(2));
                driver.setD_address(rs.getString(3));
                driver.setD_phone(rs.getString(4));



            }
        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }finally {
            if(rs!=null)rs.close();
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
        }
        return driver;
    }
    public int save(Drivers driver)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            conn=Db.getConnection();
            conn.setAutoCommit(false);
            String query="INSERT INTO drivers(D_id,D_name,D_address,D_phone) VALUES(?,?,?,?)";
            pstmt=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int i=1;
            pstmt.setInt(i++,driver.getD_id());
            pstmt.setString(i++,driver.getD_name());
            pstmt.setString(i++,driver.getD_phone());
            pstmt.setString(i++,driver.getD_address());

            pstmt.executeUpdate();
            conn.commit();
            rs=pstmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }else {
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
    public int update(Drivers driver)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            conn=Db.getConnection();
            conn.setAutoCommit(false);
            String query="UPDATE drivers SET D_name=?,D_address=?,D_phone=? WHERE D_id=?";
            pstmt=conn.prepareStatement(query);
            int i=1;
            pstmt.setString(i++,driver.getD_name());
            pstmt.setString(i++,driver.getD_phone());
            pstmt.setString(i++,driver.getD_address());

            pstmt.setInt(i++,driver.getD_id());
            int rc=pstmt.executeUpdate();
            conn.commit();
            return rc;
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
    public int delete(int id)throws SQLException{
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn=Db.getConnection();
            String query="DELETE FROM drivers WHERE D_id=?";
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate()>0){
                logger.log(Level.INFO,"Record deleted: " + id);
            }else{
                logger.log(Level.INFO,"Record not deleted: " + id);
            }
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
        return 0;
    }
}
