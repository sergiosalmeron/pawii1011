<%-- 
    Document   : estilo
    Created on : 09-feb-2011, 19:17:56
    Author     : sergio
--%>

<%@page import="utilesInterfaz.DetectorMovil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%  if (DetectorMovil.esMovil(request)){%>
       <link rel="stylesheet" type="text/css" href="CSS/cssMob/style.css">
                                    <%}
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