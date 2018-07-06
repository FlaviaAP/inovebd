<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>i9 Project Manager</h1>
    <ul>
        <c:forEach var="project" items="${projects}">
            <li>
                <a href="project/details?projectId=${project.id}">${project.name}<a/>
            </li>
        </c:forEach>
    </ul>

</body>
</html>
