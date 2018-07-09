<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
    <title>${project.name} details</title>
</head>
<body>
    <h1>${project.name}</h1>

    <h2>Tasks</h2>
    <!-- here will be all the tasks-->

    <h2>Phase</h2>
    <c:forEach var="phase" items="${phases}">
        <h3>${phase.number}</h3>
        <p>${phase.observation}</p>
        <br>
        <table>
            <thead>
                <tr>
                    <th>Employee</th>
                    <th>Hour/Day in this phase</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="employeeHoursPerDay" items="${phase.employeesHoursPerDay}">
                    <tr>
                        <td>${employeeHoursPerDay.employee.name}</td>
                        <td>${employeeHoursPerDay.hoursPerDay}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:forEach>

    <h2>Client Information</h2>
    <ul>
        <li>
            Name: ${client.name}
        </li>
        <li>
            <c:choose>
                <c:when test='${client.type.compareToIgnoreCase("J") != 0}'>
                    CPF:
                </c:when>
                <c:otherwise>
                    CNPJ:
                </c:otherwise>
            </c:choose>
            ${client.CNPJCPF}
        </li>
    </ul>

</body>
</html>
