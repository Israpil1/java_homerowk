import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.web.IWebExchange;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/shop", "/delete", "/premium-details", "/standard-details"})
public class BakeryServlet extends HttpServlet {
    private BakeryDAO bakeryDAO = new BakeryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            bakeryDAO.deleteProduct(id);
            response.sendRedirect("shop");
            return;
        }

        var application = JakartaServletWebApplication.buildApplication(getServletContext());
        var webExchange = application.buildExchange(request, response);
        var context = new WebContext(webExchange, webExchange.getLocale());
        TemplateEngine engine = createTemplateEngine(application);

        if ("/premium-details".equals(path)) {
            engine.process("premium", context, response.getWriter());
        } else if ("/standard-details".equals(path)) {
            engine.process("standard", context, response.getWriter());
        } else {
            List<Product> products = bakeryDAO.getAllProducts();
            context.setVariable("products", products);
            engine.process("products", context, response.getWriter());
        }
    }

    private TemplateEngine createTemplateEngine(JakartaServletWebApplication app) {
        WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(app);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
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