import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shop")
public class BakeryServlet extends HttpServlet {

    private BakeryDAO bakeryDAO = new BakeryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> productList = bakeryDAO.getAllProducts();

        request.setAttribute("products", productList);

        request.getRequestDispatcher("bakery-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        Product newProduct = new Product(0, name, price, description);
        bakeryDAO.addProduct(newProduct);

        response.sendRedirect("shop");
    }
}