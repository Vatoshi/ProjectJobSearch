<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile.css">
    <title>Личный кабинет
        <#if user.roleId==1>
            Соискателя
            <#else>
                Работодателя
        </#if>
    </title>
</head>

<body>
    <a href="vacancies" class="main-page">
        <p style="font-weight: 600; font-size: 12px">к вакансиям</p>
    </a>

    <div class="container">
        <h1 style="padding-bottom: 20px;">Личный кабинет <#if user.roleId==1> Соискателя <#else> Работодателя </#if>
        </h1>

        <div class="profile">
            <img src="/image/${user.avatar}" alt="User Icon">
            <div class="info">
                <h2 style="font-weight: 200;">${user.name}</h2>
                <p>Возраст: ${user.age}</p>
                <a href="profile/edit"><button class="edit-btn">Редактировать</button></a>
            </div>
            <div class="actions">
                <div class="create-resume">
                    <br>Создать<br>
                    <#if user.roleId==1> резюме <#else> вакансию </#if>
                </div>
                <div class="replies">
                    Отклики
                    <div class="notification">5</div>
                </div>
            </div>
        </div>

        <div class="resume-section">
            <h3 style="font-weight: 200;">Ваши <#if user.roleId==1>резюме:<#else>вакансии:</#if>
            </h3>
            <div class="resume-list">
                <#if user.roleId==1>
                    <#if resumes?has_content>
                        <#list resumes as resume>
                            <div class="resume">
                                <div style="margin: 0px; font-weight: 500; word-wrap: break-word;">Название резюме:
                                    ${resume.name}</div>
                                <div style="margin-top: 30px;">Дата обновления: <br> ${resume.updated!}</div>
                                <div class="resumes-button">
                                    <button class="update-btn">Обновить</button>
                                    <button class="red-btn">Ред</button>
                                </div>
                            </div>
                        </#list>
                      <#else>
                            <div class="empty-message">У вас нет резюме</div>
                    </#if>
                    <#else>
                        <#if vacancies?has_content>
                            <#list vacancies as vacancy>
                                <div class="resume">
                                    <div style="margin: 0px; font-weight: 500; word-wrap: break-word;">Название
                                        вакансии: ${vacancy.name}</div>
                                    <div style="margin-top: 30px;">Дата обновления:<br> ${vacancy.updated!}</div>
                                    <div class="resumes-button">
                                        <button class="update-btn">Обновить</button>
                                        <button class="red-btn">Ред</button>
                                    </div>
                                </div>
                            </#list>
                            <#else>
                                <div class="empty-message">У вас нет вакансий</div>
                        </#if>
                </#if>
            </div>
        </div>
    </div>
</body>

</html>