<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <title><@spring.message "login.loginInSystem"/></title>
</head>
<body>
    <div class="login-container">
        <h1><@spring.message "login.welcome"/></h1>
        
        <form method="post" action="/auth/login">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="input-group">
                <label for="email"><@spring.message "email"/></label>
                <input type="text" name="username" id="email" placeholder="Введите ваш email" required>
            </div>
            
            <div class="input-group">
                <label for="password"><@spring.message "password"/></label>
                <input type="password" name="password" id="password" placeholder="Введите ваш пароль" required>
            </div>

            <#if error?has_content>
                <p style="color:#e12e2e;font-size: 14px;">
                ${error}
                </p>
            </#if>
            <button type="submit" class="btn"><@spring.message "login"/></button>
            <div class="links">
                <a href="/auth/reset"><@spring.message "login.forgotPassword"/></a> · <a href="register"><@spring.message "register"/></a>
            </div>
        </form>
    </div>
</body>
</html>