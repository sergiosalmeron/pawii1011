<%-- 
    Document   : index
    Created on : 27-ene-2011, 16:06:41
    Author     : usuario_local
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    getServletConfig().getServletContext().getRequestDispatcher("/Inicio").forward(request, response);
%>
<%/*
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
*/%>
