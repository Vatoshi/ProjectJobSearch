<#import "spring.ftl" as spring>
<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <title>Document</title>
        <style>
            :root {
                --brand-main: #8739fb;
                --brand-accent: #924eef;
                --light-bg: #f8f9fa;
                --dark-text: #2d3748;
                --muted-text: #718096;
                --border-color: #e2e8f0;
            }

            /* Основные стили лейаута */
            .layout-container {
                width: 100%;
                background-color: var(--light-bg);
            }

            .layout-navbar {
                max-width: 1400px;
                margin: 0 auto;
                padding: 15px 30px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            }

            /* Навигационное меню */
            .layout-nav-menu {
                display: flex;
                gap: 20px;
            }

            .layout-nav-link {
                font-size: 16px;
                font-weight: 600;
                color: var(--muted-text);
                text-decoration: none;
                transition: all 0.3s ease;
                position: relative;
                padding: 8px 12px;
                border-radius: 6px;
            }

            .layout-nav-link:hover,
            .layout-nav-link--active {
                background-color: rgba(135, 57, 251, 0.05);
                color: var(--brand-main);
                transform: translateY(-0.5px);
                box-shadow: 0 2px 6px rgba(135, 57, 251, 0.1);
            }

            .layout-nav-link--active::after {
                content: '';
                position: absolute;
                bottom: -5px;
                left: 0;
                height: 3px;
                width: 100%;
                background: var(--brand-main);
                border-radius: 3px;
            }

            /* Блок авторизации */
            .layout-auth-block {
                display: flex;
                gap: 15px;
            }

            .layout-btn-signin {
                padding: 10px 18px;
                font-size: 14px;
                font-weight: 600;
                color: var(--brand-main);
                background-color: white;
                border: 1px solid var(--brand-main);
                border-radius: 10px;
                text-decoration: none;
                transition: all 0.3s ease;
            }

            .layout-btn-signin:hover {
                background-color: rgba(135, 57, 251, 0.05);
                box-shadow: 0 4px 10px rgba(135, 57, 251, 0.15);
                transform: translateY(-1px);
            }

            .layout-btn-signup {
                padding: 10px 18px;
                font-size: 14px;
                font-weight: 600;
                color: white;
                background: linear-gradient(to right, var(--brand-main), var(--brand-accent));
                border: none;
                border-radius: 10px;
                text-decoration: none;
                transition: all 0.3s ease;
            }

            .layout-btn-signup:hover {
                box-shadow: 0 6px 14px rgba(135, 57, 251, 0.25);
                transform: translateY(-1px);
            }

            /* Профиль пользователя */
            .layout-profile-link {
                display: flex;
                align-items: center;
                gap: 10px;
                text-decoration: none;
            }

            .layout-avatar-img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                object-fit: cover;
            }

            .layout-profile-info {
                font-size: 14px;
                font-weight: 600;
                color: var(--dark-text);
            }
        </style>
    </head>
    <body>
    <div class="layout-container">
        <nav class="layout-navbar">
            <div class="layout-nav-menu">
                <a href="/" class="layout-nav-link <#if currentPage == 'home'>layout-nav-link--active</#if>">Главная</a>
                <a href="/about" class="layout-nav-link <#if currentPage == 'about'>layout-nav-link--active</#if>">Ваши отклики</a>
            </div>

            <#if user?has_content>
                <a href="/profile" class="layout-profile-link">
                    <img src="user/avatars/${user.avatar}?t=${.now}"
                         onerror="this.onerror=null; this.src='/image/Default.png'"
                         alt="Фото профиля"
                         class="layout-avatar-img">
                    <div class="layout-profile-info">Ваш<br>профиль</div>
                </a>
            <#else>
                <div class="layout-auth-block">
                    <a href="auth/login" class="layout-btn-signin">Войти</a>
                    <a href="auth/register" class="layout-btn-signup">Зарегистрироваться</a>
                </div>
            </#if>
        </nav>
    </div>

    <main class="layout-content">
        <#nested spring>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>