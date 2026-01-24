package Controller;

import DAO.EquipmentDAO;
import Models.Equipment;
import Utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddEquipmentServlet", urlPatterns = {"/add-equipment"})
public class AddEquipmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("View/AddEquipment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            // Validate text fields
            String name = request.getParameter("name");
            String equipmentType = request.getParameter("equipment_type");
            String status = request.getParameter("status");
            String description = request.getParameter("description");

            if (name == null || name.isEmpty()) {
                request.setAttribute("error", "Name is required");
                request.getRequestDispatcher("View/AddEquipment.jsp").forward(request, response);
                return;
            }

            float rentalPrice = Float.parseFloat(request.getParameter("rental_price"));
            float damageFee = Float.parseFloat(request.getParameter("damage_fee"));

            if (rentalPrice <= 0 || damageFee <= 0) {
                request.setAttribute("error", "Price must be greater than 0");
                request.getRequestDispatcher("View/AddEquipment.jsp").forward(request, response);
                return;
            }

            // Handle image upload
            Part imagePart = request.getPart("image");
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            String imagePath = "uploads/" + UUID.randomUUID() + "_" + fileName;
            imagePart.write(getServletContext().getRealPath("/") + imagePath);

            // Create Equipment
            Equipment eq = new Equipment(
                    UUID.randomUUID().toString(),
                    name,
                    equipmentType,
                    imagePath,
                    rentalPrice,
                    damageFee,
                    status,
                    description
            );

            DBConnection db = new DBConnection();
            EquipmentDAO dao = new EquipmentDAO(db);

            if (dao.addEquipment(eq)) {
                request.setAttribute("success", "Equipment added successfully!");
            } else {
                request.setAttribute("error", "Failed to add equipment");
            }

            request.getRequestDispatcher("View/AddEquipment.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "System error occurred");
            request.getRequestDispatcher("View/AddEquipment.jsp").forward(request, response);
        }
    }
}
