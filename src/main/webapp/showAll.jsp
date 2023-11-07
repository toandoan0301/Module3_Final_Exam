<%--
  Created by IntelliJ IDEA.
  User: toand
  Date: 07/11/2023
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show All Student</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
    <h2> List Student</h2>
    <div class="d-flex">
        <a  class="float-left" href="/student?action=add"><button class="btn btn-info">Add</button></a></th>
        <form class="form-inline float-right" action="/student">
            <input hidden="hidden" name="action" value="search">
                <div class="form-group mb-2">
                    <label for="staticEmail2" class="sr-only">Search</label>
                    <input type="text" class="form-control-plaintext" id="staticEmail2" name="search" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-primary mb-2">Search</button>
            </form>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">DateOfBirth</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Phone</th>
            <th scope="col">Classroom</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <th scope="row">${student.id}</th>
                <td>${student.name}</td>
                <td>${student.dateOfBirth}</td>
                <td>${student.email}</td>
                <td>${student.address}</td>
                <td>${student.phone}</td>
                <td>${student.classroom.name}</td>
                <td>
                    <a href="/student?action=edit&id=${student.id}"> <button class="btn bg-warning">Edit</button></a>
                    <button onclick="confirmDelete(${student.id})" class="btn bg-danger">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function confirmDelete(id){
        x = confirm("Are you sure you want to delete");
        if(x){
            document.location.href ="/student?action=delete&id="+id;
        }
    }
</script>
</body>
</html>
