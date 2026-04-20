package com.mycompany.studentmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TaskManagerApp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private DatabaseManager dbManager = new DatabaseManager();
    private FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Description", "Completed", "Category"}, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons setup
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton loadButton = new JButton("Load Tasks");
        JButton saveButton = new JButton("Save Tasks");
        JButton exportButton = new JButton("Export to CSV");

        addButton.addActionListener(e -> showTaskEntryDialog());
        loadButton.addActionListener(e -> loadTasksFromDatabase());
        saveButton.addActionListener(e -> saveTasksToFile());
        exportButton.addActionListener(e -> exportTasksToCSV());

        panel.add(addButton);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(exportButton);
        frame.add(panel, BorderLayout.SOUTH);

        // Initialize database
        dbManager.createTable();
        frame.setVisible(true);
    }

    private void showTaskEntryDialog() {
        JTextField nameField = new JTextField(20);
        JTextField descField = new JTextField(20);
        JTextField categoryField = new JTextField(20);
        JCheckBox completedCheckBox = new JCheckBox("Completed");

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name:"));
        myPanel.add(nameField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Description:"));
        myPanel.add(descField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Category:"));
        myPanel.add(categoryField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(completedCheckBox);

        int result = JOptionPane.showConfirmDialog(frame, myPanel, "Enter Task", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Task<Integer> task = new Task<>(null, nameField.getText(), descField.getText(), completedCheckBox.isSelected(), categoryField.getText());
            dbManager.insertTask(task);
            loadTasksFromDatabase();
        }
    }

    private void loadTasksFromDatabase() {
        tableModel.setRowCount(0);
        ResultSet rs = dbManager.getTasks();
        try {
            if (rs != null) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getBoolean("completed"), rs.getString("category")});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveTasksToFile() {
        List<Task<Integer>> tasks = new ArrayList<>();
        try {
            fileManager.saveTasksToFile(tasks, "tasks.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportTasksToCSV() {
        List<Task<Integer>> tasks = new ArrayList<>();
        try {
            fileManager.exportTasksToCSV(tasks, "tasks.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
