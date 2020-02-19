package com.budanov;


import com.budanov.entity.Task;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Слой хранения в базе данных

описываем наше хранилище
 */
@Component
public class TaskRepository implements Repository {

    static final String SQL_DELETE = "DELETE FROM task WHERE id = ?";
    static final String SQL_SELECT_ALL = "SELECT * FROM task"; //1 - id, 2 - title, 3 - desc, 4 - isDone
    static final String SQL_INSERT = "INSERT INTO task (id, title, description) " +
            "VALUES  (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE `title` = ?, `description` = ?";
    static final String SQL_SET_IS_DONE = "UPDATE task SET is_done = ? WHERE id = ?";

    private Connection getDatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/taskmnger?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public void setIsDone(int taskId, boolean isDone) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SET_IS_DONE)) {
            ps.setBoolean(1, isDone);
            ps.setInt(2, taskId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Task> list() {
        List<Task> taskList = new ArrayList<>();//пустой список
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                taskList.add(task);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return taskList;
    }

    public void save(Task task) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setObject(1, task.getId());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getDescription());
            ps.setString(4, task.getTitle());
            ps.setString(5, task.getDescription());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
