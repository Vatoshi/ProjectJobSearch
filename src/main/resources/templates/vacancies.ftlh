<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/vacancy.css">
    <title>Список вакансий</title>
</head>

<body>

<#if user?has_content>
    <a href="/profile" class="profile">
        <div class="div-img"><img></div>
        <p class="profile-text">Ваш <br> профиль</p>
    </a>
</#if>

    <div class="container">
        <div class="vacancies-list">

            <#list vacancies as vacancy>
            <div class="vacancy-item">
                <div class="vacancy-header">
                    <h3 class="vacancy-title">${vacancy.name}</h3>
                    <div class="salary">от ${vacancy.salary} ₽</div>
                </div>
                <div class="company-info">
                    <span class="company-name">(имя пользователя компании)</span>
<#--                    <span>Москва</span>-->
                </div>
                <div class="vacancy-meta">
                    <span class="meta-item">Опыт от ${vacancy.expFrom} года</span>
                </div>
                <div class="vacancy-description">
                    ${vacancy.description}
                </div>
                <div class="vacancy-footer">
                    <div class="date">Опубликовано ${vacancy.createdDate}</div>
                    <button class="apply-btn">Откликнуться</button>
                </div>
            </div>
            </#list>


        </div>
    </div>


</body>

</html>