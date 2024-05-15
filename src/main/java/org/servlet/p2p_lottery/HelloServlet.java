package org.servlet.p2p_lottery;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.servlet.p2p_lottery.DAO.LotteryDAO;
import org.servlet.p2p_lottery.DAO.BlacklistDAO;

@WebServlet("/greeting")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Check if the user is authenticated with the "tomcat" role
        if (!request.isUserInRole("tomcat")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied. You do not have the required role.");
            return;
        }

        // Check if the user's name is blacklisted
        String userName = request.getParameter("name");
        if (userName != null && BlacklistDAO.isUserNameBlacklisted(userName)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied. Your name is on the blacklist.");
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            String name = (userName != null) ? userName.toUpperCase() : "Anonymous";
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
            out.println("<HTML>");
            out.println("<HEAD><TITLE>Greetings Servlet</TITLE></HEAD>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">");
            out.println("<H1>Greetings " + name + "!</H1>");

            double userWinnings = LotteryDAO.getUserWinnings(userName);
            out.println("Vous avez gagné: " + userWinnings + " millions de dollars!");

            LotteryDAO.saveUserWinnings(userName, Math.random() * 10);

            out.println("</BODY></HTML>");
        }
    }
}
