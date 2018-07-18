<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${project.name} details</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <style>
        body {
            padding-top: 54px;
        }

        @media (min-width: 992px) {
            body {
                padding-top: 56px;
            }
        }

        .responsive {
            width: 50%;
            height: auto;
        }

    </style>

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-white bg-white fixed-top" style="border-bottom: 2px solid #e0e0e0;">
    <div class="container">
        <a class="navbar-brand" href="#"><img id="imageI9"
                                              src="http://www.i9access.com.br/wp-content/uploads/2015/05/logo3.png"
                                              class="responsive"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/home">Home
                        <span class="sr-only">(current)</span>
                    </a>
                <li class="nav-item">
                    <a class="nav-link" href="mailto:gcattani@gmail.com">Report a bug</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-lg-18">
            <h1 class="mt-5 text-center">${project.name}</h1>

            <h2>Client Information</h2>
            <ul>
                <li>
                    Name: ${client.name}
                </li>
                <li>
                    ${client.type}: ${client.CNPJCPF}
                </li>
            </ul>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th colspan="2"></th>
                        <th colspan="9" style="border-left: 2px solid #e0e0e0;">Tasks</th>
                        <th colspan="4" style="border-left: 2px solid #e0e0e0;">Temporal information</th>
                    </tr>
                    <tr>
                        <th>Phase</th>
                        <th>Detail</th>
                        <th style="border-left: 2px solid #e0e0e0;">Tasks</th>
                        <th>Platform</th>
                        <th>Responsible Employee</th>
                        <th>Status</th>
                        <th>#</th>
                        <th>Initial date</th>
                        <th>End date</th>
                        <th>End date prediction</th>
                        <th>Assigned Employees</th>

                        <th style="border-left: 2px solid #e0e0e0;">Estimate</th>
                        <th>Initial date</th>
                        <th>End date</th>
                        <th>End date prediction</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="phase" items="${phases}">
                        <tr>
                            <td><b>${phase.number}</b></td>
                            <td><b>${phase.observation}</b></td>
                        </tr>
                        <c:choose>
                            <c:when test="${phase.tasks != []}">
                                <c:forEach var="task" items="${phase.tasks}">
                                    <c:forEach var="employeeAssignedToTask" items="${task.employeesAssignedToTask}">
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td style="border-left: 2px solid #e0e0e0;">${task.functionalityTag} ${task.name}</td>
                                            <td>${task.platform}</td>

                                            <c:choose>
                                                <c:when test="${task.responsibleEmployee != null}">
                                                    <td><b>${task.responsibleEmployee}</b></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>

                                            <td>${task.statusPercent}%</td>
                                            <td>${task.statusTag} </td>

                                            <c:choose>
                                                <c:when test="${task.initialDate != null}">
                                                    <td>${task.initialDate.toString()}</td>
                                                    <c:choose>
                                                        <c:when test="${task.realEndDate != null}">
                                                            <td>${task.realEndDate.toString()}</td>
                                                            <td></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:if test="${task.endDatePrediction != null}">
                                                                <td></td>
                                                                <td>${task.endDatePrediction}</td>
                                                            </c:if>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>

                                                <c:otherwise>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>

                                            <td><b>${employeeAssignedToTask.name}</b> worked
                                                <b>${employeeAssignedToTask.dailyHoursPorcentage}%</b> of his/hers time
                                                on this
                                                task
                                            </td>

                                            <c:choose>
                                                <c:when test="${phase.hourEstimation != 0}">
                                                    <td style="border-left: 2px solid #e0e0e0;"> ${phase.hourEstimation}
                                                        hours
                                                        of work
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${phase.initialDate != null}">
                                                    <td>${phase.initialDate.toString()}</td>
                                                    <c:choose>
                                                        <c:when test="${phase.endDate != null}">
                                                            <td>${phase.endDate.toString()}</td>
                                                            <td></td>
                                                        </c:when>
                                                        <c:when test="${phase.endDatePrediction != null}">
                                                            <td></td>
                                                            <td>${phase.endDatePrediction.toString()}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td></td>
                                                            <td></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>

                                                <c:otherwise>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>

                                    </c:forEach>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                <c:choose>
                                    <c:when test="${phase.hourEstimation != 0}">
                                        <td style="border-left: 2px solid #e0e0e0;"> ${phase.hourEstimation}
                                            hours
                                            of work
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>

                                    <c:choose>
                                        <c:when test="${phase.initialDate != null}">
                                            <td>${phase.initialDate.toString()}</td>
                                            <c:choose>
                                                <c:when test="${phase.endDate != null}">
                                                    <td>${phase.endDate.toString()}</td>
                                                    <td></td>
                                                </c:when>
                                                <c:when test="${phase.endDatePrediction != null}">
                                                    <td></td>
                                                    <td>${phase.endDatePrediction.toString()}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td></td>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
