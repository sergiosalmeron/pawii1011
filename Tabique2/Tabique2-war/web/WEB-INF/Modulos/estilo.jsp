<%-- 
    Document   : estilo
    Created on : 09-feb-2011, 19:17:56
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String usrAgent=request.getHeader("User-Agent");
        boolean esMovil=(usrAgent.toLowerCase().contains("iphone"))||(usrAgent.toLowerCase().contains("android"))||(usrAgent.toLowerCase().contains("symbian"));
        if (esMovil){%>
                        <link rel="stylesheet" type="text/css" href="CSS/cssMob/style.css">
        <%          }
        else{
            int valorcss=2;
            try{
                String css=request.getSession().getAttribute("css").toString();
                valorcss=Integer.parseInt(css);
            }catch (Exception e){
            }

        %>
                        <link rel="stylesheet" type="text/css" href="CSS/css<%=valorcss%>/style.css">
        <%          }%>