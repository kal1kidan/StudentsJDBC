package org.example;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Task6 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);

            try (Connection conn = DriverManager.getConnection(
                    props.getProperty("db.url") + "StudentsDB",
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "SELECT AVG(grade) AS average_grade FROM students")) {

                if (rs.next()) {
                    double averageGrade = rs.getDouble("average_grade");
                    System.out.printf("Average Grade: %.2f%n", averageGrade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
