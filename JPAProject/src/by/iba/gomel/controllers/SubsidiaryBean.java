package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.MessageManager;

@ManagedBean(name = "subBean")
@SessionScoped
/**
 * This bean uses for additional requests.
 */
public class SubsidiaryBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<String>      listItemsMenu;
    private String            linkPage;

    public SubsidiaryBean() {
        // Empty constructor
    }

    /**
     * Initialization items of menu.
     */
    private void initList() {
        listItemsMenu = new ArrayList<String>();
        final Locale currentLocale = new SessionBean().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        if (SessionBean.getUserType().equals(Constants.TYPE_ADMIN)) {
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_ADDING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_EDITING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_CONTROL));
        } else if (SessionBean.getUserType().equals(Constants.TYPE_USER)) {
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING));
        }
    }

    /**
     * 
     * @return linkPage.
     */
    public String determineAction() {
        final Locale currentLocale = new SessionBean().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        final Map<String, String> mapParameters = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        final String linkType = mapParameters.get(Constants.ATTRIBUTE_LINK_TYPE);
        if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING))) {
            linkPage = Constants.PAGE_VIEW;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_ADDING))) {
            linkPage = Constants.PAGE_ADD;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_EDITING))) {
            linkPage = Constants.PAGE_EDIT;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING))) {
            linkPage = Constants.PAGE_SEARCH;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_CONTROL))) {
            linkPage = Constants.PAGE_CONTROL;
        }
        return linkPage;
    }

    public String getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(final String linkPage) {
        this.linkPage = linkPage;
    }

    public List<String> getListItemsMenu() {
        initList();
        return listItemsMenu;
    }

    public void setListItemsMenu(final List<String> listItemsMenu) {
        this.listItemsMenu = listItemsMenu;
    }

}
