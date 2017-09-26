/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MisPaquetes.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cetecom
 */
@WebServlet(urlPatterns = {"/productosServlet"})
public class productosServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String codigo=request.getParameter("codigo");
            String descripcion=request.getParameter("descripcion");
            String marca=request.getParameter("marca");
            conexion con= new conexion();
            Connection conn=con.conectarBD("supermercado");
            PreparedStatement stmt = null;
            PreparedStatement stmt2 = null;
            String query = "insert into producto (codigo,descripcion,marca) values (?, ?, ?) ";
            session.setAttribute("mensajeProducto", null);
            String query2 = "select codigo from producto where codigo="+codigo;
            stmt2 = conn.prepareStatement(query2);
            ResultSet rs=stmt2.executeQuery();
            if(rs.next()==false){
                try {
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1,codigo);
                    stmt.setString(2,descripcion);
                    stmt.setString(3,marca);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(productosServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("muestra_productos.jsp");
            }else{
                session.setAttribute("mensajeProducto", "Producto ya existe");
                response.sendRedirect("muestra_productos.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
