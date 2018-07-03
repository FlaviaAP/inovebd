<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
    <title>Empregados</title>
</head>
<body>

    <div>
        <table>
            <thead>
            <th>Nome</th>
            <th>Idade</th>
            </thead>
        <c:forEach var="empregado" items="${empregados}">
            <tr>
                <td>${empregado.name}</td>
                <td>${empregado.age}</td>
            </tr>
        </c:forEach>
        </table>
    </div>

</body>
</html>
