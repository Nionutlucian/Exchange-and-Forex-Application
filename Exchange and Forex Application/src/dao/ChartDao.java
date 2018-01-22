package dao;
import hibernate.Currencypairs;
import hibernate.HibernateUtil;
import hibernate.Hisotryvalues;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
public class ChartDao {
    public static LinkedList<Double> valuesearch;
    public static  String pairsearch;
    public static LinkedList<String> datasearch;
    public  void setpair(String pairsearch){this.pairsearch=pairsearch;}
    public void setValues ( LinkedList<Double> valuesearch){ this.valuesearch = valuesearch;}
    public void setdate(LinkedList<String> datasearch){this.datasearch=datasearch;}
    public static void chartpair(String pair) {

        String pairs = null;
        LinkedList<Double> value = new LinkedList<>();
        LinkedList<String> date = new LinkedList<>();
        DateFormat df = new SimpleDateFormat("MM dd ");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Hisotryvalues where pair =:pair");
        query.setParameter("pair", pair);
        List<Hisotryvalues> list = (List<Hisotryvalues>) query.list();
        System.out.println(query.list());
        for (int i = 0; i < list.size(); i++) {
            pairs = list.get(i).getPair();
            value.add(list.get(i).getValue());
            date.add(String.valueOf(df.format(list.get(i).getDate())));
        }

        ChartDao object = new ChartDao();
        object.setpair(pairs);
        object.setValues(value);
        object.setdate(date);

        session.getTransaction().commit();
        session.close();
    }
}