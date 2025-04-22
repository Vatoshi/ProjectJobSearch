<#import "navbar.ftlh" as nav>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/vacancy.css">
    <title>Вакансии</title>
</head>

<body>

<@nav.navbar/>

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
        <h2 class="section-title">Компании</h2>
        <ul class="vacancy-list" id="vacancy-list">

            <#if companies? has_content>
                <#list companies as company>
            <a href="company-details?id=${company.id}" style="text-decoration: none; transition: none; animation: none">
                    <li class="vacancy-item">
                        <div class="vacancy-left">
                            <div class="vacancy-header">
                                <h3 class="vacancy-title">${company.name}</h3>
                            </div>
                        </div>
                        <div class="vacancy-company">${company.phoneNumber}</div>
                    </li>
            </a>
                </#list>
            </#if>
        </ul>
    </div>
</div>


</body>
</html>
