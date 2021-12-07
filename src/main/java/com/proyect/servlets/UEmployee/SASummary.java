package com.proyect.servlets.UEmployee;

import com.proyect.modelsDAO.OProduct.ReceiptDetailDAO;
import com.proyect.modelsDAO.UClient.ClientDAO;
import com.proyect.modelsDAO.UEmployee.Summary;
import com.proyect.modelsDTO.BCar.ReceiptDetail;
import com.proyect.modelsDTO.UClient.Client;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SASummary", value = "/SASummary")
public class SASummary extends HttpServlet {

    List<Client> clients = new ArrayList<>();
    ClientDAO cdao = new ClientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("list")){
            clients = cdao.listLast();
            Summary summ = new Summary();
            int clientsAll = cdao.list().size();
            request.getSession().setAttribute("clientsAll", clientsAll);
            request.getSession().setAttribute("clientsSummary", clients);
            request.setAttribute("sales", summ.getSummary());
            request.setAttribute("incomes", summ.getIncome());
            request.getRequestDispatcher("/views/admin/summary.jsp").forward(request, response);
        }

        //List<ReceiptDetail> detail = new ReceiptDetailDAO().list();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
