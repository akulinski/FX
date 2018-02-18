package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.sql.*;

public class Controller  {

    private  static Connection conn;
    private static Statement smt;

    public static boolean login(String userName,String password){
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:test.db";
            conn = DriverManager.getConnection(url);

            System.out.println("connected");
            conn.setAutoCommit(false);


            smt = conn.createStatement();

            ResultSet rs;

            String sql = "SELECT COUNT(*) AS TOTAL FROM USER WHERE USERNAME=? AND PASSWORD=?;";
            PreparedStatement prep=conn.prepareStatement(sql);
            prep.setString(1,userName);
            prep.setString(2,password);
            rs = prep.executeQuery();
            System.out.println("QUERY EXECUTED");

            int count = 0;

            count = rs.getInt("TOTAL");
            System.out.println(count);


            if (count > 0) {
                return true;
            } else {
                return false;
            }

        }
        catch (SQLException e) {

            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void createDataBase(){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE USER " +
                    "('USERNAME' TEXT PRIMARY KEY NOT NULL ," +
                    " 'PASSWORD');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();Controller.createDataBase();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void CreateNewUser(String userName,String password){
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:test.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(true);
            smt = conn.createStatement();
            String sql = "INSERT INTO main.USER VALUES(?,?);";
            PreparedStatement prep=conn.prepareStatement(sql);
            prep.setString(1,userName);
            prep.setString(2,password);
            prep.executeUpdate();

            System.out.println("QUERY EXECUTED");
        }
        catch (SQLException e) {

            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}





