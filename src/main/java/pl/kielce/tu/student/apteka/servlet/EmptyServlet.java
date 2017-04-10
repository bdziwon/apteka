package pl.kielce.tu.student.apteka.servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.student.apteka.model.Client;

@WebServlet(name = "emptyServlet", urlPatterns = { "/emptyServlet" })
public class EmptyServlet extends HttpServlet {

    @Inject
    private Logger logger;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.log(Level.INFO, "doGet Called");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.log(Level.INFO, "doPost Called");

    }
}
