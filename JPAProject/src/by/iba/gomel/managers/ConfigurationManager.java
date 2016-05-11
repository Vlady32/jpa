package by.iba.gomel.managers;

import java.util.ResourceBundle;

import by.iba.gomel.Constants;

/**
 * This service class contains method getProperty that gets information from property file 'config'.
 */
public class ConfigurationManager {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
                                                                .getBundle(Constants.FILE_CONFIG_PROPERTY);

    private ConfigurationManager() {
        // empty private constructor.
    }

    public static String getProperty(final String key) {
        return ConfigurationManager.RESOURCE_BUNDLE.getString(key);
    }
}
