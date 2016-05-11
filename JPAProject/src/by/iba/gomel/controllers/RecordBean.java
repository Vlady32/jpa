package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.richfaces.event.FileUploadEvent;

import by.iba.gomel.Constants;
import by.iba.gomel.business.BusinessRecord;
import by.iba.gomel.model.Record;

@ManagedBean
@ViewScoped
/**
 * This bean uses for manipulating all requests associated with record.
 */
public class RecordBean implements Serializable {

    private static final long    serialVersionUID = 1L;
    private Record               record;
    private Record               currentRecord;
    private final BusinessRecord bRecord;
    private List<Record>         listRecords;
    private List<Record>         searchedRecords;
    private int                  currentPage      = Constants.DEFAULT_PAGE;
    private int                  qualityPages;
    private String               searchPhrase;

    public RecordBean() {
        record = new Record();
        currentRecord = new Record();
        bRecord = new BusinessRecord();
    }

    /**
     * 
     * @return records from database.
     */
    public List<Record> getListRecords() {
        qualityPages = bRecord.getQualityPages();
        if (qualityPages != 0) {
            listRecords = bRecord.getAllRecords(currentPage);
        }
        return listRecords;
    }

    /**
     * 
     * @return list of pages.
     */
    public List<Integer> getValuesPages() {
        final List<Integer> values = new ArrayList<Integer>();
        for (int i = 1; i <= qualityPages; i++) {
            values.add(i);
        }
        return values;
    }

    /**
     * this method updates page.
     */
    public void updatePage() {
        final Map<String, String> mapParameters = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        final String page = mapParameters.get(Constants.ATTRIBUTE_PAGE_TYPE);
        currentPage = Integer.parseInt(page);
        getListRecords();
    }

    /**
     * 
     * @return image in base64 code.
     */
    public String getImage() {
        final Record usedRecord;
        final String servletPath = SessionBean.getRequest().getServletPath();
        if (servletPath.equals(Constants.PAGE_EDIT) || servletPath.equals(Constants.PAGE_SEARCH)) {
            usedRecord = record;
        } else {
            usedRecord = currentRecord;
        }
        if ((usedRecord.getPathFile() == null) || usedRecord.getPathFile().isEmpty()) {
            usedRecord.setPathFile(Constants.DEFAULT_PATH_NO_IMAGE);
        }
        return bRecord.getByteFile(usedRecord.getPathFile());
    }

    /**
     * 
     * @param event
     *            uses for loading image from user.
     */
    public void addImage(final FileUploadEvent event) {
        record.setPathFile(bRecord.getPathFile(event));
    }

    /**
     * Adding record.
     */
    public void addRecord() {
        if (bRecord.addRecordInDB(record)) {
            record = new Record();
        }
    }

    public void deleteRecord(final Record record) {
        bRecord.deleteRecordInDB(record);
    }

    public void changeRecord() {
        bRecord.changeRecordInDB(record);
    }

    public void searchRecord() {
        searchedRecords = bRecord.searchRecord(searchPhrase);
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(final String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<Record> getSearchedRecords() {
        return searchedRecords;
    }

    public void setSearchedRecords(final List<Record> searchedRecords) {
        this.searchedRecords = searchedRecords;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(final Record record) {
        this.record = record;
    }

    public int getQualityPages() {
        return qualityPages;
    }

    public void setQualityPages(final int qualityPages) {
        this.qualityPages = qualityPages;
    }

    public Record getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(final Record currentRecord) {
        this.currentRecord = currentRecord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    public void setListRecords(final List<Record> listRecords) {
        this.listRecords = listRecords;
    }

}
