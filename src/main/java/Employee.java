public class Employee {
    private int id;
    private String name;
    private int age;
    private String position;
    private float salary;

    // Конструктор, геттери та сеттери
    public Employee(int id, String name, int age, String position, float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
