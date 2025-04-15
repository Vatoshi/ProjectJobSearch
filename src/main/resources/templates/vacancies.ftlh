<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/vacancy.css">
    <title>Вакансии</title>
</head>

<body>

<#if user?has_content>
    <a href="/profile" class="profile">
        <div class="div-img">
            <img src="user/avatars/${user.avatar}" onerror="this.onerror=null; this.src='/image/Default.png'" alt="Фото профиля" class="avatar">
        </div>
        <p class="profile-text">Ваш <br> профиль</p>
    </a>
<#else>
    <a href="/auth/login" class="profile" style="justify-content: center" >
        <div style="font-weight: 600;">Войти в аккаунт</div>
    </a>
</#if>

<div class="container">
    <div class="sidebar">
        <h3>Фильтры</h3>
        <div class="filter-section">
            <label for="category">Категория</label>
            <select id="category">
                <option value="">Все</option>
                <option value="frontend">Frontend</option>
                <option value="backend">Backend</option>
                <option value="designer">Дизайнер</option>
            </select>
        </div>

        <div class="filter-section">
            <label for="location">Местоположение</label>
            <select id="location">
                <option value="">Все</option>
                <option value="moscow">Москва</option>
                <option value="spb">Санкт-Петербург</option>
                <option value="kazan">Казань</option>
            </select>
        </div>

        <button class="action-btn" onclick="filterJobs()">Применить фильтры</button>
    </div>

    <div class="main-content">
        <h2 class="section-title">Вакансии</h2>
        <ul class="vacancy-list" id="vacancy-list">

            <#if vacancies? has_content>
            <#list vacancies as vacancy>
                <a href="vacancies/details?id=${vacancy.id}" style="text-decoration: none; transition: none; animation: none">
                <li class="vacancy-item">
                    <div class="vacancy-left">
                        <div class="vacancy-header">
                            <h3 class="vacancy-title">${vacancy.name}</h3>
                        </div>
                        <div class="vacancy-date">Последнее обновление: ${vacancy.updateTime}</div>
                        <div class="vacancy-experience">Опыт работы: ${vacancy.expFrom} года</div>
                        <p class="vacancy-description">${vacancy.description!"-"}</p>
                        <div class="vacancy-actions">
                            <button class="action-btn">Подать заявку</button>
                        </div>
                    </div>
                    <div class="vacancy-company">${vacancy.author}</div>
                    <div class="vacancy-salary">Зарплата: ${vacancy.salary!"Договорная"} ₽ </div>
                </li>
                </a>
            </#list>
            </#if>
        </ul>
    </div>
</div>


</body>
</html>
