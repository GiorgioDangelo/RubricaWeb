<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="it.rdev.rubrica.model.entities.Contact" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- 	<jsp:useBean id="user" scope="request" class="it.rdev.rubrica.dto.User"> --%>
<%-- 		<jsp:setProperty name="user" property="*"/> --%>
<%-- 	</jsp:useBean> --%>
	
<%-- 	<%=user.getUsername()  + " " + user.getPassword() %> --%>
	<%  
		List<Contact> users = (List<Contact>) request.getAttribute("users");
 	%>
	<table>
	<thead>
		<tr><td>Name</td><td>surname</td></tr><%--Crea una tabella dove ci sono due colonne ,una nomi e una cognomi --%>
	</thead>
	<tbody>
	<c:forEach items="${users}" var="u">  <%--Scorre tutte le tuple  --%>
		<tr>
			<td><c:out value="${u.name }"></c:out></td>  <%--Inserisce in un colonna nome  --%>
			<td><c:out value="${u.surname }"></c:out></td><%--e in questa colonna cognome --%>
             <td><a href="DeleteServlet?id=<c:out value='${u.id}' />" >elimina</a></td>
			
		</tr>
	</c:forEach>
	</tody>
	</table>

<a href="secure/SecureResourceServlet">Vai in login</a>



<form method="post" action="/RubricaWeb/Inserimento">
		<p>
			Username: <input type="text" name="username">
		</p>
		<p>
			Surname: <input type="text" name="surname">
		</p>
		<p>
			Email: <input type="text" name="email">
		</p>
      <p>Phone: <input type="text" name="phone"></p>  		
		<button type="submit">Inserimento</button>
	</form>
	
	<p>
--------------------------------------------------------------------------	
	</p>
	Cambia il nome utente e cognome inserendo l'id	
	<form method="post" action="/RubricaWeb/UpdateServlet">
		<p>
			ID:<input type="text" name="ID">
		</p>
		<p>
			Name: <input type="text" name="name">
		</p>
		<p>
			Surname: <input type="text" name="surname">
		</p>
<%--<p>Phone: <input type="text" name="phone"></p>  --%>		
		<button type="submit">Update</button>
	</form>
  <%--Praticamente ti manda nella servelet  SecureResourceServerlet --%>
</body>  <%--Però attenzione poichè c'è la parola chiave Secure prima di andare in quella Servelet viene filtrato dal filtro  --%>
<%--nel package filter AuthFilter che intercetta tutti i percorsi che hanno al loro interno la parola chiave Secure --%>
</html>