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
    <div class="top-bar">
        <a href="/profile" class="profile">
            <div class="div-img">
                <img src="user/avatars/${user.avatar}" onerror="this.onerror=null; this.src='/image/Default.png'" alt="Фото профиля" class="avatar">
            </div>
            <p class="profile-text">Ваш <br> профиль</p>
        </a>
    </div>
<#else>
    <div class="top-bar">
        <a href="/auth/login" class="profile" style="justify-content: center" >
            <div style="font-weight: 600;">Войти в аккаунт</div>
        </a>
    </div>
</#if>

<div class="container">
    <div class="sidebar">
        <h3>Фильтры</h3>

        <div class="pagination">
            <#if (currentPage >= 3)>
                <a href="?page=0" class="page-link" style="margin-right: 8px">1 ...</a>
            </#if>

            <#if (currentPage != 0)>
                <a href="?page=${currentPage - 1}" class="page-link">${currentPage}</a>
            </#if>

            <a href="?page=${currentPage}" class="page-link active">${currentPage + 1}</a>

            <#if (currentPage != totalPages)>
                <a href="?page=${currentPage + 1}" class="page-link">${currentPage + 2}</a>
            </#if>

            <#if (currentPage < 5 && totalPages > 3)>
                <a href="?page=${totalPages}" class="page-link" style="margin-left: 8px">... ${totalPages + 1}</a>
            </#if>
        </div>
    </div>

    <div class="main-content">
        <h2 class="section-title">Вакансии</h2>
        <ul class="vacancy-list" id="vacancy-list">

            <#if resumes? has_content>
                <#list resumes as resume>
                    <li class="vacancy-item">
                        <div class="vacancy-left">
                            <div class="vacancy-header">
                                <h3 class="vacancy-title">${resume.name}</h3>
                            </div>
                            <div class="vacancy-date">Последнее обновление: ${resume.updateTime}</div>
                            <div class="vacancy-actions">
                                <button class="action-btn">Откликнуться</button>
                            </div>
                        </div>
                        <div class="vacancy-company">${resume.author}</div>
                        <div class="vacancy-salary">Хочу зарплату: ${resume.salary!"Договорная"} ₽ </div>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
</div>


</body>
</html>
