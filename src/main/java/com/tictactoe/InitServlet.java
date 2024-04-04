package com.tictactoe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Створення нової сесії
        HttpSession currentSession = req.getSession(true);

        // Створення ігрового поля
        Field field = new Field();
        Map<Integer, Sign> fieldData = field.getField();

        // Отримання списку значень поля
        List<Sign> data = field.getFieldData();

        // Додавання до сесії параметрів поля (потрібно буде для зберігання стану між запитами)
        currentSession.setAttribute("field", field);
        // та значень поля, що відсортовані за індексом (потрібно для промальовки хрестиків і нуликів)
        currentSession.setAttribute("data", data);

        // Перенаправлення запиту на сторінку index.jsp через сервер
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}