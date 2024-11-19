package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Productos;
import servicios.ProductosServiceImplement;
import servicios.ProductosServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.awt.SystemColor.text;

//Anotación
@WebServlet({"/producto.xls","/productohtml"})
public class ProductoXls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ProductosServices servicios= new ProductosServiceImplement();
        List<Productos> productos= servicios.listar();
        resp.setContentType("text/html;charset=UTF-8");
        //OBTENER EL SERVLET PATH
        String servletpath = req.getServletPath();
        //crear una ariable de tipo booleano para ver que pat estoy obteniendo
        boolean xls=servletpath.equals(".xls");
        if (xls){
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; filename=productos.xls");
        }

        PrintWriter out = resp.getWriter();
        //ecribimos la respuesta en htnl
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset ='UTF-8'>");
        out.println("<title>Listado de Productos</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Listado de Productos</h1>");
        out.println("<p><a href=\""+req.getContextPath()+"/productos.xls"+"\">Descargar.xls</a></p>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID PROTUCTO</th>");
        out.println("<th>NOMBRE</th>");
        out.println("<th>CATEGORIA</th>");
        out.println("<th>PRECIO</th>");
        out.println("</tr>");
        productos.forEach(p->{
            out.println("<tr>");
            out.println("<td>" +p.getIdProducto()+"</td>");
            out.println("<td>" +p.getNombre()+"</td>");
            out.println("<td>" +p.getCategoria()+"</td>");
            out.println("<td>" +p.getPrecio()+"</td>");
            out.println("</tr>");

        });
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

    }
}
