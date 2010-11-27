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
        <link rel="stylesheet" type="text/css" href="Estilo/style.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Tabique</title>
    </head>
    <body>
        <div id="page">
            <div id="header">
                <jsp:include page="Modulos/Opciones.jsp" />
            </div>
        

        <% 
        
            switch (us.getRol()){
                case Invitado: %>
                    <div id="contentarea">
                        <jsp:include page="Modulos/VisorTabique.jsp" />
                    </div>
                    <%; break;
                case Autorizado:%>
                    <div id="contentarea">
                        <jsp:include page="Modulos/MuestraMensaje.jsp" />
                        <jsp:include page="Modulos/VisorTabique.jsp" />
                    
                    </div>
                    <div id="sidebar">
                        <jsp:include page="Modulos/EditorMensajes.jsp" />
                    </div>
                    <%; break;
                case Administrador:%> 
                    <div id="contentarea">
                        <jsp:include page="Modulos/MuestraMensaje.jsp" />
                        <jsp:include page="Modulos/VisorUsuarios.jsp" />
                    </div>
                    <div id="sidebar">
                    
                        <jsp:include page="Modulos/CreadorUsuarios.jsp" />
                    </div>
                    <%; break;
            }
         %>
        
        <div id="footer">
            Creado por Sergio Salmerón y Aleix Garrido
        </div>
        </div>
    </body>
</html>
<% }%>