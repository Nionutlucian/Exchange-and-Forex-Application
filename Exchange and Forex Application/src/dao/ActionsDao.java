package dao;
import hibernate.Currencypairs;
import hibernate.HibernateUtil;
import hibernate.Shop;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.hibernate.type.StandardBasicTypes.INTEGER;
public class ActionsDao {

    public static LinkedList list1 = new LinkedList();//lista cu  numele perechii cumparate
    public static LinkedList list2 = new LinkedList();//lista cu valoarea perechii cumparate
    public void setList1(LinkedList list1) {
        this.list1 = list1;
    }
    public void setList2(LinkedList list2) {
        this.list2 = list2;
    }
    public static void getShopping(int id_user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LinkedList listpair = new LinkedList();
        LinkedList listvalue = new LinkedList();
        Query query = session.createQuery("from Shop where id_user =:id_user");
        query.setParameter("id_user",id_user,INTEGER);
        List<Shop> list = (List<Shop>) query.list();
        for (int i = 0; i < list.size(); i++) {
            listpair.add(list.get(i).getPairname());
            listvalue.add(list.get(i).getValue());
        }
        ActionsDao object = new ActionsDao();
        object.setList1(listpair);
        object.setList2(listvalue);
    }
}