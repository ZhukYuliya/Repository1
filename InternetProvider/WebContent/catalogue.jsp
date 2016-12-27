<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Title</title>
</head>
<body>
<%@include file="WEB-INF/jsp_fragments/header.jsp" %>
<br>
<br>
<table border="1">
      <tr>
       <td><fmt:message key="book_description"/></td>
       <td><fmt:message key="book_genre"/></td>
      </tr>
      <c:forEach var="book" items="${catalogue}">
        <tr>
         <td>${book.description}</td>
         <td>${book.genre.name}</td>
        </tr>
      </c:forEach>     
     </table>
<br>
<br>
<br>
<fmt:message key="want_to_add"/>
<br>
<br>
<form action="controller" method="post">
		<input type="hidden" name="command" value="add_book" /> 
		<fmt:message key="book_description"/>:<br />
		<input type="text" name="description" value="" /><br /> 
		<fmt:message key="book_genre"/>:<br /> 
		<input type="text" name="genre" value="" /><br /> 
		<input type="submit" value= '<fmt:message key="add"/> ' />
	</form>

<br>
<c:if test="${not empty addBookMessage}"> 
   <fmt:message key="${addBookMessage}"/> </c:if>

<br>
<br>

</body>
</html>


    