import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DatabaseConnector dbConnector;

    public EmployeeDAO(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    // Додавання нового співробітника
    public void addEmployee(String name, int age, String position, float salary) throws SQLException {
        String query = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, position);
            stmt.setFloat(4, salary);
            stmt.executeUpdate();
        }
    }

    // Оновлення інформації про співробітника
    public void updateEmployee(int id, String name, int age, String position, float salary) throws SQLException {
        String query = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, position);
            stmt.setFloat(4, salary);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    // Видалення співробітника за ID
    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Отримання списку всіх співробітників
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection conn = dbConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String position = rs.getString("position");
                float salary = rs.getFloat("salary");
                employees.add(new Employee(id, name, age, position, salary));
            }
        }
        return employees;
    }
}
