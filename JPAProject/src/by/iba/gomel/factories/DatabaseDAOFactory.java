package by.iba.gomel.factories;

import by.iba.gomel.impl.DatabaseDAOImplDB2;
import by.iba.gomel.interfaces.DatabaseDAO;

/**
 * This factory class has one method which returns instance of database implementation using in this
 * application.
 */
public class DatabaseDAOFactory {

    /**
     * 
     * @return instance of database implementation.
     */
    public static DatabaseDAO getInstance() {
        return new DatabaseDAOImplDB2();
    }

}
