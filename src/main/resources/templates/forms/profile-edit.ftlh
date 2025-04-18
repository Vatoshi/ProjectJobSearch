<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile-edit.css">
    <title>Редактирование профиля</title>
</head>
<body>
<div class="profile-edit-container">

        <div class="icon">
            <div class="circle">
                <form action="/profile/avatar" method="post" enctype="multipart/form-data" id="avatarForm">
                    <input type="file" name="image" id="upload" style="display: none" onchange="document.getElementById('avatarForm').submit();">
                    <label for="upload" class="plus">
                        <img class="plus" src="/image/icons8-знак-плюс-30.png" alt="plus">
                    </label>
                    <img src="/user/avatars/${user.avatar}"
                         onerror="this.onerror=null; this.src='/image/Default.png'"
                         alt="avatar">
                </form>
            </div>
        </div>

        <form id="profileForm" method="post" action="/profile/edit" enctype="multipart/form-data">

        <div class="form-group">
            <label for="name">Имя:</label>
            <@spring.formInput "userEditDto.name" 'placeholder="Имя"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <@spring.formInput "userEditDto.surname" 'placeholder="Фамилия"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="age">Возраст:</label>
            <@spring.formInput "userEditDto.age" 'placeholder="Возраст"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <@spring.formInput "userEditDto.email" 'placeholder="example@gmail.com" disabled'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="phoneNumber">Номер телефона:</label>
            <@spring.formInput "userEditDto.phoneNumber" 'placeholder="Номер телефона"'/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" disabled placeholder="••••••••">
<#--            <div class="password-change">-->
<#--                <a href="/change-password">Изменить пароль</a>-->
<#--            </div>-->
        </div>

        <button type="submit" class="submit-btn">Сохранить изменения</button>
    </form>

</div>
</body>
</html>
