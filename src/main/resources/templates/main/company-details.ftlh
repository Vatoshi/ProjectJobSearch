<#import "navbar.ftlh" as nav>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>${company.name} – Профиль компании</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Inter', sans-serif;
        }

        body {
            background-color: #f8f9fa;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem;
        }

        h1 {
            font-size: 2.5rem;
            font-weight: 700;
            color: #2c3e50;
            margin-bottom: 0.5rem;
        }

        .subtitle {
            font-size: 1.1rem;
            color: #7f8c8d;
            margin-bottom: 2rem;
        }

        .subtitle strong {
            color: #5a4ad1;
            font-weight: 600;
        }

        .company-avatar {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            overflow: hidden;
            margin: 2rem 0;
            border: 4px solid #e0d6ff;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #fff;
            box-shadow: 0 4px 12px rgba(90, 74, 209, 0.1);
        }

        .company-avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        h2 {
            font-size: 1.8rem;
            color: #2c3e50;
            margin: 2rem 0 1.5rem;
            padding-bottom: 0.5rem;
            border-bottom: 2px solid #e0d6ff;
        }

        .vacancy-list {
            display: grid;
            grid-template-columns: 1fr;
            gap: 1.5rem;
            margin-top: 2rem;
        }

        .vacancy-card {
            border: 1px solid #e0d6ff;
            border-radius: 12px;
            padding: 1.5rem;
            background: #fff;
            transition: all 0.3s ease;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
        }

        .vacancy-card:hover {
            box-shadow: 0 8px 20px rgba(160, 132, 232, 0.2);
            transform: translateY(-2px);
            border-color: #a084e8;
        }

        .vacancy-title {
            font-size: 1.2rem;
            font-weight: 600;
            margin-bottom: 0.5rem;
            color: #5a4ad1;
        }

        .vacancy-details {
            font-size: 0.95rem;
            color: #555;
            line-height: 1.8;
        }

        .vacancy-details br {
            display: block;
            content: "";
            margin: 0.5rem 0;
        }

        p {
            color: #7f8c8d;
            font-size: 1rem;
            margin: 2rem 0;
            padding: 1rem;
            background: #f5f3ff;
            border-radius: 8px;
            text-align: center;
        }

        @media (max-width: 768px) {
            .container {
                padding: 1.5rem;
            }

            h1 {
                font-size: 2rem;
            }

            .company-avatar {
                width: 120px;
                height: 120px;
                margin: 1.5rem 0;
            }
        }
    </style>
</head>

<body>
<div class="container">
    <h1>${company.name}</h1>
    <div class="subtitle">
        Контактный телефон: <strong>${company.phoneNumber}</strong>
    </div>

    <div class="company-avatar">
        <img src="/user/avatars/${company.avatar}" alt="Аватар компании" onerror="this.onerror=null; this.src='/image/Default.png'">
    </div>

    <#if company.vacancies?has_content>
        <h2>Открытые вакансии</h2>
        <div class="vacancy-list">
            <#list company.vacancies as vacancy>
                <div class="vacancy-card">
                    <div class="vacancy-title">${vacancy.description}</div>
                    <div class="vacancy-details">
                        📅 Обновлено: ${vacancy.updateTime}<br>
                        🧳 Опыт: ${vacancy.expFrom}<br>
                        🧑‍💼 Автор: ${vacancy.user.name}
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <p>Пока что нет доступных вакансий от этой компании.</p>
    </#if>
</div>

</body>
</html>