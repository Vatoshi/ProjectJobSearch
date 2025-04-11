<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/register.css">
    <title>Страница регистрации</title>
    <style>

    </style>
</head>
<body>
<div class="registration-form">
    <h2>Регистрация</h2>
    <form id="registrationForm" method="POST" action="/auth/register">
        <div class="form-group">
            <input type="text"  name="name" required placeholder="name">
        </div>
        <div class="form-group">
            <input type="text"  name="surname" required placeholder="surname">
        </div>
        <div class="form-group">
            <input type="email" name="email" required placeholder="email">
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" required placeholder="password">
        </div>
        <div class="form-group">
            <input type="number" name="age" required placeholder="age">
        </div>
        <div class="form-group">
            <input type="text"  name="phoneNumber" required placeholder="phone">
        </div>
        <div class="form-group">
            <label for="accountType">Тип аккаунта:</label>
            <select name="roleId" id="accountType" required>
                <option value="" disabled selected>Выберите тип</option>
                <option value="1">Соискатель</option>
                <option value="2">Работодатель</option>
            </select>
        </div>

        <div class="form-group">
            <input type="submit" value="Зарегистрироваться">
        </div>
        <div class="error-message" id="error-message">Ошибка регистрации. Пожалуйста, попробуйте снова.</div>
    </form>
</div>

</body>
</html>
