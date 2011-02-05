<%-- 
    Document   : EditorMensajes
    Created on : 05-feb-2011, 11:52:10
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<% boolean enviado;
   boolean error;
%>

<h1>Editor de mensajes</h1>
    <FORM action="FuncionesTabique" method="post" Accept-Charset ="UTF-8">
        <input type="text" name="mensaje" value="Escriba aquí su mensaje">
        <input type="submit" value="Enviar">

    </FORM>