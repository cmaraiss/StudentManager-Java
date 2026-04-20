package com.mycompany.studentmanager;

public class Task<T> {
    private T id;
    private String name;
    private String description;
    private boolean completed;
    private String category;

    public Task(T id, String name, String description, boolean completed, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.category = category;
    }

    public T getId() { return id; }
    public void setId(T id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "Task{id=" + id + ", name='" + name + "', description='" + description + "', completed=" + completed + ", category='" + category + "'}";
    }
}
