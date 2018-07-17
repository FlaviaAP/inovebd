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

    <title>Create Task</title>

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
        .createTaskBody{
            margin-top:50px;
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
        <div class="createTaskBody">

        <input type="text" id="taskName" placeholder="Task Name"><br>
        <input type="text" id="functionality" placeholder="Task Functionality"><br>
        <select  id="platform">
            <option value="iOS" selected="selected">iOS</option>
            <option value="Android">Android</option>
        </select><br>
                <label for="responsibleFunctionary"</label>
                <select id="responsibleFunctionary">
                    <c:forEach items="${employees}" var="employee" >
                        <option value="${employee.name}">${employee.name}</option>
                    </c:forEach>
                </select><br>
        <input type="number" id="estimationHours" placeholder="Estimation Hours"><br>
        <input type="date" id="initialDate" placeholder="Initial Date"><br>
        <div id="employees">
            <c:forEach items="${employees}" var="employee" varStatus="i">
                <div style="display:flex;justify-content:space-between">
                    <label for="percentage${i.index}" id='name${i.index.toString()}'  value="${employee.name}">${employee.name} </label>
                    <input type="number" id="percentage${i.index}" name="dailyHoursPercentage" value="0" max="100" maxlength="3">
                </div>
            </c:forEach>
        </div><br>

        <button onclick="sendWorkers()">Create</button>
        </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
    function sendWorkers(){
        let data=[]
        let numberOfEmployees = $("#employees").children().length
        for(var i=0; i < numberOfEmployees; i++){
            let name = document.getElementById("name"+i).getAttribute("value")
            let percentage= document.getElementById("percentage"+i).value
            data.push(
                {
                    "name": name,
                    "dailyHoursPercentage" : percentage
                }
            )
        }
        console.log(data)
        let task = {}
        task.name=document.getElementById("taskName").value
        task.functionalityTag=document.getElementById("functionality").value
        task.responsibleEmployee=document.getElementById("responsibleFunctionary").value
        task.platform=document.getElementById("platform").value
        task.employeesAssignedToTask = data;
        task.initialDate=document.getElementById("initialDate").value;
        task.hourEstimation=document.getElementById("estimationHours").value
        task.phaseId=${phaseId}
            console.log(task)
        $.ajax(
            {
                url:"/project/createTask",
                data:JSON.stringify(task),
                dataType:"json",
                contentType: 'application/json',
                type:"POST"
            }
        )
    }
</script>

</body>
</html>
