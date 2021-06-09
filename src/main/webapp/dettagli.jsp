<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="it.rdev.rubrica.model.entities.Email" %>
<%@ page import="it.rdev.rubrica.model.entities.Phone" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%  
		List<Email> emails = (List<Email>) request.getAttribute("emails");
	    List<Phone> cell = (List<Phone>) request.getAttribute("cell");
 	%>
	<table>
	<thead>
		<tr><td>Email</td></tr><%--Crea una tabella dove ci sono due colonne ,una nomi e una cognomi --%>
	</thead>
	<tbody>
	<c:forEach items="${emails}" var="u">  <%--Scorre tutte le tuple  --%>
		<tr>
			<td><c:out value="${u.email }"></c:out></td>  <%--Inserisce in un colonna nome  --%>			
		</tr>
	</c:forEach>
	</tody>
	</table>
	
	<p>
	--------------------------------------------------
	</p>
	<table>
	<thead>
		<tr><td>Cell</td></tr><%--Crea una tabella dove ci sono due colonne ,una nomi e una cognomi --%>
	</thead>
	<tbody>
	<c:forEach items="${cell}" var="i">  <%--Scorre tutte le tuple  --%>
		<tr>
			<td><c:out value="${i.phone }"></c:out></td>  <%--Inserisce in un colonna nome  --%>			
		</tr>
	</c:forEach>
	</tody>
	</table>
	


<a href="/RubricaWeb/">Home</a>
</body>
</html>