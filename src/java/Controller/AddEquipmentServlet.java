package Controller;

import DAO.EquipmentDAO;
import Models.Equipment;
import Utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddEquipmentServlet", urlPatterns = {"/add-equipment"})
public class AddEquipmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String equipmentId = UUID.randomUUID().toString();
            String name = request.getParameter("name");
            String equipmentType = request.getParameter("equipment_type");
            String imageUrl = request.getParameter("image_url");
            float rentalPrice = Float.parseFloat(request.getParameter("rental_price"));
            float damageFee = Float.parseFloat(request.getParameter("damage_fee"));
            String status = request.getParameter("status");
            String description = request.getParameter("description");

            Equipment eq = new Equipment(
                    equipmentId,
                    name,
                    equipmentType,
                    imageUrl,
                    rentalPrice,
                    damageFee,
                    status,
                    description
            );

            DBConnection db = new DBConnection();
            EquipmentDAO dao = new EquipmentDAO(db);

            boolean success = dao.addEquipment(eq);

            if (success) {
                response.sendRedirect("addEquipment.jsp?success=true");
            } else {
                response.sendRedirect("addEquipment.jsp?error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addEquipment.jsp?error=true");
        }
    }
}
