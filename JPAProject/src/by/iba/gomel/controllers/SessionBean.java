package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.MessageManager;

/**
 * This bean uses for working with session.
 */
@Named("sessionInformation")
@SessionScoped
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public SessionBean() {
        // Empty constructor.
    }

    /**
     * 
     * @return active user from session.
     */
    public String getUserName() {
        final HttpSession session = SessionBean.getSession();
        if (session != null) {
            return (String) session.getAttribute(Constants.ATTRIBUTE_NAME_LOGIN);
        } else {
            return null;
        }
    }

    /**
     * 
     * @return current locale using in application.
     */
    public Locale getCurrentLocale() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale currentLocale = context.getExternalContext().getRequestLocale();
        return currentLocale;
    }

    /**
     * 
     * @return active session.
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false);
    }

    /**
     * 
     * @return current request.
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
    }

    /**
     * 
     * @return current response.
     */
    public static HttpServletRequest getResponse() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();
    }

    /**
     * 
     * @param name
     *            name attribute.
     * @param value
     *            value attribute.
     */
    public static void setAttributesSession(final String name, final Object value) {
        SessionBean.getSession().setAttribute(name, value);
    }

    /**
     * 
     * @return status of user.
     */
    public static String getUserType() {
        final HttpSession session = SessionBean.getSession();
        if (session != null) {
            return session.getAttribute(Constants.ATTRIBUTE_NAME_TYPE).toString().trim();
        } else {
            return null;
        }
    }

    /**
     * 
     * @param propertyMessage
     *            property name for getting value from property file.
     * @param tag
     *            html tag for setting message.
     */
    public static void addMessage(final String propertyMessage, final String tag) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale currentLocale = new SessionBean().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        final String messageError = messageManager.getProperty(propertyMessage);
        context.addMessage(tag, new FacesMessage(messageError));
    }

}
