package controller;

import dao.NEXEntityDAO;
import model.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/EditDB")
public class EditDB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NEXEntityDAO<Students> nexEntityDAO2 = new NEXEntityDAO<>(Students.class);
        String date = req.getParameter("birthdayDate");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String age = req.getParameter("age");
        int age1 = Integer.parseInt(age);

        Students editing = new Students(req.getParameter("firstName"), req.getParameter("lastName"),
                age1 , date1, req.getParameter("faculty"));

        nexEntityDAO2.update(editing);

        resp.sendRedirect("/testMain.html");
    }
}
