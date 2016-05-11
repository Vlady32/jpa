package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import by.iba.gomel.Constants;
import by.iba.gomel.business.BusinessUser;
import by.iba.gomel.model.User;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private static final long  serialVersionUID = 1L;
    private User               user;
    private final BusinessUser bUser;

    public UserBean() {
        bUser = new BusinessUser();
        user = new User();
    }

    /**
     * 
     * @return list of registered users.
     */
    public List<User> getListUsers() {
        return bUser.getAllUsers();
    }

    /**
     * 
     * @return result of logging.
     */
    public String signIn() {
        if (bUser.validateUser(user.getUserName(), user.getPassword())) {
            return Constants.RESULT_SUCCESS;
        }
        return Constants.RESULT_FAIL;
    }

    /**
     * 
     * @return result of registration user.
     */
    public String register() {
        if (bUser.registerUser(user)) {
            return Constants.RESULT_SUCCESS;
        }
        return null;
    }

    /**
     * 
     * @return result of output user.
     */
    public String logOut() {
        if (bUser.logOutUser()) {
            return Constants.RESULT_LOG_OUT;
        }
        return null;
    }

    /**
     * 
     * @param user
     *            deleting this user from database.
     */
    public void deleteUser(final User user) {
        bUser.deleteUserFromDB(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
