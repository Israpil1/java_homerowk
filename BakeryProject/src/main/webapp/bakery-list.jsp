<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Витрина пекарни</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #fdf5e6; color: #5d4037; }
        h1 { text-align: center; color: #8b4513; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; background: white; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        th, td { border: 1px solid #d7ccc8; padding: 12px; text-align: left; }
        th { background-color: #a0522d; color: white; }
        tr:nth-child(even) { background-color: #f9f4f1; }
        .price { font-weight: bold; color: #2e7d32; }
    </style>
</head>
<body>

<h1>Наша свежая выпечка </h1>

<table>
    <tr>
        <th>ID</th>
        <th>Название изделия</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Действия</th>


    </tr>
    <c:forEach var="item" items="${products}">
        <tr>
            <td>${item.id}</td>
            <td><strong>${item.name}</strong></td>
            <td>${item.description}</td>
            <td class="price">${item.price} руб.</td>
            <td>
                <a href="shop?action=delete&id=${item.id}"
                   onclick="return confirm('Удалить этот шедевр?')">Удалить</a>
                |
                <a href="edit-product.jsp?id=${item.id}&name=${item.name}&price=${item.price}&desc=${item.description}">Ред.</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>