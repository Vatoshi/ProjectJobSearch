<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <title>Sign in</title>
</head>

<body>
    <div class="sign">
        <h1 style="font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; text-align: center;">
            Вход</h1>
        <div class="sign-form">
            <form class="form" id="loginForm" method="POST" action="/auth/login">
                <input minlength="2" maxlength="50" type="text" id="username" name="username"
                    placeholder="Веддите никнейм" required>
                <br>
                <input minlength="2 " maxlength="50" style="margin-top: 2px;" name="password" type="password"
                    id="password" placeholder="Веддите пароль" required>
                <br>
        </div>
        <div class="btns">
            <button class="btn-sum" type="submit">Войти</button>
            </form>
            <a href="/auth/register"><button class="btn-log">Авторизоваться</button></a>
        </div>

    </div>

    <script src="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>