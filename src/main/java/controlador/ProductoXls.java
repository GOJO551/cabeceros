package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
//Anotación
@WebServlet({"/producto.xls","/productohtml"})
public class ProductoXls extends HttpServlet {
}
