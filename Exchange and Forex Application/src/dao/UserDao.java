package dao;
import hibernate.HibernateUtil;
import hibernate.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.hibernate.type.StandardBasicTypes.INTEGER;

/**
 * Created by lucian.Nicolescu on 9/11/2017.
 */
public class UserDao {
    public static String name;
    public static String password;
    public static int type;
    public static String username;
    public static String email;
    public static Double balance;
    public static String currency;

    public static int  getID(String name,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int id=0;
        try {
            Query query = session.createQuery("SELECT id from Users where name =:name and password =:password ");
            query.setParameter("name", name);
            query.setParameter("password", password);
            id= (int) query.uniqueResult();
            System.out.println(id);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return id;
    }
}
