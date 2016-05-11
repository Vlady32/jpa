package by.iba.gomel.managers;

import java.util.Locale;
import java.util.ResourceBundle;

import by.iba.gomel.Constants;

/**
 * This service class contains method getProperty that gets information from property file
 * 'messages'.
 */
public class MessageManager {

    private final ResourceBundle RESOURCE_BUNDLE;

    public MessageManager(final Locale locale) {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(Constants.FILE_MESSAGES_PROPERTY, locale);
    }

    public String getProperty(final String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}
