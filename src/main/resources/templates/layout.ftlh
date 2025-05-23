<#macro layout>
    <#import "/spring.ftl" as spring>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="/image/icon.png" type="image/png">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <title>Job Search</title>
    </head>
    <body>

    <nav class="full-width-navbar">
        <div class="navbar-content">
            <div class="nav-links">
                <a href="/" class="nav-link"><@spring.message "layout.main"/></a>
                <div class="dropdown">
                    <input type="checkbox" id="dropdownToggle" class="dropdown-toggle-checkbox" />
                    <label for="dropdownToggle" class="nav-link dropdown-toggle"><@spring.message "layout.goOver"/></label>

                    <ul class="dropdown-menu">
                        <#if user?? && user.role.id == 1><li><a class="dropdown-item" href="/company"><@spring.message "layout.companies"/></a></li></#if>
                        <#if user?? && user.role.id == 2><li><a class="dropdown-item" href="/resumes"><@spring.message "resume"/></a></li></#if>
                        <li><a class="dropdown-item" href="/"><@spring.message "vacancies"/></a></li>
                    </ul>
                </div>
                <#if user??><a href="#" class="nav-link"><@spring.message "layout.yourResponses"/></a></#if>
            </div>

            <div class="right-section">
                <div class="language-switcher">
                    <input type="checkbox" id="languageToggle" class="language-toggle-checkbox" />
                    <label for="languageToggle" class="language-toggle">
                        <#if .locale == 'en'>
                            <img src="/image/en.png" alt="English" class="language-flag">
                        <#else>
                            <img src="/image/ru.png" alt="Русский" class="language-flag">
                        </#if>
                    </label>
                    <ul class="language-menu">
                        <li style="all: unset">
                            <form action="/user/changeLang" method="post" style="display: inline;">
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </#if>
                                <input type="hidden" name="lang" value="en"/>
                                <button type="submit" style="background: none; border: none;">
                                    <img src="/image/en.png" alt="English" class="language-flag">
                                    <span>English</span>
                                </button>
                            </form>

                            <form action="/user/changeLang" method="post" style="display: inline;">
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </#if>
                                <input type="hidden" name="lang" value="ru"/>
                                <button type="submit" style="background: none; border: none;">
                                    <img src="/image/ru.png" alt="Русский" class="language-flag">
                                    <span>Русский</span>
                                </button>
                            </form>
                        </li>

                    </ul>
                </div>

                <#if user?has_content>
                    <div class="profile-logout-container">
                        <a href="/profile" class="profile-container">
                            <img src="user/avatars/${user.avatar}?t=${.now}"
                                 onerror="this.onerror=null; this.src='/image/Default.png'"
                                 alt="<@spring.message "layout.profilePhoto"/>"
                                 class="profile-avatar">
                        </a>
                        <#if logout?has_content>
                            <a href="/logout" class="logout-button"><img src="image/logout.png" alt="<@spring.message "layout.logout"/>"></a>
                        </#if>
                    </div>
                <#else>
                    <div class="auth-links">
                        <a href="auth/login" class="btn-login"><@spring.message "layout.login"/></a>
                        <a href="auth/register" class="btn-register"><@spring.message "layout.register"/></a>
                    </div>
                </#if>
            </div>
        </div>
    </nav>

    <main>
        <#nested spring>
    </main>

    <style>
        :root {
            --primary: #8739fb;
            --secondary: #924eef;
            --light: #f8f9fa;
            --dark: #2d3748;
            --gray: #718096;
            --light-gray: #e2e8f0;
        }

        .full-width-navbar {
            width: 100%;
            background-color: white;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }

        .navbar-content {
            max-width: 1300px;
            margin: 0 auto;
            padding: 13px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .right-section {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .nav-links {
            display: flex;
            gap: 20px;
        }

        .profile-logout-container {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .nav-link {
            font-size: 16px;
            font-weight: 600;
            color: var(--gray);
            text-decoration: none;
            padding: 8px 12px;
            border-radius: 6px;
            transition: all 0.3s ease;
        }

        .nav-link:hover,
        .nav-link.active {
            background-color: rgba(135, 57, 251, 0.05);
            color: var(--primary);
            transform: translateY(-1px);
            box-shadow: 0 2px 6px rgba(135, 57, 251, 0.1);
        }

        .profile-container {
            display: flex;
            align-items: center;
        }

        .profile-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
        }

        .profile-text {
            margin: 0;
            color: var(--dark);
            font-size: 14px;
            line-height: 1.2;
            text-align: left;
        }

        .auth-links {
            display: flex;
            gap: 15px;
        }

        .btn-login {
            padding: 8px 16px;
            font-weight: 600;
            font-size: 14px;
            border: 1px solid var(--primary);
            border-radius: 10px;
            color: var(--primary);
            background-color: white;
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .btn-login:hover {
            background-color: rgba(135, 57, 251, 0.05);
            transform: translateY(-1px);
            box-shadow: 0 2px 6px rgba(135, 57, 251, 0.1);
        }

        .btn-register {
            padding: 8px 16px;
            font-weight: 600;
            font-size: 14px;
            border-radius: 10px;
            background: linear-gradient(to right, var(--primary), var(--secondary));
            color: white;
            text-decoration: none;
            transition: all 0.3s ease;
            border: none;
        }

        .btn-register:hover {
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(135, 57, 251, 0.3);
        }

        .profile-avatar {
            width: 50px;
            height: 50px;
            overflow: hidden;
            border: 1px solid rgb(98, 98, 98);
            border-radius: 100%;
            margin-left: 7px;
            background-color: #f0f0f0;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: box-shadow 0.3s ease;
        }

        .profile-avatar:hover{
            box-shadow: 0 0 7px #6a46eacc;
        }

        .profile-avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .logout-button {
            margin-top: 22px;
            width: 27px;
            height: 27px;
            text-decoration: none;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: transparent;
            padding: 0;
            transition: opacity 0.3s ease;
        }

        .logout-button img {
            width: 100%;
            height: 100%;
            object-fit: contain;
        }

        .dropdown-toggle-checkbox {
            display: none;
        }

        .nav-link.dropdown-toggle {
            position: relative;
            font-size: 16px;
            font-weight: 600;
            color: var(--gray);
            text-decoration: none;
            padding: 8px 12px;
            border-radius: 6px;
            transition: all 0.3s ease;
        }

        .dropdown-menu {
            display: none;
            position: absolute;
            background-color: white;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            min-width: 160px;
            border-radius: 8px;
            padding: 8px 0;
            z-index: 1;
            margin-top: 5px;
        }

        .dropdown-toggle-checkbox:checked + .dropdown-toggle + .dropdown-menu {
            display: block;
        }

        .dropdown-item {
            padding: 10px 16px;
            color: var(--dark);
            text-decoration: none;
            display: block;
            font-weight: 500;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        .dropdown-item:hover {
            background-color: rgba(135, 57, 251, 0.1);
            color: var(--primary);
        }

        .nav-link.dropdown-toggle:hover {
            background-color: rgba(135, 57, 251, 0.05);
            color: var(--primary);
            transform: translateY(-1px);
            box-shadow: 0 2px 6px rgba(135, 57, 251, 0.1);
        }

        /* Language switcher styles */
        .language-switcher {
            position: relative;
            display: inline-block;
        }

        .language-toggle-checkbox {
            display: none;
        }

        .language-toggle {
            cursor: pointer;
            display: flex;
            align-items: center;
            padding: 5px;
            border-radius: 50%;
            transition: all 0.3s ease;
        }

        .language-toggle:hover {
            background-color: rgba(135, 57, 251, 0.1);
        }

        .language-flag {
            width: 24px;
            height: 16px;
            object-fit: cover;
            border-radius: 2px;
        }

        .language-menu {
            display: none;
            position: absolute;
            right: 0;
            top: 100%;
            background-color: white;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 8px 0;
            min-width: 120px;
            z-index: 1000;
            margin-top: 5px;
        }

        .language-toggle-checkbox:checked ~ .language-menu {
            display: block;
        }

        .language-item {
            display: flex;
            align-items: center;
            gap: 8px;
            padding: 8px 16px;
            text-decoration: none;
            color: var(--dark);
            font-weight: 500;
            transition: all 0.2s ease;
        }

        .language-item:hover {
            background-color: rgba(135, 57, 251, 0.1);
            color: var(--primary);
        }

        .language-item span {
            font-size: 14px;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>