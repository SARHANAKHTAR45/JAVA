package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    public boolean registerUser(String username, String plainPassword ){
        String hashedPassword=BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        String sql="INSERT INTO users (username, password_hash) VALUES(?,?)";
        try(
            Connection conn= DatabaseConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)
        ){
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            int rowsInserted=stmt.executeUpdate();
            return rowsInserted>0;
        }catch(SQLException e){
            System.out.println("Registration failed due to some error:"+e.getMessage());
            return false;
        }


    }

    public boolean loginUser(String username, String plainPassword){
        String sql="SELECT password_hash FROM users WHERE username=?";
        try(
            Connection conn=DatabaseConnection.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sql)
        ){
            stmt.setString(1, username);
            try(ResultSet rs= stmt.executeQuery()){
                if(!rs.next()){
                    return false;
                }
                 String storedHash=rs.getString("password_hash");
            return BCrypt.checkpw(plainPassword, storedHash);
            }
           
        }catch(SQLException e){
            System.out.println("Login check failed:"+e.getMessage());
            return false;
        }
    }
}
