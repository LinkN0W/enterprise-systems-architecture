<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Меню</title>
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
        .menu-container {
            max-width: 400px;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        button {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>Меню</h1>
<div class="menu-container">
    <button>
        <a href="user/userAdd.jsp">Создать пользователя</a>
    </button>
    <form action="userListServlet" method="get">
        <button type="submit">Список пользователей</button>
    </form>
    <button>
        <a href="book/bookAdd.jsp">Создать книгу</a>
    </button>
    <form action="bookListServlet" method="get">
        <button type="submit">Список книг</button>
    </form>
</div>

</body>
</html>