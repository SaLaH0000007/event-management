package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addEvents {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/events_mangement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "events_pro";


    public static void openAdd(String title) {
        JFrame frame = new JFrame("Add Events");

        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 144, 255));

        JLabel header = new JLabel("Add Events", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        mainPanel.add(header, BorderLayout.NORTH);


        // Labels and TextFields
        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField(15);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField(15);

        JLabel timeLabel = new JLabel("Time (HH:MM):");
        JTextField timeField = new JTextField(15);

        JLabel typeLabel = new JLabel("Type of Event:");
        JTextField typeField = new JTextField(15);

        JLabel organizerLabel = new JLabel("Organizer ID:");
        JTextField organizerField = new JTextField(15);

        JLabel customerLabel = new JLabel("Customer ID:");
        JTextField customerField = new JTextField(15);

        JLabel paymentLabel = new JLabel("Payment:");
        JTextField paymentField = new JTextField(15);


        JButton addButton = new JButton("Add Event");

        JButton backButton = new JButton("Back");

        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);


        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeField);
        inputPanel.add(organizerLabel);
        inputPanel.add(organizerField);
        inputPanel.add(customerLabel);
        inputPanel.add(customerField);
        inputPanel.add(paymentLabel);
        inputPanel.add(paymentField);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        JPanel outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Action Listeners
        backButton.addActionListener(e -> new secondPage());
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            String type = typeField.getText();
            int organizer = Integer.parseInt(organizerField.getText());
            int customer = Integer.parseInt(customerField.getText());
            int payment = Integer.parseInt(paymentField.getText());

            addRecord(name, date, time, type, organizer, customer, payment, outputArea);
        });


    }

    private static void addRecord(String name, String date, String time, String type, int organizer, int customer, int payment, JTextArea outputArea) {
        String insertQuery = "INSERT INTO events (Name, Date, Time, Type_Of_Event, Organizer_ID, Customer_ID, Payment) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), TO_TIMESTAMP(?, ' HH24:MI:SS'), ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setString(3, time);
            stmt.setString(4, type);
            stmt.setInt(5, organizer);
            stmt.setInt(6, customer);
            stmt.setInt(7, payment);


            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                outputArea.setText("Event added successfully!\n");
            }
        } catch (SQLException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }


}
