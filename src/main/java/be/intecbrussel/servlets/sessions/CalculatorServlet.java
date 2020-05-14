package be.intecbrussel.servlets.sessions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //manual way to check if attribute does not exist
        //if it does not exist initialize it
        /*
        Object sum = session.getAttribute("sum");
        if(sum ==null){
            sum = 0;
            session.setAttribute("sum",sum);
        }*/
        if(session.isNew()){
            session.setAttribute("sum",0);
        }
        req.getRequestDispatcher("/WEB-INF/pages/Calculator.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.isNew()){
            session.setAttribute("sum",0);
        }

        Integer sum = (Integer) session.getAttribute("sum");
        sum = sum + Integer.parseInt(req.getParameter("numbertoadd"));
        session.setAttribute("sum",sum);

        resp.sendRedirect("/sessiondemo/calculate");
    }
}
