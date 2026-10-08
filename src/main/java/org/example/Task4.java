package org.example;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Task4 {

    private static void printStudent(Connection conn, int id) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM students WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: "
                            + rs.getString("firstname") + " "
                            + rs.getString("lastname") + ", Grade: "
                            + rs.getInt("grade"));
                } else {
                    System.out.println("No student with id " + id);
                }
            }
        }
    }

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);

            try (Connection conn = DriverManager.getConnection(
                    props.getProperty("db.url") + "StudentsDB",
                    props.getProperty("db.user"),
                    props.getProperty("db.password"))) {

                int id = 1;
                String newFirstName = "Johnny";

                System.out.println("Before:");
                printStudent(conn, id);

                try (PreparedStatement ps = conn.prepareStatement(
                        "UPDATE students SET firstname = ? WHERE id = ?")) {
                    ps.setString(1, newFirstName);
                    ps.setInt(2, id);
                    int rows = ps.executeUpdate();
                    System.out.println("Rows updated: " + rows);
                }

                System.out.println("After:");
                printStudent(conn, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}