<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/login.css">
    <title>Вход в систему</title>
</head>
<body>
    <div class="login-container">
        <h1>Добро пожаловать</h1>
        
        <form method="post" action="/auth/login">
            <div class="input-group">
                <label for="email">Электронная почта</label>
                <input type="text" name="username" id="email" placeholder="Введите ваш email" required>
            </div>
            
            <div class="input-group">
                <label for="password">Пароль</label>
                <input type="password" name="password" id="password" placeholder="Введите ваш пароль" required>
            </div>
            
            <button type="submit" class="btn">Войти</button>
            
            <div class="links">
                <a href="#">Забыли пароль?</a> · <a href="register">Регистрация</a>
            </div>
        </form>
    </div>
</body>
</html>