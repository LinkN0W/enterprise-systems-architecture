<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 03.01.2025
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создание нового читателя</title>
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
        form {
            max-width: 400px;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input, select, button {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
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

<h1>Создание нового читателя</h1>
<form action="${pageContext.request.contextPath}/userServlet" method="POST">
    <label for="surname">Фамилия:</label>
    <input type="text" id="surname" name="surname" required>

    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>

    <label for="patronymic">Отчество:</label>
    <input type="text" id="patronymic" name="patronymic">

    <label for="birthdate">Дата рождения:</label>
    <input type="date" id="birthdate" name="birthdate" required>

    <label for="gender">Пол:</label>
    <select id="gender" name="gender" required>
        <option value="">Выберите</option>
        <option value="male">Мужской</option>
        <option value="female">Женский</option>
    </select>

    <button type="submit">Отправить</button>
</form>

</body>
</html>
