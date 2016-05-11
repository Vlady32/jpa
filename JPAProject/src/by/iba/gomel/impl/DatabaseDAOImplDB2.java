package by.iba.gomel.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.managers.RequestManager;
import by.iba.gomel.model.Record;
import by.iba.gomel.model.User;

/**
 * This class realizes all methods associated with working in database.
 */
public class DatabaseDAOImplDB2 implements DatabaseDAO, Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory
                                                         .getLogger(DatabaseDAOImplDB2.class);
    final EntityManagerFactory  emf              = Persistence
                                                         .createEntityManagerFactory(Constants.PERSISTENT_NAME_DB2);

    @Override
    public User checkUser(final String userName, final String password) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        final TypedQuery<User> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_CHECK_USER), User.class);
        query.setParameter(Constants.FIRST_PARAMETER, userName);
        query.setParameter(Constants.SECOND_PARAMETER, password);
        final User user;
        et.begin();
        try {
            user = query.getSingleResult();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        } finally {
            et.commit();
            em.close();
        }
        return user;
    }

    @Override
    public boolean registerUser(final User user) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(user);
            et.commit();
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public List<Record> getRecords(final int start) {
        List<Record> listRecords;
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        final TypedQuery<Record> query = em
                .createQuery(RequestManager.getProperty(Constants.PROPERTY_GET_ALL_RECORDS),
                        Record.class).setFirstResult(start)
                .setMaxResults((int) Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE);
        et.begin();
        try {
            listRecords = query.getResultList();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        } finally {
            et.commit();
            em.close();
        }
        return listRecords;
    }

    @Override
    public long getQualityRecords() {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        final TypedQuery<Long> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_GET_QUALITY_RECORDS), Long.class);
        Long qualityRecords = Constants.NUMBER_ZERO;
        et.begin();
        try {
            qualityRecords = query.getSingleResult();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return qualityRecords;
        } finally {
            et.commit();
            em.close();
        }
        return qualityRecords;
    }

    @Override
    public boolean addRecord(final Record record) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(record);
            et.commit();
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public Record checkExistRecord(final Record record) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        final TypedQuery<Record> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_CHECK_EXIST_RECORD), Record.class);
        query.setParameter(Constants.FIRST_PARAMETER, record.getPhoneNumber());
        Record gotRecord;
        et.begin();
        try {
            gotRecord = query.getSingleResult();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        } catch (final NonUniqueResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NON_UNIQUE_RESULT_EXCEPTION, e);
            return new Record();
        } finally {
            et.commit();
            em.close();
        }
        return gotRecord;
    }

    @Override
    public boolean changeRecord(final Record record) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        et.begin();
        if (em.find(Record.class, record.getItem()) == null) {
            return false;
        }
        em.merge(record);
        et.commit();
        em.close();
        return true;
    }

    @Override
    public boolean deleteRecord(final Record record) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.merge(record));
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        } finally {
            et.commit();
            em.close();
        }
        return true;
    }

    @Override
    public List<User> getUsers() {
        final List<User> listUsers;
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        final TypedQuery<User> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_GET_USERS), User.class);
        et.begin();
        try {
            listUsers = query.getResultList();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        } finally {
            et.commit();
            em.close();
        }
        return listUsers;
    }

    @Override
    public boolean deleteUser(final User user) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.merge(user));
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        } finally {
            et.commit();
            em.close();
        }
        return true;
    }

    @Override
    public List<Record> searchRecord(final String searchPhrase) {
        List<Record> listRecords;
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        final TypedQuery<Record> query;
        query = em.createQuery(RequestManager.getProperty(Constants.PROPERTY_SEARCH_ALL_COLUMNS),
                Record.class);
        query.setParameter(Constants.FIRST_PARAMETER, Constants.PERCENT + searchPhrase
                + Constants.PERCENT);
        et.begin();
        try {
            listRecords = query.getResultList();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        } finally {
            et.commit();
            em.close();
        }
        return listRecords;
    }
}
