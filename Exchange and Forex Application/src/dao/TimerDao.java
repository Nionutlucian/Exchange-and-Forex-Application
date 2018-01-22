package dao;
import hibernate.HibernateUtil;
import com.example.DeleteDao;
import com.example.ParseFunction;
import hibernate.Currencypairs;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

/**
 * Created by lucian.Nicolescu on 9/13/2017.
 */
public class TimerDao extends TimerTask {

    public void run() {
        String a = "E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\download.xml";
        String url = "http://www.bnr.ro/nbrfxrates.xml";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
            try {
                DownloadDao.downloadUsingStream(url, "E:/workspace/Craiova-Internship-2017/CcyXcg/web/WEB-INF/download.xml");
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            DeleteDao.delete();
            ParseFunction.pairs(a);//parsarea
            for (Map.Entry i:ParseFunction.currencypairs.entrySet()) {
                Currencypairs object = new Currencypairs();
                object.setPair((String) i.getKey());
                object.setValues_pairs((Double) i.getValue());
                object.setDateTime(Date.from(Instant.now()));
                session.save(object);
            }
            session.close();
       }
    }