package com.designpatterns.service;
import com.designpatterns.singleton.DBConnection;
import org.junit.Assert;

import java.sql.*;

public class DBConnectionTest {

    @org.junit.Test
    public void getInstance(){

//        -----------------------------
//        create a single connection into the database
//        -----------------------------


        DBConnection dbConnection = DBConnection.getDbConnection();
        Connection connection = dbConnection.getConnection();

//        ------------------------------
//        create database in memory using h2
//        ------------------------------

        String createTableQuery = "CREATE TABLE bank_accounts(id INT PRIMARY KEY, balance DOUBLE, type VARCHAR(20), status BOOLEAN) ";

        try(Statement statement = connection.createStatement();){
            statement.execute(createTableQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }

//        ------------------------------
//        insert 2 records into the database table
//        ------------------------------

        String insertQuery = "INSERT INTO bank_accounts (id, balance, type, status) VALUES (? ,? ,? ,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);){

            preparedStatement.setInt(1, 1);
            preparedStatement.setDouble(2, 1500.24);
            preparedStatement.setString(3, "CHEKING");
            preparedStatement.setBoolean(4, true);
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 2);
            preparedStatement.setDouble(2, 4501.324);
            preparedStatement.setString(3, "SAVINGS");
            preparedStatement.setBoolean(4, true);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

//    --------------------------------
//    fetch all records fron database
//    --------------------------------

        String selectQuery = "SELECT COUNT(*) FROM bank_accounts";

        try(Statement query = connection.createStatement();){
            try(ResultSet resultSet = query.executeQuery(selectQuery);){
                if (resultSet.next()){
                    System.out.println("The records count is : " + resultSet.getInt(1));
                    Assert.assertEquals(2, resultSet.getInt(1));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



}
