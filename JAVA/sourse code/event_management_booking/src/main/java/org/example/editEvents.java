package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class editEvents {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/events_mangement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "events_pro";

    public static void openEditPage() {
        JFrame frame = new JFrame("Edit Event");

        frame.setSize(800, 700);

        // Labels and TextFields for searching and updating
        JLabel idLabel = new JLabel("Event ID: (For Search only)");
        JTextField idField = new JTextField(15);

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

        JLabel statusLabel = new JLabel("Status:");
        JTextField statusField = new JTextField(15);

        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back");

        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);

        // Layout setup
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        inputPanel.add(idLabel);
        inputPanel.add(idField);
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
        inputPanel.add(statusLabel);
        inputPanel.add(statusField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);
        buttonPanel.add(updateButton);
        JPanel outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Action Listeners
        searchButton.addActionListener(e -> {
            int eventId = Integer.parseInt(idField.getText());
            searchEvent(eventId, nameField, dateField, timeField, typeField, organizerField, customerField, paymentField, statusField, outputArea);
        });

        backButton.addActionListener(e -> new secondPage());

        updateButton.addActionListener(e -> {
            int eventId = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            String type = typeField.getText();
            int organizer = Integer.parseInt(organizerField.getText());
            int customer = Integer.parseInt(customerField.getText());
            int payment = Integer.parseInt(paymentField.getText());
            String status = statusField.getText();

            updateEvent(eventId, name, date, time, type, organizer, customer, payment, status, outputArea);
        });
    }

    private static void searchEvent(int eventId, JTextField nameField, JTextField dateField, JTextField timeField, JTextField typeField,
                                    JTextField organizerField, JTextField customerField, JTextField paymentField, JTextField statusField, JTextArea outputArea) {
        String selectQuery = "SELECT * FROM events WHERE Event_ID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("Name"));
                dateField.setText(rs.getString("Date"));
                timeField.setText(rs.getString("Time"));
                typeField.setText(rs.getString("Type_Of_Event"));
                organizerField.setText(String.valueOf(rs.getInt("Organizer_ID")));
                customerField.setText(String.valueOf(rs.getInt("Customer_ID")));
                paymentField.setText(String.valueOf(rs.getInt("Payment")));
                statusField.setText(rs.getString("Status"));
                outputArea.setText("Event loaded successfully.");
            } else {
                outputArea.setText("Event not found.");
            }
        } catch (SQLException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private static void updateEvent(int eventId, String name, String date, String time, String type, int organizer, int customer, int payment, String status, JTextArea outputArea) {
        String updateQuery = "UPDATE events SET Name = ?, Date = TO_DATE(?, 'YYYY-MM-DD'), Time = TO_TIMESTAMP(?, 'HH24:MI'), Type_Of_Event = ?, Organizer_ID = ?, Customer_ID = ?, Payment = ?, Status = ? WHERE Event_ID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setString(3, time);
            stmt.setString(4, type);
            stmt.setInt(5, organizer);
            stmt.setInt(6, customer);
            stmt.setInt(7, payment);
            stmt.setString(8, status);
            stmt.setInt(9, eventId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                outputArea.setText("Event updated successfully!");
            } else {
                outputArea.setText("Event not found or update failed.");
            }
        } catch (SQLException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

}
