<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить выпечку</title>
    <style>
        body { font-family: sans-serif; background-color: #fdf5e6; display: flex; justify-content: center; padding-top: 50px; }
        form { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); width: 300px; }
        input, textarea { width: 100%; margin-bottom: 15px; padding: 8px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background: #8b4513; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<form action="shop" method="POST">
    <h2>Новое изделие</h2>
    <input type="text" name="name" placeholder="Название (например, Плюшка)" required>
    <input type="number" step="0.01" name="price" placeholder="Цена" required>
    <textarea name="description" placeholder="Описание"></textarea>
    <button type="submit">Добавить на витрину</button>
</form>
</body>
</html>