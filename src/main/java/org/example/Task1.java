package org.example;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Task1 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);

            try (Connection conn = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));
                 Statement stmt = conn.createStatement()) {

                System.out.println("Established Connection");

                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS StudentsDB");
                System.out.println("Database StudentsDB ready.");

                stmt.executeUpdate("USE StudentsDB");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students ("
                        + "id INT PRIMARY KEY, "
                        + "firstname VARCHAR(255), "
                        + "lastname VARCHAR(255), "
                        + "grade INT)");
                System.out.println("Table students ready.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}