<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%! 
    @SuppressWarnings("unchecked")
    public List<String> castToSafeList(Object obj) {
        return (List<String>) obj;
    }
%>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8">
    <title>Logueado</title>
    
    
</head>
<body>
    <h1>Datos del Usuario</h1>
    <ul>
        <%
            List<String> usuario = (List<String>) request.getAttribute("usuario");
            for (String us : usuario) {
                out.println("<li>" + us + "</li>");
            }
        %>
    </ul>
</body>
</html>
