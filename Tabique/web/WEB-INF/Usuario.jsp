<%-- 
    Document   : Usuario
    Created on : 26-nov-2010, 18:30:09
    Author     : Aleix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="clases.Usuario"%>
<%@page import="clases.Rol"%>

<%
    Usuario us= (Usuario)session.getAttribute("usuario");
    if (us==null){
        RequestDispatcher fwd = request.getRequestDispatcher("../Inicio");
        try{
            fwd.forward(request,response);
        }catch(Exception se){
            System.out.println("Action.forward: error en el redireccionamiento "+se);
        }
    }
    else{
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Tabique</title>
    </head>
    <body>
        <jsp:include page="Modulos/Opciones.jsp" />
        <% 
        
            switch (us.getRol()){
                case Invitado: %>   
                    <jsp:include page="Modulos/VisorTabique.jsp" />
                    <%; break;
                case Autorizado:%>
                    <jsp:include page="Modulos/VisorTabique.jsp" />
                    <jsp:include page="Modulos/MuestraMensaje.jsp" />>
                    <jsp:include page="Modulos/EditorMensajes.jsp" />
                    <%; break;
                case Administrador:%> 
                    <jsp:include page="Modulos/CreadorUsuarios.jsp" />
                    <jsp:include page="Modulos/MuestraMensaje.jsp" />
                    <jsp:include page="Modulos/VisorUsuarios.jsp" />
                    <%; break;
            }
         %>
    </body>
</html>
<% }%>