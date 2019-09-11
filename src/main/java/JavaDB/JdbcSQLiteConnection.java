package JavaDB;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcSQLiteConnection implements ReadDataSource {

    public Map<Integer, Customer> readCustomers() throws IOException {
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
        try {
// setup
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:atmdb.db";
            Connection conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                String query = "Select * from customers";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int number = resultSet.getInt(1);
                    int pin = resultSet.getInt(2);
                    double balance = resultSet.getDouble(3);
                    Customer c = new Customer(number, pin, balance);
                    customers.put(c.getCustomerNumber(),c);
                }
// close connection
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }
}

