import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        EmployeeDAO employeeDAO = new EmployeeDAO(dbConnector);

        try {
            // Додавання співробітників
            employeeDAO.addEmployee("John Doe", 30, "Manager", 5000.0f);
            employeeDAO.addEmployee("Jane Smith", 25, "Developer", 4000.0f);

            // Отримання та виведення списку співробітників
            List<Employee> employees = employeeDAO.getAllEmployees();
            System.out.println("Список співробітників:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }

            // Оновлення інформації про співробітника
            employeeDAO.updateEmployee(1, "John Doe", 31, "Senior Manager", 5500.0f);

            // Видалення співробітника
            employeeDAO.deleteEmployee(2);

            // Перевірка змін
            employees = employeeDAO.getAllEmployees();
            System.out.println("\nОновлений список співробітників:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
