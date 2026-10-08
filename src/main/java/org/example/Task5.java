package org.example;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Task5 {

    private static int countStudents(Connection conn) throws Exception {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM students")) {
            rs.next();
            return rs.getInt(1);
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

                int id = 2;

                System.out.println("Rows before delete: " + countStudents(conn));

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM students WHERE id = ?")) {
                    ps.setInt(1, id);
                    int rows = ps.executeUpdate();
                    System.out.println("Rows deleted: " + rows);
                }

                System.out.println("Rows after delete: " + countStudents(conn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}