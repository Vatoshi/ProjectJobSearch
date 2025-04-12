<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/register.css">
    <title>Регистрация</title>
</head>

<body>
    <div class="registration-container">
        <div class="registration-header">
            <h1 class="registration-title">Создайте аккаунт</h1>
            <p class="registration-subtitle">Заполните форму для регистрации</p>
        </div>

        <form id="registrationForm" method="POST" action="/auth/register">
            <div class="form-group">
                <input type="text" name="name" required placeholder="Имя">
            </div>

            <div class="form-group">
                <input type="text" name="surname" required placeholder="Фамилия">
            </div>

            <div class="form-group">
                <input type="email" name="email" required placeholder="Email">
            </div>

            <div class="form-group">
                <input type="password" id="password" name="password" required placeholder="Пароль">
            </div>

            <div class="form-group">
                <input type="number" name="age" required placeholder="Возраст">
            </div>

            <div class="form-group">
                <input type="text" name="phoneNumber" required placeholder="Телефон">
            </div>

            <div class="form-group">
                <label for="accountType">Тип аккаунта:</label>
                <select name="roleId" id="accountType" required>
                    <option value="" disabled selected>Выберите тип</option>
                    <option value="1">Соискатель</option>
                    <option value="2">Работодатель</option>
                </select>
            </div>

            <input type="submit" class="submit-btn" value="Зарегистрироваться">

            <div class="error-message" id="error-message">Ошибка регистрации. Пожалуйста, попробуйте снова.</div>
        </form>

        <div class="login-link">
            Уже есть аккаунт? <a href="/auth/login">Войдите</a>
        </div>
    </div>

</body>
</html>