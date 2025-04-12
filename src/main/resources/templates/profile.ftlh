<#import "profile-edit.ftlh" as layout>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile.css">
    <title>Личный кабинет <#if user.roleId==1> соискателя <#else> работодателя </#if> </title>
</head>

<body>
    <div class="container">
        <div class="sidebar">
            <div class="profile-header">
                <img src="/image/${user.avatar}" alt="Фото профиля" class="avatar">
                <h2 class="profile-name">${user.name}</h2>
                <div class="profile-age">Возраст: ${user.age}</div>
                <a href="/profile/edit" class="btn" id="edit-profile-btn">Редактировать</a>
            </div>
            <ul class="nav-menu">
                <li class="nav-item">
                    <a href="#" class="nav-link active" data-target="resumes">
                        <span class="nav-icon">📄</span>
                        <span>Мои <#if user.roleId==1> резюме <#else> вакансии </#if></span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link" data-target="responses">
                        <span class="nav-icon">✉️</span>
                        <span>Отклики</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link" data-target="create">
                        <span class="nav-icon">➕</span>
                        <span>Создать <#if user.roleId==1> резюме <#else> вакансии </#if></span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="main-content" id="resumes">
            <h2 class="section-title"> Ваши <#if user.roleId==1> резюме <#else> вакансии </#if> </h2>
            <ul class="resume-list">

                <#if user.roleId==1>
                <#if resumes?has_content>
                    <#list resumes as resume>
                <li class="resume-item">
                    <div class="resume-header">
                        <h3 class="resume-title">${resume.name}</h3>
                        <div class="resume-date">Дата обновления: ${resume.updated!}</div>
                    </div>
                    <div class="resume-actions">
                        <a href="#" class="btn action-btn">Обновить</a>
                        <a href="#" class="btn btn-outline action-btn">Редактировать</a>
                    </div>
                </li>
                </#list>
                <#else>
                    <p>На данный момент у вас нету резюме</p>
                </#if>
                </#if>

                <#if user.roleId==2>
                    <#if vacancies?has_content>
                        <#list vacancies as vacancy>
                        <li class="resume-item">
                            <div class="resume-header">
                                <h3 class="resume-title">${vacancy.name}</h3>
                                <div class="resume-date">Дата обновления: ${vacancy.updated!}</div>
                            </div>
                            <div class="resume-actions">
                                <a href="#" class="btn action-btn">Обновить</a>
                                <a href="#" class="btn btn-outline action-btn">Редактировать</a>
                            </div>
                        </li>
                        </#list>
                    <#else>
                        <p>На данный момент у вас нету вакансий</p>
                    </#if>
                </#if>


            </ul>
        </div>

        <div class="main-content" id="responses" style="display: none;">
            <h2 class="section-title">Отклики</h2>
            <p>Пока нет откликов.</p>
        </div>

        <div class="main-content" id="create" style="display: none;">
            <h2 class="section-title">Создать резюме</h2>
            <p>Здесь будет форма создания резюме.</p>
        </div>

        <div class="main-content" id="edit-profile" style="display: none;">
            <@layout.layout/>
        </div>
    </div>

    <script>
        const navLinks = document.querySelectorAll('.nav-link');
        const contentSections = document.querySelectorAll('.main-content');

        navLinks.forEach(link => {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                const target = this.getAttribute('data-target');

                navLinks.forEach(l => l.classList.remove('active'));
                this.classList.add('active');

                contentSections.forEach(section => section.style.display = 'none');
                document.getElementById(target).style.display = 'block';
            });
        });

        const editBtn = document.getElementById('edit-profile-btn');
        const editSection = document.getElementById('edit-profile');

        editBtn.addEventListener('click', (e) => {
            e.preventDefault();

            navLinks.forEach(link => link.classList.remove('active'));
            contentSections.forEach(section => section.style.display = 'none');
            editSection.style.display = 'block';
        });

    </script>

</body>

</html>
