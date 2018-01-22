package dao;
import hibernate.Currencypairs;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class SearchDao {

    public static LinkedList list1 = new LinkedList();

    public void setList1(LinkedList list1) {
        this.list1 = list1;
    }

    public static LinkedList list2 = new LinkedList();

    public void setList2(LinkedList list2) {
        this.list2 = list2;
    }

    public static void searchPair(String pairtosearch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LinkedList listpair = new LinkedList();
        LinkedList listvalue = new LinkedList();
        Query query = session.createQuery("from Currencypairs where pair like :pairtosearch");
        query.setParameter("pairtosearch", "%" + pairtosearch + "%");
        List<Currencypairs> list = (List<Currencypairs>) query.list();
        for (int i = 0; i < list.size(); i++) {
            listpair.add(list.get(i).getPair());
            listvalue.add(list.get(i).getValues_pairs());
        }
        SearchDao object = new SearchDao();
        object.setList1(listpair);
        object.setList2(listvalue);
    }
}