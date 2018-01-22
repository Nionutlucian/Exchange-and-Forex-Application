package dao;
import hibernate.Currencypairs;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
public class SelectActualValueDao {
    public static HashMap<Double, String> pairsmap = new HashMap<Double, String>();
    public void setMap(HashMap<Double, String> pairsmap) { this.pairsmap = pairsmap; }

    public static void select() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<Double, String> pairsmap = new HashMap<Double, String>();
        Query query = session.createQuery("from Currencypairs");
        List<Currencypairs> list = (List<Currencypairs>) query.list();
        for (int i = 0; i < list.size(); i++) {
            pairsmap.put(list.get(i).getValues_pairs(), list.get(i).getPair());
        }
        SelectActualValueDao object = new SelectActualValueDao();
        object.setMap(pairsmap);
        session.getTransaction().commit();
    }
}