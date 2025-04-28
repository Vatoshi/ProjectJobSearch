<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Перенаправление...</title>
    <link rel="icon" href="/image/icon.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="refresh" content="4;url=${redirect}">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #becaff 0%, #ead5ff 100%);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #333;
        }

        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            width: 400px;
            padding: 40px;
            text-align: center;
            animation: fadeIn 0.5s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }

            to {
                opacity: 1;
            }
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #444;
        }

        p {
            font-size: 16px;
            color: #666;
            margin-top: 15px;
        }
    </style>
</head>

<body>

<div class="login-container">
    <h3 style="font-weight: 400; margin-bottom: 10px">${error}</h3>
    <h2>Перенаправляем вас...</h2>
    <p>Пожалуйста, подождите несколько секунд</p>
</div>

</body>

</html>
