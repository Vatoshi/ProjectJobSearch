<#import "/spring.ftl" as spring>
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
                <@spring.formInput "userFormDto.name" 'placeholder="Имя" required'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.surname" 'placeholder="Фамилия" required'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.email" 'placeholder="Почта" required'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.password" 'placeholder="Пароль" required'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.age" 'placeholder="Возраст" required'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.phoneNumber" 'placeholder="Номер телефона" required'/>
                <@spring.showErrors "<br>" "error"/>
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