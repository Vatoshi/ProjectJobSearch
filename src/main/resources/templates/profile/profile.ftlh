<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="profile-isolated">
        <div class="profile-isolated__container">
            <div class="profile-isolated__sidebar">
                <div class="profile-isolated__header">
                    <img src="user/avatars/${user.avatar}" onerror="this.onerror=null; this.src='/image/Default.png'"
                         alt="Фото профиля" class="profile-isolated__avatar">
                    <h2 class="profile-isolated__name">${user.name}</h2>
                    <div class="profile-isolated__age">Возраст: ${user.age}</div>
                    <a href="/profile/edit" class="profile-isolated__edit-btn">Редактировать</a>
                </div>

                <ul class="profile-isolated__nav">
                    <li class="profile-isolated__nav-item">
                        <a href="#" class="profile-isolated__nav-link profile-isolated__nav-link--active">
                            <span class="profile-isolated__nav-icon">📄</span>
                            <span>Мои <#if user.role.id==1> резюме <#else> вакансии </#if></span>
                        </a>
                    </li>
                    <li class="profile-isolated__nav-item">
                        <a <#if user.role.id == 1> href="/profile/create-resume" <#else> href="/profile/create-vacancy" </#if>
                                class="profile-isolated__nav-link">
                            <span class="profile-isolated__nav-icon">➕</span>
                            <span>Создать <#if user.role.id==1> резюме <#else> вакансию </#if></span>
                        </a>
                    </li>
                    <li class="profile-isolated__nav-item">
                        <a href="#" class="profile-isolated__nav-link">
                            <span class="profile-isolated__nav-icon">✉️</span>
                            <span>Отклики</span>
                        </a>
                    </li>
<#--                    <#if user.role.id == 2>-->
<#--                        <li class="profile-isolated__nav-item">-->
<#--                            <a href="/resumes" class="profile-isolated__nav-link">-->
<#--                                <span class="profile-isolated__nav-icon">📌</span>-->
<#--                                <span>Доступные резюме</span>-->
<#--                            </a>-->
<#--                        </li>-->
<#--                    <#else>-->
<#--                        <li class="profile-isolated__nav-item">-->
<#--                            <a href="/company" class="profile-isolated__nav-link">-->
<#--                                <span class="profile-isolated__nav-icon">📌</span>-->
<#--                                <span>Страница компаний</span>-->
<#--                            </a>-->
<#--                        </li>-->
<#--                    </#if>-->
<#--                    <li class="profile-isolated__nav-item">-->
<#--                        <a href="/" class="profile-isolated__nav-link">-->
<#--                            <span class="profile-isolated__nav-icon">🔄</span>-->
<#--                            <span>К вакансиям</span>-->
<#--                        </a>-->
<#--                    </li>-->
                </ul>
            </div>

            <div class="profile-isolated__content">
                <h2 class="profile-isolated__content-title">Ваши <#if user.role.id==1> резюме <#else> вакансии </#if></h2>

                <ul class="profile-isolated__items">
                    <#if user.role.id==1>
                        <#if resumes?has_content>
                            <#if (resumes.totalPages > 1)>
                                <div class="profile-isolated__pagination">
                                    <#if (currentPage > 0)>
                                        <a href="?page=${currentPage - 1}" class="profile-isolated__page-link">${currentPage}</a>
                                    </#if>
                                    <a href="?page=${currentPage}" class="profile-isolated__page-link profile-isolated__page-link--active">${currentPage + 1}</a>
                                    <#if currentPage + 1 < resumes.totalPages>
                                        <a href="?page=${currentPage + 1}" class="profile-isolated__page-link">${currentPage + 2}</a>
                                    </#if>
                                </div>
                            </#if>

                            <#list resumes.content as resume>
                                <li class="profile-isolated__item">
                                    <div class="profile-isolated__item-header">
                                        <h3 class="profile-isolated__item-title">${resume.name}</h3>
                                        <div class="profile-isolated__item-date">Дата обновления: ${resume.updateTime!}</div>
                                    </div>
                                    <div class="profile-isolated__item-actions">
                                        <form id="updateTimeResume${resume.id}" method="get" action="/profile/update-resume-time">
                                            <input type="hidden" name="id" value="${resume.id}">
                                        </form>
                                        <button form="updateTimeResume${resume.id}" type="submit" class="profile-isolated__action-btn">Обновить</button>
                                        <a href="/profile/edit-resume?id=${resume.id}" class="profile-isolated__action-btn profile-isolated__action-btn--outline">Редактировать</a>
                                    </div>
                                </li>
                            </#list>
                        <#else>
                            <p class="profile-isolated__empty">На данный момент у вас нет резюме</p>
                        </#if>
                    </#if>

                    <#if user.role.id==2>
                        <#if vacancies?has_content>
                            <#if (vacancies.totalPages > 1)>
                                <div class="profile-isolated__pagination">
                                    <#if (currentPage > 0)>
                                        <a href="?page=${currentPage - 1}" class="profile-isolated__page-link">${currentPage}</a>
                                    </#if>
                                    <a href="?page=${currentPage}" class="profile-isolated__page-link profile-isolated__page-link--active">${currentPage + 1}</a>
                                    <#if currentPage + 1 < vacancies.totalPages>
                                        <a href="?page=${currentPage + 1}" class="profile-isolated__page-link">${currentPage + 2}</a>
                                    </#if>
                                </div>
                            </#if>

                            <#list vacancies.content as vacancy>
                                <li class="profile-isolated__item">
                                    <div class="profile-isolated__item-header">
                                        <h3 class="profile-isolated__item-title">${vacancy.name}</h3>
                                        <div class="profile-isolated__item-date">Дата обновления: ${vacancy.updateTime!}</div>
                                    </div>
                                    <div class="profile-isolated__item-actions">
                                        <form id="updateTime${vacancy.id}" method="get" action="/profile/update-vacancy-time">
                                            <input type="hidden" name="id" value="${vacancy.id}">
                                        </form>
                                        <button form="updateTime${vacancy.id}" type="submit" class="profile-isolated__action-btn">Обновить</button>
                                        <a href="/profile/edit-vacancy?id=${vacancy.id}" class="profile-isolated__action-btn profile-isolated__action-btn--outline">Редактировать</a>
                                        <a href="/details?id=${vacancy.id}" class="profile-isolated__action-btn profile-isolated__action-btn--view">Посмотреть</a>
                                    </div>
                                </li>
                            </#list>
                        <#else>
                            <p class="profile-isolated__empty">На данный момент у вас нет вакансий</p>
                        </#if>
                    </#if>
                </ul>
            </div>
        </div>
    </div>

    <style>
        .profile-isolated {
            --pi-primary: #8739fb;
            --pi-secondary: #924eef;
            --pi-light: #f8f9fa;
            --pi-dark: #2d3748;
            --pi-gray: #718096;
            --pi-light-gray: #e2e8f0;
            --pi-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f7fa;
            min-height: 100vh;
        }

        .profile-isolated__container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            display: grid;
            grid-template-columns: 300px 1fr;
            gap: 30px;
        }

        .profile-isolated__sidebar {
            background: white;
            border-radius: 10px;
            padding: 25px;
            box-shadow: var(--pi-shadow);
            height: fit-content;
        }

        .profile-isolated__header {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            margin-bottom: 25px;
        }

        .profile-isolated__avatar {
            width: 125px;
            height: 125px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 15px;
            border: 3px solid var(--pi-light-gray);
        }

        .profile-isolated__name {
            font-size: 1.4rem;
            font-weight: 600;
            margin-bottom: 5px;
            color: var(--pi-dark);
        }

        .profile-isolated__age {
            color: var(--pi-gray);
            margin-bottom: 15px;
        }

        .profile-isolated__edit-btn {
            display: inline-block;
            background: var(--pi-primary);
            color: white;
            padding: 8px 16px;
            border-radius: 6px;
            font-size: 0.9rem;
            text-decoration: none;
            transition: all 0.3s;
        }

        .profile-isolated__edit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(135, 57, 251, 0.3);
        }

        .profile-isolated__nav {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .profile-isolated__nav-item {
            margin-bottom: 12px;
        }

        .profile-isolated__nav-link {
            display: flex;
            align-items: center;
            padding: 10px 15px;
            border-radius: 6px;
            color: var(--pi-dark);
            text-decoration: none;
            transition: all 0.2s;
        }

        .profile-isolated__nav-link:hover,
        .profile-isolated__nav-link--active {
            background-color: rgba(135, 57, 251, 0.1);
            color: var(--pi-primary);
        }

        .profile-isolated__nav-icon {
            margin-right: 10px;
            width: 20px;
            text-align: center;
        }

        .profile-isolated__content {
            background: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: var(--pi-shadow);
        }

        .profile-isolated__content-title {
            font-size: 1.3rem;
            font-weight: 600;
            margin-bottom: 20px;
            color: var(--pi-dark);
            display: flex;
            align-items: center;
        }

        .profile-isolated__content-title::after {
            content: '';
            flex: 1;
            height: 1px;
            background: var(--pi-light-gray);
            margin-left: 15px;
        }

        .profile-isolated__items {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .profile-isolated__item {
            border: 1px solid var(--pi-light-gray);
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 15px;
            transition: all 0.3s;
        }

        .profile-isolated__item:hover {
            border-color: var(--pi-primary);
            box-shadow: 0 2px 8px rgba(135, 57, 251, 0.1);
        }

        .profile-isolated__item-header {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .profile-isolated__item-title {
            font-size: 1.1rem;
            font-weight: 500;
            color: var(--pi-dark);
        }

        .profile-isolated__item-date {
            color: var(--pi-gray);
            font-size: 0.9rem;
        }

        .profile-isolated__item-actions {
            display: flex;
            gap: 10px;
            margin-top: 15px;
        }

        .profile-isolated__action-btn {
            padding: 8px 16px;
            font-size: 0.85rem;
            background: var(--pi-primary);
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: all 0.3s;
            text-decoration: none;
        }

        .profile-isolated__action-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(135, 57, 251, 0.3);
        }

        .profile-isolated__action-btn--outline {
            background: white;
            color: var(--pi-primary);
            border: 1px solid var(--pi-primary);
        }

        .profile-isolated__action-btn--outline:hover {
            background: rgba(135, 57, 251, 0.1);
        }

        .profile-isolated__action-btn--view {
            margin-left: auto;
        }

        .profile-isolated__pagination {
            display: flex;
            gap: 7px;
            margin-bottom: 20px;
        }

        .profile-isolated__page-link {
            padding: 7px 13px;
            font-size: 0.85rem;
            font-weight: 500;
            color: var(--pi-dark);
            text-decoration: none;
            background-color: white;
            border: 1px solid var(--pi-light-gray);
            border-radius: 8px;
            transition: all 0.3s;
        }

        .profile-isolated__page-link:hover {
            color: white;
            background: var(--pi-primary);
        }

        .profile-isolated__page-link--active {
            background: var(--pi-primary);
            color: white;
            font-weight: 600;
        }

        .profile-isolated__empty {
            color: var(--pi-gray);
            text-align: center;
            padding: 40px 0;
        }

        @media (max-width: 768px) {
            .profile-isolated__container {
                grid-template-columns: 1fr;
            }

            .profile-isolated__sidebar {
                margin-bottom: 20px;
            }
        }
    </style>
</@main.layout>