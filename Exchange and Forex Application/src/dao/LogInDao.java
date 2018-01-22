package dao;
import hibernate.HibernateUtil;
import jdk.nashorn.internal.codegen.types.Type;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import java.sql.*;
import java.util.List;
import static org.hibernate.type.StandardBasicTypes.INTEGER;

public class LogInDao {
    public static boolean validate(String username, String pass, int type_user) {
        boolean status = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from  Users where username = :username and password = :pass and type =:type_user");
            query.setParameter("username", username);
            query.setParameter("pass", pass);
            query.setParameter("type_user",type_user, INTEGER );
            List list = query.list();
            if(list.size() >0){
               status = true;
           }
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    return status;
    }
}