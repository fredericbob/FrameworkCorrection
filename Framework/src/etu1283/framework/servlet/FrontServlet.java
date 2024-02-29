package etu1283.framework.servlet;


import etu1283.framework.Mapping;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;


public class FrontServlet extends HttpServlet {
    private HashMap<String, Mapping> mappingUrls;

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

}

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException ,IOException{

    }
}
