package jm.task.core.jdbc.dao;

import jakarta.persistence.criteria.CriteriaQuery;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS diya_userov.users" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").addEntity(User.class).executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx1 != null) {
                tx1.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE users").executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx1 != null) {
                tx1.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.merge(new User(name, lastName, age));
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx1 != null) {
                tx1.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.remove(session.get(User.class, id));
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx1 != null) {
                tx1.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            tx1.commit();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            tx1.rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx1 != null) {
                tx1.rollback();
            }
        } finally {
            session.close();
        }
    }
}
