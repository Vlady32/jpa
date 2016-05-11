package by.iba.gomel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import by.iba.gomel.Constants;

/**
 * This class contains fields and methods for working with records.
 */
@Entity
@Table(name = Constants.TABLE_NAME_RECORDS)
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int               item;
    @Column(nullable = false)
    private String            fullName         = null;
    @Column(nullable = false)
    private String            address          = null;
    @Column(nullable = false)
    private String            phoneNumber      = null;
    @Temporal(TemporalType.TIMESTAMP)
    private Date              creationDate;
    private String            mail             = null;
    @Temporal(TemporalType.DATE)
    private Date              birthDate        = null;
    private String            pathFile         = null;

    public Record() {
        // Empty constructor.
    }

    @PrePersist
    public void createTimestamp() {
        creationDate = new Date();
    }

    public int getItem() {
        return item;
    }

    public void setItem(final int item) {
        this.item = item;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

}
