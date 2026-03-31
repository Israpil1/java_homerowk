import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BakeryDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");

                products.add(new Product(id, name, price, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}