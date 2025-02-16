package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class disActiveEvents {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/events_mangement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "events_pro";

    public static void opendisActivePage() {
        JFrame frame = new JFrame("disActive Event");

        frame.setSize(800, 700);

        // Labels and TextFields for searching and updating
        JLabel idLabel = new JLabel("Event ID: ");
        JTextField idField = new JTextField(15);
        JButton disActiveButton = new JButton("disActive");
        JButton backButton = new JButton("Back");
        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(disActiveButton);
        buttonPanel.add(backButton);
        JPanel outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        backButton.addActionListener(e -> new secondPage());
        disActiveButton.addActionListener(e -> {int eventId = Integer.parseInt(idField.getText());
        disActiveEvent(eventId,outputArea);
        });
    }

    private static void disActiveEvent(int eventId, JTextArea outputArea) {
        String updateQuery = "UPDATE events SET  Status = 'disActive' WHERE Event_ID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {


            stmt.setInt(1, eventId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                outputArea.setText("Event disActive successfully!");
            } else {
                outputArea.setText("Event not found or disActive failed.");
            }
        } catch (SQLException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
