package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class showEvents {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/events_mangement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "events_pro";


    public static void openShow(String title) {
        JFrame frame = new JFrame("Show Events");

        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 144, 255));

        JLabel header = new JLabel("Show Events", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        mainPanel.add(header, BorderLayout.NORTH);

        // Labels and TextFields

        JButton backButton = new JButton("Back");

        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);


        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));


        JPanel buttonPanel = new JPanel();

        buttonPanel.add(backButton);

        JPanel outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));

        frame.setLayout(new BorderLayout());

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(outputPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        // Action Listeners
        backButton.addActionListener(e -> new secondPage());

        viewRecords(outputArea);

    }

    private static void viewRecords(JTextArea outputArea) {
        String selectQuery = "SELECT Event_ID, Name, Date, Time ,Type_Of_Event , Organizer_ID ,Customer_ID , Payment ,Status FROM events";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            StringBuilder records = new StringBuilder("Event_ID\tName\tDate\tTime\tType_Of_Event\tOrganizer_ID\tCustomer_ID\tPayment\tStatus\n");
            while (rs.next()) {
                records.append(rs.getInt("Event_ID")).append("\t")
                        .append(rs.getString("Name")).append("\t")
                        .append(rs.getDate("Date")).append("\t")
                        .append(rs.getTime("Time")).append("\t")
                        .append(rs.getString("Type_Of_Event")).append("\t")
                        .append(rs.getInt("Organizer_ID")).append("\t")
                        .append(rs.getInt("Customer_ID")).append("\t")
                        .append(rs.getDouble("Payment")).append("\n")
                        .append(rs.getString("Status")).append("\n");
            }
            outputArea.setText(records.toString());
        } catch (SQLException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

}
