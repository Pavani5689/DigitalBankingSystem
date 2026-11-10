
package com.dao;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionManager.getConnection();

            if (connection != null) {
                System.out.println("MySQL Connected Successfully!");

                connection.close();
                System.out.println("Connection Closed Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
