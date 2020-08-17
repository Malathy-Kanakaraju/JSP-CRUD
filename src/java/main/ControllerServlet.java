package main;

import java.io.*;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MALATHI
 */
public class ControllerServlet extends HttpServlet {

    private UserDAO userDAO;
    
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
        File file = new File(System.getenv("CATALINA_BASE") + "/config.properties");
        InputStream input = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(input);
        userDAO = new UserDAO(prop.getProperty("jdbc.url"), prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));

        String action = request.getServletPath();
        
        try {
            switch(action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/newList":
                    showNewFormMultiple(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/insertList":
                    insertUserMultiple(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException{
        List<User> users = userDAO.listUsers();
        request.setAttribute("userList", users);
        RequestDispatcher rd = request.getRequestDispatcher("UserList.jsp");
        rd.forward(request, response);        
    }
    
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        RequestDispatcher rd = request.getRequestDispatcher("UserForm.jsp");
        rd.forward(request, response);        
    }
    
    
    private void showNewFormMultiple(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        RequestDispatcher rd = request.getRequestDispatcher("MultipleUserForm.jsp");
        rd.forward(request, response);        
    }
    
    private void insertUser(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        User user = new User(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("address"));
        userDAO.addUser(user);
        response.sendRedirect("list");
    }
    
    private void insertUserMultiple(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        List<User> user = new ArrayList<>();
        String[] firstName = request.getParameterValues("firstName");
        String[] lastName = request.getParameterValues("lastName");
        String[] address = request.getParameterValues("address");
        for (int i = 0; i < firstName.length; i++) {
            user.add(new User(firstName[i], lastName[i], address[i]));            
        }
        userDAO.addUserMultiple(user);
        response.sendRedirect("list");
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        User user = new User(Integer.parseInt(request.getParameter("id")));
        userDAO.deleteUser(user);
        response.sendRedirect("list");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        User user = userDAO.getUser(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("UserForm.jsp");
        rd.forward(request, response);
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        User user = new User(Integer.parseInt(request.getParameter("id")), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("address"));
        userDAO.updateUser(user);
        response.sendRedirect("list");
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

}
