package com.designpatterns.singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection = null; // Lazy loading
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private Connection connection;


    private DBConnection(){
        initialConnecion();
    }

    public static DBConnection getDbConnection(){
        if(dbConnection == null){
            synchronized (DBConnection.class){ // Thread safe
                if(dbConnection == null)
                    dbConnection = new DBConnection();
            }
        }
        return dbConnection;
    }

    private void initialConnecion() {
        try{
            Class.forName(DB_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public Connection getConnection(){
        return connection;
    }
}
