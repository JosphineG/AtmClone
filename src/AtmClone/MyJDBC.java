package AtmClone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJDBC {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/atm";
    private static final String USER = "root";
    private static final String PASSWORD = "vero@nicah27";

    public static void main(String[] args) {
        MyJDBC myJDBC = new MyJDBC();
        Connection connection = myJDBC.getConnection();

        if (connection != null) {
            System.out.println("Database connection established successfully!");
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
