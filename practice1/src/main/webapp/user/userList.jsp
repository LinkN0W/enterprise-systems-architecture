<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 04.01.2025
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Список пользователей</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        padding: 20px;
        background-color: #f9f9f9;
    }
    h1 {
        color: #333;
    }
    .table-container {
        max-width: 400px;
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #4CAF50;
        color: white;
    }
    tr:hover {
        background-color: #f1f1f1;
        cursor: pointer;
    }
    a {
        text-decoration: none;
        color: inherit;
        display: block;
    }
    a:hover {
        color: #4CAF50;
    }
    .header {
        display: flex;
        justify-content: center;
        background-color: #4CAF50;
        padding: 10px 0;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    .header a {
        text-decoration: none;
        color: white;
        padding: 14px 20px;
        margin: 0 10px;
        border-radius: 4px;
        font-size: 16px;
        transition: background-color 0.3s ease;
    }
    .header a:hover {
        background-color: #45a049;
    }
    .content {
        padding: 20px;
        margin: 20px auto;
        max-width: 800px;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
</style>
</head>
<body>

<div class="header">
    <button>
        <a href="userAdd.jsp">Создать пользователя</a>
    </button>
    <form action="userListServlet" method="get">
        <button type="submit">Список пользователей</button>
    </form>
    <button>
        <a href="../book/bookAdd.jsp">Создать книгу</a>
    </button>
    <form action="bookListServlet" method="get">
        <button type="submit">Список книг</button>
    </form>
</div>

<h1>Список пользователей</h1>
<div class="table-container">
<table >
    <thead>
    <tr>
        <th>Пользователь</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry" items="${users}">
        <tr>
            <td>
                <a href= ${entry.key}>${entry.value}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>