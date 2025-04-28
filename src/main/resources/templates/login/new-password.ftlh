<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <link rel="icon" href="/image/icon.png" type="image/png">
    <meta charset="UTF-8">
    <title>Смена пароля</title>
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

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #444;
        }

        form {
            display: flex;
            flex-direction: column;
        }


        .input[type="password"] {
            padding: 8px 15px;
            margin-bottom: 10px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s;
        }

        .input[type="password"]:focus {
            border-color: #667eea;
            outline: none;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
        }

        .btn {
            margin-top: 20px;
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            border: none;
            padding: 12px 0;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        @keyframes fadeIn {
            to {
                opacity: 1;
            }
        }

        p {
            color: #e12e2e;
            font-size: 14px;
            opacity: 0;
            animation: fadeIn 1s forwards;
            margin-top: 3px;
        }
    </style>
</head>

<body>

    <div class="container">
        <h1>Смена пароля</h1>
        <form action="/auth/change-form" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <@spring.formInput "passwordChangeDto.newPassword" 'placeholder="новый пароль", class="input"' 'password'></@spring.formInput>
            <@spring.formInput "passwordChangeDto.confirmPassword" 'placeholder="повторите пароль", class="input"' 'password'></@spring.formInput>
            <#if error?has_content>
                <p style="color:#e12e2e;font-size: 14px;">
                    ${error}
                </p>
            </#if>
            <button class="btn" type="submit">Сменить пароль</button>
        </form>
    </div>

</body>

</html>
