package dao;
import hibernate.Currencypairs;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import static org.hibernate.type.StandardBasicTypes.INTEGER;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class ParseDao {
    /*.................................................................................................................*/
    public static ArrayList<String> pairs = new ArrayList<>();
    public static ArrayList<Double> values = new ArrayList<>();
    public  void setPairs ( ArrayList<String> pairs){
        this.pairs = pairs;
    }
    public void setValues ( ArrayList<Double> values){
        this.values = values;
    }
    public static void getcurrency() {
        ArrayList<String> pairs1 = new ArrayList<>();
        ArrayList<Double> values1 = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from  Currencypairs");
        ArrayList<Currencypairs> list = (ArrayList<Currencypairs>)query.list();
        for(int i=0;i<list.size();i++) {
        pairs1.add(list.get(i).getPair());
        values1.add(list.get(i).getValues_pairs());
        }
        ParseDao object = new ParseDao();
        object.setPairs(pairs1);
        object.setValues(values1);
        session.getTransaction().commit();
    }
}

