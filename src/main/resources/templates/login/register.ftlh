<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <link rel="stylesheet" href="/css/register.css">
    <title><@spring.message "register.register" /></title>
</head>

<body>
    <div class="registration-container">
        <div class="registration-header">
            <h1 class="registration-title"><@spring.message "register.createAcc" /></h1>
            <p class="registration-subtitle"><@spring.message "register.endForm" /></p>
        </div>

        <form id="registrationForm" method="POST" action="/auth/register">
            <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"/></#if>
            <div class="form-group">
                <@spring.formInput "userFormDto.name" 'placeholder="Имя"'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.surname" 'placeholder="Фамилия"'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.email" 'placeholder="Почта"'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.password" 'placeholder="Пароль"' 'password'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.age" 'placeholder="Возраст"'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <@spring.formInput "userFormDto.phoneNumber" 'placeholder="Номер телефона"'/>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <label for="accountType"><@spring.message "register.accType" /></label>
                <select name="roleId" id="accountType">
                    <option value="" disabled selected><@spring.message "register.selectType" /></option>
                    <option value="1"><@spring.message "applicant" /></option>
                    <option value="2"><@spring.message "employer" /></option>
                </select>
                <#if accountType?has_content>
                    <p style="color:#e12e2e;font-size: 14px;">
                        ${accountType}
                    </p>
                </#if>
            </div>

            <input type="submit" class="submit-btn" value="Зарегистрироваться">

            <div class="error-message" id="error-message"><@spring.message "register.errorReg" /></div>
        </form>

        <div class="login-link">
            <@spring.message "register.alreadyExist"/> <a href="/auth/login"><@spring.message "register.login" /></a>
        </div>
    </div>

</body>
</html>