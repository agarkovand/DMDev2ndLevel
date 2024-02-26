package dmdev.mentoring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static dmdev.mentoring.util.DatabaseUtil.initDataBase;

public class AbstractDaoTest {

    protected static SessionFactory sessionFactory;
    protected Session session;

    @BeforeAll
    static void initDB() {
        Configuration cfg = new Configuration();
        cfg.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        initDataBase(sessionFactory);
    }

    @AfterAll
    static void closeSessionFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void beginTransaction() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void rollbackTransaction() {
        session.getTransaction().rollback();
        session.close();
    }
}
