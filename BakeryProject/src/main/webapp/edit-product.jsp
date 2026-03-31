<%--
  Created by IntelliJ IDEA.
  User: gulag
  Date: 31.03.2026
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование</title>
    <style>
        body { font-family: sans-serif; background-color: #fdf5e6; display: flex; justify-content: center; padding-top: 50px; }
        form { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); width: 300px; }
        input, textarea { width: 100%; margin-bottom: 15px; padding: 8px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background: #8b4513; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<form action="shop" method="POST">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${param.id}">
    Название: <input type="text" name="name" value="${param.name}"><br>
    Цена: <input type="number" step="0.01" name="price" value="${param.price}"><br>
    Описание: <textarea name="description">${param.desc}</textarea><br>
    <button type="submit">Сохранить изменения</button>
</form>
</body>
</html>
