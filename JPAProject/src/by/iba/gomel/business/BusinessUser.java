package by.iba.gomel.business;

import java.io.Serializable;
import java.util.List;

import by.iba.gomel.Constants;
import by.iba.gomel.controllers.SessionBean;
import by.iba.gomel.factories.DatabaseDAOFactory;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.model.User;

/**
 * This business-logic class uses for working with user.
 */
public class BusinessUser implements Serializable {

    private static final long serialVersionUID = 1L;
    DatabaseDAO               data             = DatabaseDAOFactory.getInstance();

    /**
     * 
     * @param userName
     *            userName.
     * @param password
     *            password.
     * @return result of validating user.
     */
    public boolean validateUser(final String userName, final String password) {
        final User user = data.checkUser(userName, password);
        if (user != null) {
            SessionBean.setAttributesSession(Constants.ATTRIBUTE_NAME_LOGIN, user.getUserName());
            SessionBean.setAttributesSession(Constants.ATTRIBUTE_NAME_TYPE, user.getType());
            return true;
        } else {
            SessionBean.addMessage(Constants.MESSAGE_LOGIN_ERROR,
                    Constants.TAG_ERROR_MESSAGE_PASSWORD);
            return false;
        }
    }

    /**
     * 
     * @param user
     *            this user will be registered in application.
     * @return result of registration.
     */
    public boolean registerUser(final User user) {
        if (user.getPassword().equals(user.getConfirmedPassword())) {
            if (data.registerUser(user)) {
                return true;
            } else {
                SessionBean.addMessage(Constants.MESSAGE_REGISTRATION_LOGIN_ERROR,
                        Constants.TAG_ERROR_MESSAGE_REGISTR);
            }
        } else {
            SessionBean.addMessage(Constants.MESSAGE_REGISTRATION_PASSWORDS_ERROR,
                    Constants.TAG_ERROR_MESSAGE_REGISTR);
        }
        return false;
    }

    /**
     * 
     * @return result of user's output.
     */
    public boolean logOutUser() {
        SessionBean.getSession().invalidate();
        return true;
    }

    /**
     * 
     * @return list of all users.
     */
    public List<User> getAllUsers() {
        final List<User> listUsers = data.getUsers();
        if (listUsers == null) {
            SessionBean.addMessage(Constants.MESSAGE_WRONG_VIEW, null);
        }
        return listUsers;
    }

    /**
     * 
     * @param user
     *            this user will be deleted form database.
     */
    public void deleteUserFromDB(final User user) {
        if (data.deleteUser(user)) {
            SessionBean.addMessage(Constants.MESSAGE_DELETE_USER_SUCCESS, null);
        } else {
            SessionBean.addMessage(Constants.MESSAGE_DELETE_USER_ERROR, null);
        }
    }
}
