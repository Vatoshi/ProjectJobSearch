<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><@spring.message "edit.password.title"/></title>
    <link rel="stylesheet" href="/css/profile-edit.css">
    <link rel="icon" href="/image/icon.png" type="image/png">
</head>
<body>
<div class="profile-edit-container">
    <form method="post" action="/profile/edit-password">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>

        <div class="form-group">
            <label for="currentPassword"><@spring.message "edit.password.currentPassword"/></label>
            <@spring.formInput "changePasswordDto.password" 'class="input", value="changePasswordDto.password"' 'password'></@spring.formInput>
            <@spring.showErrors "<br>" "error"/>

            <div class="password-change">
                <a href="/auth/reset"><@spring.message "edit.password.forgotPassword"/></a>
            </div>
        </div>

        <div class="form-group">
            <label for="newPassword"><@spring.message "edit.password.newPassword"/></label>
            <@spring.formInput "changePasswordDto.newPassword" 'class="input"' 'password'></@spring.formInput>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="confirmPassword"><@spring.message "edit.password.confirmPassword"/></label>
            <@spring.formInput "changePasswordDto.confirmPassword" 'class="input"' 'password'></@spring.formInput>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <#if message?has_content>
            <p style="color:#e12e2e;font-size: 14px;">
                ${message}
            </p>
        </#if>

        <button type="submit" class="submit-btn small-btn"><@spring.message "edit.password.changePasswordBtn"/></button>
    </form>

    <div class="back-btn-container">
        <a href="/profile/edit" class="back-btn"><@spring.message "edit.password.back"/></a>
    </div>
</div>
</body>
</html>
