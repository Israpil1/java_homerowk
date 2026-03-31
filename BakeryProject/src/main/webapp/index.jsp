<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Золотой Колос — Ваша любимая пекарня</title>
    <style>
        body {
            font-family: 'Georgia', serif;
            margin: 0;
            padding: 0;
            background-color: #fffaf0; /* Кремовый цвет фона */
            color: #4e342e;
            text-align: center;
        }
        .hero {
            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),
            url('https://images.unsplash.com/photo-1509440159596-0249088772ff?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            height: 400px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            color: white;
        }
        h1 { font-size: 3.5rem; margin-bottom: 10px; text-shadow: 2px 2px 4px rgba(0,0,0,0.5); }
        p { font-size: 1.2rem; max-width: 600px; margin: 0 auto 30px; }

        .btn-shop {
            display: inline-block;
            padding: 15px 30px;
            background-color: #d84315;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 30px;
            transition: background 0.3s;
            font-size: 1.1rem;
        }
        .btn-shop:hover {
            background-color: #bf360c;
        }
        .features {
            display: flex;
            justify-content: center;
            padding: 50px 20px;
            gap: 40px;
        }
        .feature-item { max-width: 250px; }
        .feature-item h3 { color: #8d6e63; }
    </style>
</head>
<body>

<div class="hero">
    <h1>Золотой Колос</h1>
    <p>Мы выпекаем хлеб по старинным рецептам с любовью и только из натуральных ингредиентов.</p>
    <a href="shop" class="btn-shop">Посмотреть витрину</a>
    <a href="add-product.jsp" class="btn-shop">Добавить товар</a>
</div>

<div class="features">
    <div class="feature-item">
        <h3>Свежесть</h3>
        <p>Выпекаем трижды в день, чтобы вы всегда получали горячий хлеб.</p>
    </div>
    <div class="feature-item">
        <h3>Качество</h3>
        <p>Используем только фермерскую муку высшего сорта.</p>
    </div>
    <div class="feature-item">
        <h3>Традиции</h3>
        <p>Наши закваски живут уже более 10 лет.</p>
    </div>
</div>

</body>
</html>