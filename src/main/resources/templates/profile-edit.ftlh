<#macro userEdit>
    <#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile-edit.css">
    <title>Редактирование профиля</title>
</head>
<body>
    <div class="profile-edit-container">

        <form id="profileForm" method="post" action="/profile/edit" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Имя:</label>
                <@spring.formInput "userEditDto.name" 'placeholder="Имя"'/>
                <@spring.showErrors "<br>" "error"/>
<#--                <input type="text" id="name" name="name" -->
<#--                       pattern="^\p{L}+$"-->
<#--                       title="Только буквы"-->
<#--                       minlength="1" maxlength="20">-->
            </div>
            
            <div class="form-group">
                <label for="surname">Фамилия:</label>
                <@spring.formInput "userEditDto.surname" 'placeholder="Фамилия" '/>
                <@spring.showErrors "<br>" "error"/>
<#--                <input type="text" id="surname" name="surname"-->
<#--                       pattern="^\p{L}+$"-->
<#--                       title="Только буквы"-->
<#--                       minlength="1" maxlength="20">-->
            </div>
            
            <div class="form-group">
                <label for="age">Возраст:</label>
                <@spring.formInput "userEditDto.age" 'placeholder="Возраст"'/>
                <@spring.showErrors "<br>" "error"/>
<#--                <input type="number" id="age" name="age" min="1" max="120">-->
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <@spring.formInput "userEditDto.email" 'placeholder="example@gmail.com"'/>
                <@spring.showErrors "<br>" "error"/>
<#--                <input type="email" id="email" name="email" placeholder="example@gmail.com">-->
            </div>
            
            <div class="form-group">
                <label for="phoneNumber">Номер телефона:</label>
                <@spring.formInput "userEditDto.phoneNumber" 'placeholder="Номер телефона"'/>
                <@spring.showErrors "<br>" "error"/>
<#--                <input type="text" id="phoneNumber" name="phoneNumber" placeholder="7993708422">-->
            </div>
            
            <div class="form-group">
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" disabled
                       placeholder="••••••••">
                <div class="password-change">
                    <a href="/change-password">Изменить пароль</a>
                </div>
            </div>
            
            <button type="submit" class="submit-btn">Сохранить изменения</button>
        </form>
    </div>

</body>
</html>
</#macro>