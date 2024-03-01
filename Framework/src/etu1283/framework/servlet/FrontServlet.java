package etu1283.framework.servlet;


import etu1283.framework.Mapping;
import etu1283.framework.util.Util;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;


public class FrontServlet extends HttpServlet {
    private HashMap<String, Mapping> mappingUrls;
    private Util util;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {

            this.util = new Util();
            this.mappingUrls = new HashMap<>();
            final String tomPath = "/WEB-INF/classes/";
            String path = getServletContext().getRealPath(tomPath);
            util.loadMapping(path, tomPath, mappingUrls);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException ,IOException{
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processRequest(HttpServletRequest request , HttpServletResponse response) throws Exception {
        String url = util.processUrl(request.getRequestURL().toString(), request.getContextPath());
        Mapping mapping = mappingUrls.get(url);

        if (mapping == null) throw new Exception("Ressources not found");

        System.out.println(mapping.getClassName() +" | "+ mapping.getMethod());
    }
}
