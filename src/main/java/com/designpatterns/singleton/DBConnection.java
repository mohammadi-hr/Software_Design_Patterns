package com.designpatterns.singleton;

public class DBConnection {

    private static DBConnection dbConnection = null;

    private DBConnection(){

    }

    public static DBConnection getDbConnection(){
        if(dbConnection == null){
            synchronized (DBConnection.class){
                if(dbConnection == null)
                    dbConnection = new DBConnection();
            }
        }
        return dbConnection;
    }
}
