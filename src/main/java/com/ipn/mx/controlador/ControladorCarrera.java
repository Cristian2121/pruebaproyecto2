package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CarreraDAO;
import com.ipn.mx.modelo.dto.CarreraDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "ControladorCarrera", value = "/ControladorCarrera")
@WebServlet(name = "ControladorCarrera", urlPatterns = {"/ControladorCarrera"})
public class ControladorCarrera extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        try(PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Carreras</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("</head>");
            out.println("<body>");
            
            if (accion.equals("listadoCarreras")) {
               listadoCarreras(request, response);
            } else if(accion.equals("agregarCarrera")){
                agregarCarrera(request, response);
            } else if(accion.equals("agregadoCarrera")){
                agregadoCarrera(request, response);
            } else if(accion.equals("leerCarrera")){
                leerCarrera(request, response);
            } else if(accion.equals("modificarCarrera")){
                modificarCarrera(request, response);
            } else if(accion.equals("modificadoCarrera")){
                modificadoCarrera(request, response);
            } else if(accion.equals("eliminarCarrera")){
                eliminarCarrera(request, response);
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listadoCarreras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>CREAR, ALTAS, BAJAS Y CAMBIOS</h1></header>");
            out.println("<div><a href='index.html' class='btn btn-secondary'>Regresar</a><br /><br />");
            out.println("<div><a href='ControladorCarrera?accion=agregarCarrera' class='btn btn-success'>Nueva carrera</a><br /><br />");
            out.println("<table  class='table table-striped'>");
            out.println("<tr><td>ID</td><td>Nombre Carrera</td><td>Descripción Carrera</td><td>Acciones</td></tr>");

            CarreraDAO dao = new CarreraDAO();
            CarreraDTO dto = new CarreraDTO();

            try {
                List lista = dao.readAll();

                for (int i = 0; i < lista.size(); i++) {
                    dto = (CarreraDTO) lista.get(i);
                    out.println("<tr><td>" + dto.getEntidad().getIdCarrera() + "</td>"
                            + "<td>" + dto.getEntidad().getNombreCarrera() + "</td>"
                            + "<td>" + dto.getEntidad().getDescripcionCarrera() + "</td>"
                            + "<td><a href='ControladorCarrera?accion=leerCarrera&idCarrera=" + dto.getEntidad().getIdCarrera() + "' class='btn btn-primary'>Leer pregunta</a>" + " | "
                            + "<a href='ControladorCarrera?accion=modificarCarrera&idCarrera=" + dto.getEntidad().getIdCarrera() + "' class='btn btn-warning'>Modificar pregunta</a>" + " | "
                            + "<a href='ControladorCarrera?accion=eliminarCarrera&idCarrera=" + dto.getEntidad().getIdCarrera() + "' class='btn btn-danger'>Eliminar pregunta</a></td><tr>");
                }
                out.println("</table></div>");
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
        }
    }
    
    private void agregarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>NUEVA CARRERA</h1></header>");
            out.println("<form action='ControladorCarrera?accion=agregadoCarrera' method='post'>");
            out.println("<table>");
            out.println("<tr><td>Nombre</td><td><input type='text' name='nombreCarrera' class='form-control'></td></tr>");
            out.println("<tr><td>Descripción</td><td><input type='text' name='descripcionCarrera' class='form-control'></td></tr>");
            out.println("</table>");
            out.println("<input type='submit'>");
            out.println("</form><br />");
            out.println("<a href='ControladorCarrera?accion=listadoCarreras' class='btn btn-secondary'>Listado Carreras</a>");
            out.println("</div>");
        }
    }
    
    private void agregadoCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            CarreraDTO dto = new CarreraDTO();
            dto.getEntidad().setNombreCarrera(request.getParameter("nombreCarrera"));
            dto.getEntidad().setDescripcionCarrera(request.getParameter("descripcionCarrera"));
            CarreraDAO dao = new CarreraDAO();
            
            out.println("<div class='container'>");
            try {
                dao.create(dto);
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("Registro insertado con éxito <a href='ControladorCarrera?accion=listadoCarreras' class='alert-link'>Volver</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("No se pudo insertar el registro <a href='ControladorCarrera?accion=listadoCarreras' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
        }
    }
    
    private void leerCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            CarreraDAO dao = new CarreraDAO();
            CarreraDTO dto = new CarreraDTO();
            dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("idCarrera")));
            
            out.println("<div class='container'>");
            try {
                 CarreraDTO dto1 = new CarreraDTO();
                 dto1 = dao.read(dto);
                 
                 out.println("<header><h1 align='center'>LEER CARRERA</h1></header>");
                 out.println("<table class='table'>");
                 out.println("<tr><td>ID Carrera: </td><td>" + dto1.getEntidad().getIdCarrera() + "</td></tr>");
                 out.println("<tr><td>Nombre carrera: </td><td>" + dto1.getEntidad().getNombreCarrera() + "</td></tr>");
                 out.println("<tr><td>Descripción carrera: </td><td>" + dto1.getEntidad().getDescripcionCarrera() + "</td></tr>");
                 out.println("</table>");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("<br />");
            out.println("<a href='ControladorCarrera?accion=listadoCarreras' class='btn btn-secondary'>Listado Carreras</a>");
            out.println("</div>");
        }
    }
    
    private void modificarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            CarreraDTO dto = new CarreraDTO();
            CarreraDAO dao = new CarreraDAO();
            dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("idCarrera")));
            
            out.println("<div class='container'>");
            out.println("<header><h1 align='center'>MODIFICAR CARRERA</h1></header>");
            out.println("<form action='ControladorCarrera?accion=modificadoCarrera' method='post'>");
            out.println("<table>");
            try {
                 CarreraDTO dto1 = new CarreraDTO();
                 dto1 = dao.read(dto);
                 
                 out.println("<tr><td>ID Carrera</td><td><input value='"+ dto1.getEntidad().getIdCarrera() +"' type='text' name='idCarrera' disabled class='form-control'></td></tr>");
                 out.println("<tr><td>Nombre Carrera</td><td><input value='"+ dto1.getEntidad().getNombreCarrera() +"' type='text' name='nombreCarrera' class='form-control'></td></tr>");
                 out.println("<tr><td>Descripción Carrera</td><td><input value='"+ dto1.getEntidad().getDescripcionCarrera() +"' type='text' name='descripcionCarrera' class='form-control'></td></tr>");
                 out.println("<input type='hidden' value='"+ dto1.getEntidad().getIdCarrera() +"' name='idCarrera' >");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</table>");
            out.println("<input type='submit'>");
            out.println("</form><br /><br />");
            
            out.println("<a href='ControladorCarrera?accion=listadoCarreras' class='btn btn-secondary'>Listado Carreras</a>");
            out.println("</div>");
        }
    }
    
    private void modificadoCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            CarreraDTO dto = new CarreraDTO();
            CarreraDAO dao = new CarreraDAO();
            dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("idCarrera")));
            dto.getEntidad().setNombreCarrera(request.getParameter("nombreCarrera"));
            dto.getEntidad().setDescripcionCarrera(request.getParameter("descripcionCarrera"));
            
            out.println("<div class='container'>");
            try {
                dao.update(dto);
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("Registro modificado con éxito <a href='ControladorCarrera?accion=listadoCarreras' class='alert-link'>Volver</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("No se pudo actualizar el registro <a href='ControladorCarrera?accion=listadoCarreras' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
        }
    }
    
    private void eliminarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            CarreraDAO dao = new CarreraDAO();
            CarreraDTO dto = new CarreraDTO();
            dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("idCarrera")));
            
            out.println("<div class='container'>");
            try {
                dao.delete(dto);
                out.println("<div class='alert alert-info' role='alert'>");
                out.println("Registro eliminado con éxito <a href='ControladorCarrera?accion=listadoCarreras' class='alert-link'>Volver</a>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("No se pudo eliminar el registro <a href='ControladorCarrera?accion=listadoCarreras' class='alert-link'>Volver</a>");
                out.println("</div>");
                Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
        }
    }
}
