package com.example;

/**
 * Created by lucian.Nicolescu on 9/13/2017.
 */


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import dao.DownloadDao;

public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String url = "http://www.bnr.ro/nbrfxrates.xml";
        DownloadDao object  = new DownloadDao();
        try {
            object.downloadUsingStream(url, "E:/workspace/Craiova-Internship-2017/CcyXcg/web/WEB-INF/download.xml");
        } catch (IOException e) {
            out.print("error");
            e.printStackTrace();
        }
        out.close();
    }
}
