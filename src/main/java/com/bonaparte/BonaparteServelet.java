package com.bonaparte;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yangmingquan on 2018/7/21.
 * Servlet技术
 */
@WebServlet(urlPatterns = "/bonaparte/*")
public class BonaparteServelet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("-------Bonaparte doGet---------");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        printWriter.println("<HTML>");
        printWriter.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        printWriter.println("  <BODY>");
        printWriter.print("    This is ");
        printWriter.print(this.getClass());
        printWriter.println(", using the get method");
        printWriter.println("  </BODY>");
        printWriter.println("</HTML>");
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("-------Bonaparte doPost---------");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        printWriter.println("<HTML>");
        printWriter.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        printWriter.println("  <BODY>");
        printWriter.print("    This is ");
        printWriter.print(this.getClass());
        printWriter.println(", using the POpostST method");
        printWriter.println("  </BODY>");
        printWriter.println("</HTML>");
        printWriter.flush();
        printWriter.close();
    }
}
