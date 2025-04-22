<#import "navbar.ftlh" as nav>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Frontend Developer – Подробности</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Inter', sans-serif;
            background-color: #f7f9fc;
            color: #1a1a1a;
            overflow-x: hidden;
        }

        .container {
            max-width: 1000px;
            margin: 40px auto;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            padding: 32px;
            border: 2px solid #a084e8;
            animation: fadeSlideUp 0.5s ease forwards;
            opacity: 0;
        }

        @keyframes fadeSlideUp {
            0% {
                opacity: 0;
                transform: translateY(30px);
            }

            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h1,
        .section h2 {
            opacity: 0;
            animation: fadeIn 1s ease forwards;
            animation-delay: 0.3s;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 8px;
        }

        .section h2 {
            font-size: 20px;
            margin-bottom: 8px;
        }

        @keyframes fadeIn {
            to {
                opacity: 1;
            }
        }

        .subtitle {
            font-size: 16px;
            color: #666;
            margin-bottom: 24px;
            animation: fadeIn 1s ease forwards;
            animation-delay: 0.5s;
            opacity: 0;
        }

        .section {
            margin-top: 25px;
            margin-bottom: 24px;
            animation: fadeIn 1s ease forwards;
            animation-delay: 0.6s;
            opacity: 0;
        }

        .section:nth-of-type(2) {
            animation-delay: 0.7s;
        }

        .section:nth-of-type(3) {
            animation-delay: 0.8s;
        }

        .section p,
        .section ul {
            margin: 0;
            line-height: 1.6;
        }

        ul {
            padding-left: 20px;
        }

        .apply-button {
            background: linear-gradient(to right, #a084e8, #bf7cf4);
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            box-shadow: 0 4px 8px rgba(160, 132, 232, 0.3);
        }

        .apply-button:hover {
            transform: translateY(-3px) scale(1.05);
            box-shadow: 0 8px 16px rgba(160, 132, 232, 0.4);
        }

        .apply-button:active {
            transform: scale(0.98);
        }

        .salary {
            font-weight: 600;
            color: #7c3aed;
            font-size: 18px;
        }

        .info-row {
            margin-top: 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            animation: fadeIn 1s ease forwards;
            animation-delay: 1s;
            opacity: 0;
        }
    </style>
</head>

<body>

<div class="container">
    <h1>Frontend Developer</h1>
    <div class="subtitle">Последняя дата обновления : ${vacancy.updateTime} • Опыт: ${vacancy.expFrom} • Автор: ${vacancy.author}</div>

    <div class="section">
        <h2>Описание</h2>
        <p>
            ${vacancy.description}
        </p>
    </div>

    <div class="info-row">
        <div class="salary">Зарплата: ${vacancy.salary} ₽</div>
        <button class="apply-button">Подать заявку</button>
    </div>
</div>

</body>

</html>
