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
        <ul>
            <c:forEach var="task" items="${phase.tasks}">
                <li>
                    <h5>${task.functionalityTag}: ${task.name} - ${task.platform}</h5>
                    <ul>
                        <c:if test="${task.responsibleEmployee != null}">
                            <li> Responsible Employee: ${task.responsibleEmployee}</li>
                        </c:if>
                        <li> Assignee: ${task.responsibleEmployee}
                            <%--<c:forEach var="employeeHoursPerDay" items="${task.employeesHoursPerDay}">--%>
                                <%--${employeeHoursPerDay.employee.name} working ${employeeHoursPerDay.hoursPerDay} dailyWorkload/day;--%>
                            <%--</c:forEach>--%>
                        </li>
                        <li> Status: ${task.statusPercent}% = ${task.statusTag} </li>
                        <c:if test="${task.initialDate != null}">
                            <li> Initial date: ${task.initialDate.toString()}</li>
                            <c:choose>
                                <c:when test="${task.realEndDate != null}">
                                    <li> End date: ${task.realEndDate.toString()}</li>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${task.endDatePrediction != null}">
                                        <li> End date prediction: ${task.endDatePrediction}</li>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </ul>
                </li>
            </c:forEach>
        </ul>


        <h4>Temporal information</h4>
        <ul>
            <c:if test="${phase.hourEstimation != 0}">
                <li> Estimate: ${phase.hourEstimation} hours of work</li>
            </c:if>
            <c:if test="${phase.initialDate != null}">
                <li> Initial date: ${phase.initialDate.toString()}</li>
                <c:choose>
                    <c:when test="${phase.endDate != null}">
                        <li> End date: ${phase.endDate.toString()}</li>
                    </c:when>
                    <c:otherwise>
                        <%--<c:if test="${phase.endDatePrediction != null}">--%>
                            <li> End date prediction: ${phase.endDatePrediction.toString()}</li>
                        <%--</c:if>--%>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </ul>
        <%--<table>--%>
            <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th>Employee</th>--%>
                    <%--<th>Hour/Day in this phase</th>--%>
                <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
                <%--<c:forEach var="employeeHoursPerDay" items="${phase.employeesHoursPerDay}">--%>
                    <%--<tr>--%>
                        <%--<td>${employeeHoursPerDay.employee.name}</td>--%>
                        <%--<td>${employeeHoursPerDay.hoursPerDay}</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--<tr>--%>
                    <%--<td>TOTAL</td>--%>
                    <%--<td>${phase.totalHoursPerDay}</td>--%>
                <%--</tr>--%>
            <%--</tbody>--%>
        <%--</table>--%>
    </c:forEach>

    <h2>Client Information</h2>
    <ul>
        <li>
            Name: ${client.name}
        </li>
        <li>
            ${client.type}: ${client.CNPJCPF}
        </li>
    </ul>

</body>
</html>
