package com.budanov;


import com.budanov.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Слой хранения в базе данных

описываем наше хранилище
 */
public class TaskRepository {

    static final String SQL_DELETE = "DELETE FROM task WHERE id = ?";
    static final String SQL_SELECT_ALL = "SELECT * FROM task"; //1 - id, 2 - title, 3 - desc, 4 - isDone
    static final String SQL_INSERT = "INSERT INTO task (id, title, description) " +
            "VALUES  (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE `title` = ?, `description` = ?";
    static final String SQL_SET_IS_DONE = "UPDATE task SET is_done = ? WHERE id = ?";
    static final String SQL_ORDER_BY = "SELECT * FROM tasks ORDER BY %s ASC";
    static final String SQL_SET_COPY = "INSERT INTO task (title, description) SELECT title, description FROM task WHERE id = ?";

    private Connection getDatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/taskmanager?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    void setIsDone(int taskId, boolean isDone) throws SQLException {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SET_IS_DONE)) {
            ps.setBoolean(1, isDone);
            ps.setInt(2, taskId);
            ps.execute();
        }
    }

    void delete(int id) throws SQLException {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    List<Task> list() throws SQLException {
        List<Task> taskList = new ArrayList<>();//пустой список
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                taskList.add(task);
            }
        }
        return taskList;
    }

    void save(Task task) throws SQLException {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setObject(1, task.getId());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getDescription());
            ps.setString(4, task.getTitle());
            ps.setString(5, task.getDescription());
            ps.execute();
        }
    }

    // Этот метод лишь выводит на экран (в консоль) отсортированный список в алфавитном порядке, не изменяя БД
    List<Task> viewInAsc(String columnName) throws SQLException {
        String sortCommand = String.format(SQL_ORDER_BY, columnName);
        List<Task> sortedTaskList = new ArrayList<>();
        try (Connection connection = getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sortCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(resultSet.getString("Title"), // в этих строках нужно указать правильные имена колонок со своей БД
                        resultSet.getString("Description"));
                task.setDone(resultSet.getBoolean("is_done"));
                sortedTaskList.add(task);
            }
        }
        return sortedTaskList;
    }

    void setCopy(int id) throws SQLException {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SET_COPY)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }
}
