package by.iba.gomel.managers;

import java.util.ResourceBundle;

import by.iba.gomel.Constants;

/**
 * This service class contains method getProperty that gets information from property file
 * 'requests_db2'.
 */
public class RequestManager {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
                                                                .getBundle(Constants.FILE_REQUESTS_PROPERTY);

    private RequestManager() {
        // private empty constructor.
    }

    public static String getProperty(final String key) {
        return RequestManager.RESOURCE_BUNDLE.getString(key);
    }

}
