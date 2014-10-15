package Bibliotheque.Connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Gael on 15/10/2014.
 */


public class Connexion {


    public static Connection connexion(){
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/Bibliotheque","root","root");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;





    }







    }


