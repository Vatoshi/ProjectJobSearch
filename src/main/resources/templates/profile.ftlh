<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile.css">
    <title>Личный кабинет
        <#if user.roleId == 1>
            Соискателя
        <#else>
            Работодателя
        </#if>
    </title>
</head>

<body>
    <div class="container">
        <h1 style="padding-bottom: 20px;">Личный кабинет <#if user.roleId == 1> Соискателя <#else> Работодателя </#if></h1>

        <div class="profile">
            <img src="" alt="User Icon">
            <div class="info">
                <h2 style="font-weight: 200;">${user.name}</h2>
                <p>Возраст: ${user.age}</p>
                <button class="edit-btn">Редактировать</button>
            </div>
            <div class="actions">
                <div class="create-resume">
                    +<br>Создать<br>резюме
                </div>
                <div class="replies">
                    Отклики
                    <div class="notification">5</div>
                </div>
            </div>
        </div>

        <div class="resume-section">
            <h3 style="font-weight: 200;">Резюме:</h3>
            <div class="resume-list">

                <div class="resume">
                    <div style="margin: 0px; font-weight: 500; word-wrap: break-word;">Название резюме</div>
                    <div style="margin-top: 30px;">Дата обновления: </div>
                    <div class="resumes-button">
                        <button class="update-btn">Обновить</button>
                        <button class="red-btn">Ред</button>
                    </div>
                </div>

                
            </div>
        </div>
    </div>
</body>

</html>