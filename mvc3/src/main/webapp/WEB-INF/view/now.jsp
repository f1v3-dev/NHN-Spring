<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%--    <%= new Date().toString()%>--%>

<html>
<head>
    <title>Now</title>
</head>
</html>
<body>
now : <%= request.getAttribute("time")%>
<br/>
name : <%= request.getAttribute("name")%>
<br/>
nick name : <%= request.getAttribute("nick")%>
</body>
