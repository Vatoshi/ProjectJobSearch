<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile-edit.css">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <title><@spring.message "edit.profile.title"/></title>
</head>
<body>
<div class="profile-edit-container">

    <div class="icon">
        <div class="circle">
            <form action="/profile/avatar" method="post" enctype="multipart/form-data" id="avatarForm">
                <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"/></#if>
                <input type="file" name="image" id="upload" style="display: none" onchange="document.getElementById('avatarForm').submit();">
                <label for="upload" class="plus">
                    <img class="plus" src="/image/icons8-знак-плюс-30.png" alt="<@spring.message "edit.profile.plus"/>">
                </label>
                <img src="/user/avatars/${user.avatar}"
                     onerror="this.onerror=null; this.src='/image/Default.png'"
                     alt="<@spring.message "edit.profile.avatar"/>">
            </form>
        </div>
    </div>

    <form id="profileForm" method="post" action="/profile/edit" enctype="multipart/form-data">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>

<#--        ya hz kak placeholder menyat-->
        <div class="form-group">
            <label for="name"><@spring.message code="edit.profile.name"/></label>
            <@spring.formInput "userEditDto.name" 'placeholder="Имя"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="surname"><@spring.message "edit.profile.surname"/></label>
            <@spring.formInput "userEditDto.surname" 'placeholder="Фамилия"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="age"><@spring.message "edit.profile.age"/></label>
            <@spring.formInput "userEditDto.age" 'placeholder="Возраст"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="email"><@spring.message "edit.profile.email"/></label>
            <div class="input-with-button">
                <@spring.formInput "userEditDto.email" 'placeholder="example@gmail.com" disabled'/>
                <a href="/profile/edit-email" class="input-button"><@spring.message "edit.profile.change"/></a>
            </div>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="phoneNumber"><@spring.message "edit.profile.phoneNumber"/></label>
            <@spring.formInput "userEditDto.phoneNumber" 'placeholder="Номер телефона"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="password"><@spring.message "edit.profile.password"/></label>
            <div class="input-with-button">
                <input type="password" id="password" name="password" disabled placeholder="••••••••">
                <a href="/profile/edit-password" class="input-button"><@spring.message "edit.profile.change"/></a>
            </div>
        </div>

        <button type="submit" class="submit-btn"><@spring.message "edit.profile.saveChanges"/></button>
    </form>

</div>
</body>
</html>
