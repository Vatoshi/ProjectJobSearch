
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <title>Сброс пароля</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #becaff 0%, #ead5ff 100%);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #333;
        }

        .login-container {
            margin-bottom: 60px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            width: 400px;
            padding: 40px;
            text-align: center;
            animation: fadeIn 0.5s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .logo {
            width: 80px;
            margin-bottom: 20px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 30px;
            color: #444;
        }

        .input-group {
            margin-bottom: 13px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
        }

        input {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s;
        }

        input:focus {
            border-color: #667eea;
            outline: none;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
        }

        .btn {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            border: none;
            padding: 12px 0;
            width: 100%;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            margin-top: 10px;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .links {
            margin-top: 25px;
            font-size: 14px;
        }

        .links a {
            color: #667eea;
            text-decoration: none;
            transition: all 0.3s;
        }

        .links a:hover {
            text-decoration: underline;
        }

        p {
            color: #e12e2e;
            font-size: 14px;
            opacity: 0;
            animation: fadeIn 0.5s forwards;
            margin-top: 3px;
        }

    </style>
</head>

<body>

<div class="login-container">

    <h1>Сброс пароля</h1>

    <form action="/auth/reset" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="input-group">
            <label for="email">Введите вашу почту</label>
            <input type="email" id="email" name="email" value="${email}" placeholder="example@mail.com">
            <#if error?has_content>
                <p style="color:#e12e2e;font-size: 14px;">
                    ${error}
                </p>
            </#if>
        </div>
        <button type="submit" class="btn">Отправить код</button>
    </form>

    <div class="links">
        <a href="/auth/login">Вернуться к входу</a>
    </div>
</div>

</body>

</html>
