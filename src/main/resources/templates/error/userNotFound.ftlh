<!DOCTYPE html>
<html>
<head>
    <title>Пользователь не найден</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #d9534f;
            font-size: 50px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background: #337ab7;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>404</h1>
<p>Пользователь не найден</p>
<p th:text="${message} ?: 'Запрошенный пользователь не существует'"></p>
<a href="/vacancies" class="btn">На главную</a>
</body>
</html>