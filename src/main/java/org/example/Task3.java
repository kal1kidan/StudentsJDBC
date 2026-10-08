package org.example;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Task3 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);

            try (Connection conn = DriverManager.getConnection(
                    props.getProperty("db.url") + "StudentsDB",
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM students LIMIT 5")) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int grade = rs.getInt("grade");
                    System.out.println("ID: " + id + ", Name: " + firstname + " "
                            + lastname + ", Grade: " + grade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}