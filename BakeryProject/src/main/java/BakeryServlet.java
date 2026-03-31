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

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            bakeryDAO.deleteProduct(id);
            response.sendRedirect("shop");
            return;
        }

        List<Product> productList = bakeryDAO.getAllProducts();
        request.setAttribute("products", productList);
        request.getRequestDispatcher("bakery-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String desc = request.getParameter("description");

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            bakeryDAO.updateProduct(new Product(id, name, price, desc));
        } else {
            bakeryDAO.addProduct(new Product(0, name, price, desc));
        }

        response.sendRedirect("shop");
    }
}