<%-- 
    Document   : MuestraMensaje
    Created on : 27-nov-2010, 2:44:53
    Author     : sergio
--%>

<%@page import="clases.MensajePantalla"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%//=session.getAttribute("usuario")
    MensajePantalla mes=(MensajePantalla)session.getAttribute("MensajeStatus");
    if (mes!=null){
        boolean error=mes.isError();
        if (error)
            out.print("<font color=\"red\">");
        else
            out.print("<font color=\"green\">");
        out.print(mes.getTexto());
        out.print("</font>");
        session.removeAttribute("MensajeStatus");
    }


%>
