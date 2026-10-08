package org.example;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class Task2 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);

            try (Connection conn = DriverManager.getConnection(
                    props.getProperty("db.url") + "StudentsDB",
                    props.getProperty("db.user"),
                    props.getProperty("db.password"))) {

                // Start clean so the program can be re-run
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("DELETE FROM students");
                }

                String sql = "INSERT INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    // One example row
                    ps.setInt(1, 1);
                    ps.setString(2, "John");
                    ps.setString(3, "Doe");
                    ps.setInt(4, 90);
                    ps.executeUpdate();
                    System.out.println("Inserted 1 example row.");

                    // Ten more rows
                    String[][] rows = {
                            {"Abel", "Tesfaye", "85"},
                            {"Selam", "Bekele", "92"},
                            {"Dawit", "Mekonnen", "78"},
                            {"Hanna", "Alemu", "88"},
                            {"Yonas", "Girma", "71"},
                            {"Meron", "Tadesse", "95"},
                            {"Biruk", "Haile", "64"},
                            {"Liya", "Assefa", "83"},
                            {"Nahom", "Kebede", "79"},
                            {"Tigist", "Worku", "90"}
                    };
                    int id = 2;
                    for (String[] r : rows) {
                        ps.setInt(1, id++);
                        ps.setString(2, r[0]);
                        ps.setString(3, r[1]);
                        ps.setInt(4, Integer.parseInt(r[2]));
                        ps.executeUpdate();
                    }
                    System.out.println("Inserted 10 more rows.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}