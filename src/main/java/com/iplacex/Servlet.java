package com.iplacex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth")
public class Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (verificarCredenciales(username, password)) {
            List<String> usuario = obtenerDatosDesdeLaBaseDeDatos(username);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/correcto.jsp").forward(request, response);
        } else {
            response.sendRedirect("fallido.jsp");
        }
    }
    
    public boolean verificarCredenciales(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM usuario WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next(); // Si hay un resultado, las credenciales son válidas
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> obtenerDatosDesdeLaBaseDeDatos(String username) {
        List<String> usuario = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT rut FROM usuario WHERE username = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String rut = rs.getString("rut");
                usuario.add(rut);
                
             
                String query2 = "SELECT nombre, apellido, telefono, fechaIngreso FROM persona WHERE rut = ?";
                PreparedStatement personaStmt = conn.prepareStatement(query2);
                personaStmt.setString(1, rut);
                ResultSet personaRs = personaStmt.executeQuery();
                
                while (personaRs.next()) {
                    String nombre = personaRs.getString("nombre");
                    String apellido = personaRs.getString("apellido");
                    String telefono = personaRs.getString("telefono");
                    String fechaIngreso = personaRs.getString("fechaIngreso");
                    
                   
                    usuario.add("Nombre: " + nombre);
                    usuario.add("Apellido: " + apellido);
                    usuario.add("Telefono: " + telefono);
                    usuario.add("Fecha de Ingreso: " + fechaIngreso);
                }
                
                personaRs.close();
                personaStmt.close();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuario;
    }
}

