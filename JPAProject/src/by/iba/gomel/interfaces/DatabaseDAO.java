package by.iba.gomel.interfaces;

import java.util.List;

import by.iba.gomel.model.Record;
import by.iba.gomel.model.User;

/**
 * This interface defines all methods for working with database in this application.
 */
public interface DatabaseDAO {

    /**
     * 
     * @param userName
     *            userName for checking.
     * @param password
     *            password for checking.
     * @return found user.
     */
    public User checkUser(String userName, String password);

    /**
     * 
     * @param user
     *            user for registration in database.
     * @return result of registration.
     */
    public boolean registerUser(User user);

    /**
     * 
     * @return list of users.
     */
    public List<User> getUsers();

    /**
     * 
     * @param user
     *            this user will be deleted from database.
     * @return result of operation.
     */
    public boolean deleteUser(User user);

    /**
     * 
     * @param start
     *            position for start.
     * @return list of record on certain page.
     */
    public List<Record> getRecords(int start);

    /**
     * 
     * @return quality records.
     */
    public long getQualityRecords();

    /**
     * 
     * @param record
     *            this record will be added in database.
     * @return result of adding.
     */
    public boolean addRecord(Record record);

    /**
     * 
     * @param record
     *            checking on existing record in database.
     * @return found record.
     */
    public Record checkExistRecord(Record record);

    /**
     * 
     * @param record
     *            this record will be changed in database.
     * @return result of operation.
     */
    public boolean changeRecord(Record record);

    /**
     * 
     * @param record
     *            this record will be deleted from database.
     * @return result of operation.
     */
    public boolean deleteRecord(Record record);

    /**
     * 
     * @param searchPhrase
     *            phrase for searching in database.
     * @return list of found records.
     */
    public List<Record> searchRecord(String searchPhrase);

}
