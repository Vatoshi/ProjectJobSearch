<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><@spring.message "edit.email.title"/></title>
    <link rel="stylesheet" href="/css/profile-edit.css">
    <link rel="icon" href="/image/icon.png" type="image/png">
</head>
<body>
<div class="profile-edit-container">
    <form method="post" action="/profile/edit-email">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>

        <div class="form-group">
            <label for="newEmail"><@spring.message "edit.email.confirmPassword"/></label>
            <@spring.formInput "changeEmailDto.password" 'class="input"' 'password'></@spring.formInput>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="newEmail"><@spring.message "edit.email.newEmail"/></label>
            <@spring.formInput "changeEmailDto.newEmail" 'placeholder="newEmail.com", class="input"'></@spring.formInput>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="confirmEmail"><@spring.message "edit.email.confirmEmail"/></label>
            <@spring.formInput "changeEmailDto.confirmEmail" 'placeholder="newEmail.com", class="input"'></@spring.formInput>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <#if message?has_content>
            <p style="color:#e12e2e;font-size: 14px;">
                ${message}
            </p>
        </#if>

        <button type="submit" class="submit-btn small-btn"><@spring.message "edit.email.changeEmail"/></button>
    </form>

    <div class="back-btn-container">
        <a href="/profile/edit" class="back-btn"><@spring.message "edit.email.back"/></a>
    </div>
</div>
</body>
</html>
