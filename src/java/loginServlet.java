/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MisPaquetes.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
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
@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

        try (PrintWriter out = response.getWriter()) {
            String usuario=request.getParameter("usuario");
            String clave=request.getParameter("clave");
            String claveMD5=md5(clave);
            
            String nombreUsuario = null;
            session.setAttribute("nombreUsuario", nombreUsuario);
            conexion con= new conexion();
            Connection conn=con.conectarBD("supermercado");
            PreparedStatement stmt = null;
            String query = "select * from usuario where usuario=? and clave=? ";
            try {
                stmt = conn.prepareStatement(query);
                stmt.setString(1,usuario);
                stmt.setString(2,claveMD5);
                ResultSet rs=stmt.executeQuery();
                while (rs.next()){
                    nombreUsuario=rs.getString("nombre");
                }
            } catch (SQLException ex) {
                Logger.getLogger(productosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (nombreUsuario != null){
                session.setAttribute("nombreUsuario", nombreUsuario);
            }
            response.sendRedirect("index.jsp");
        } catch (Exception ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String md5(String textoplano) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(textoplano.getBytes());

        int size = b.length;
        StringBuilder h = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0").append(Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
}
