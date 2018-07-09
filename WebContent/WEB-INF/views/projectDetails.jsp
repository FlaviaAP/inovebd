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

    <h2>Phases</h2>
    <c:forEach var="phase" items="${phases}">
        <h3>Phase ${phase.number}</h3>
        <p>${phase.observation}</p>

        <h4>Tasks</h4>
        <!-- here will be all the tasks of this phase-->


        <h4>Temporal information</h4>
        <ul>
            <c:if test="${phase.hourEstimation != 0}">
                <li> Estimate: ${phase.hourEstimation} hours = ${phase.dayEstimation} days</li>
            </c:if>
            <c:if test="${phase.initialDate != null}">
                <li> Initial date: ${phase.initialDate.toString()}</li>
                <c:choose>
                    <c:when test="${phase.endDate != null}">
                        <li> End date: ${phase.endDate.toString()}</li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${phase.endDatePrediction != null}">
                            <li> End date prediction: ${phase.endDatePrediction.toString()}</li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </ul>
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
                <tr>
                    <td>TOTAL</td>
                    <td>${phase.totalHoursPerDay}</td>
                </tr>
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
