package by.iba.gomel.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.ConfigurationManager;

/**
 * This servlet uses for checking session between client and server.
 */
@WebServlet("/SessionController")
public class SessionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        if (session.getAttribute(Constants.ATTRIBUTE_NAME_LOGIN) == null) {
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty(Constants.PROPERTY_PATH_LOGIN_PAGE)).forward(
                    request, response);
        } else {
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty(Constants.PROPERTY_PATH_MAIN_PAGE)).forward(
                    request, response);
        }

    }

}
