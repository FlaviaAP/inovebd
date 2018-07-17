<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${project.name} details</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

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

        .item {
            display: inline-block;
            width: 100px;
            height: 100px;
            margin: 0 auto;
            background-color: #6CB6C0;
            border: 3px solid #6CB6C0;
            padding-bottom: 2px;
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            text-align: center;
            align-items: center;

            color:white;
            vertical-align: middle;
        }

    </style>

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-white bg-white fixed-top" style="border-bottom: 2px solid #e0e0e0;">
    <div class="container">
        <a class="navbar-brand" href="#"><img id="imageI9" src="http://www.i9access.com.br/wp-content/uploads/2015/05/logo3.png" class="responsive"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
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
        <div class="col-lg-12">
        <h1 class="mt-5 text-center">${project.name}</h1>

    <h2>Phases</h2>
    <c:forEach var="phase" items="${phases}">
        <h3>Phase ${phase.number}</h3>
        <p>${phase.observation}</p>

                <h4>Task</h4>
                <ul>
                    <c:forEach var="task" items="${phase.tasks}">
                        <li>
                            <h5>${task.functionalityTag}: ${task.name} - ${task.platform}</h5>
                            <ul>
                                <c:if test="${task.responsibleEmployee != null}">
                                    <li> Responsible Employee: ${task.responsibleEmployee}</li>
                                </c:if>
                                <li> Assigneds Employees: </li>
                                <ul>
                                    <c:forEach var="employeeAssignedToTask" items="${task.employeesAssignedToTask}">
                                        <li>${employeeAssignedToTask.name} worked ${employeeAssignedToTask.dailyHoursPercentage}% of his/hers time on this task</li>
                                    </c:forEach>
                                </ul>
                                <li>Estimated ${task.hourEstimation} Hours</li>
                                <li> Status: ${task.statusPercent}% = ${task.statusTag} </li>
                                <c:if test="${task.initialDate != null}">
                                    <li> Initial date: ${task.initialDate.format(timeFormatter)}</li>
                                    <c:choose>
                                        <c:when test="${task.realEndDate != null}">
                                            <li> End date: ${task.realEndDate.format(timeFormatter)}</li>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${task.endDatePrediction != null}">
                                                <li> End date prediction: ${task.localDateEndDatePrediction.format(timeFormatter)}</li>
                                            </c:if>
                                            <c:if test="${task.endDatePrediction == null}">
                                                <li> End date prediction: Undefined</li>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </ul>
                        </li>
                    </c:forEach>
                    <form action="/project/createTask" method="get">
                        <input type="hidden" name="phaseId" value="${phase.id}">
                        <button>Add Task To Phase</button>
                    </form>
                </ul>


                <h4>Phase ${phase.number} Temporal information</h4>
                <ul>
                    <c:if test="${phase.hourEstimation != 0}">
                        <li> Estimate: ${phase.hourEstimation} hours of work</li>
                    </c:if>
                    <c:if test="${phase.initialDate != null}">
                        <li> Initial date: ${phase.initialDate.format(timeFormatter)}</li>
                        <c:choose>
                            <c:when test="${phase.endDate != null}">
                                <li> End date: ${phase.endDate.format(timeFormatter)}</li>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${phase.endDatePrediction != null}">
                                <li> End date prediction: ${phase.localDateEndDatePrediction    .format(timeFormatter)}</li>
                                </c:if>
                                <c:if test="${phase.endDatePrediction == null}">
                                    <li> End date prediction: Undefined</li>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </ul>
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
        </div>
    </div>
</div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>
