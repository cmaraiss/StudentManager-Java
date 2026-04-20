package com.mycompany.studentmanager;

import java.io.*;
import java.util.List;

public class FileManager {

    public void saveTasksToFile(List<Task<Integer>> tasks, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task<Integer> task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        }
    }

    public void exportTasksToCSV(List<Task<Integer>> tasks, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,Name,Description,Completed,Category");
            writer.newLine();
            for (Task<Integer> task : tasks) {
                writer.write(String.format("%s,%s,%s,%s,%s",
                    task.getId(), task.getName(), task.getDescription(),
                    task.isCompleted(), task.getCategory()));
                writer.newLine();
            }
        }
    }
}
