<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Ввод кода для сброса пароля</title>
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

        .container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            width: 400px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .input-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .input-container input {
            width: 50px;
            height: 50px;
            text-align: center;
            font-size: 24px;
            border: 2px solid #ddd;
            border-radius: 8px;
            transition: border-color 0.3s;
        }

        .input-container input:focus {
            border-color: #667eea;
            outline: none;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
        }

        .btn {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            border: none;
            padding: 12px 0;
            width: 100%;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            margin-top: 10px;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .timer {
            margin-top: 15px;
            font-size: 14px;
            color: #444;
        }

        .timer span {
            color: #e74c3c;
            font-weight: bold;
        }

        .links {
            margin-top: 20px;
        }

        .links a {
            color: #667eea;
            text-decoration: none;
            transition: all 0.3s;
        }

        .links a:hover {
            text-decoration: underline;
        }

        @keyframes fadeIn {
            to {
                opacity: 1;
            }
        }

        p {
            color: #e12e2e;
            font-size: 14px;
            opacity: 0;
            animation: fadeIn 1s forwards;
            margin-top: 3px;
        }
    </style>
</head>

<body>

<div class="container">
    <h1>Введите код</h1>
    <h4 style="margin-bottom: 15px; color: gray;">${email}</h4>
    <form action="/auth/reset-code" method="post" onsubmit="return combineCode()">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="input-container">
            <input type="text" maxlength="1" id="digit1" oninput="moveFocus(1)" onkeydown="handleKeyDown(event, 1)">
            <input type="text" maxlength="1" id="digit2" oninput="moveFocus(2)" onkeydown="handleKeyDown(event, 2)">
            <input type="text" maxlength="1" id="digit3" oninput="moveFocus(3)" onkeydown="handleKeyDown(event, 3)">
            <input type="text" maxlength="1" id="digit4" oninput="moveFocus(4)" onkeydown="handleKeyDown(event, 4)">
            <input type="text" maxlength="1" id="digit5" oninput="moveFocus(5)" onkeydown="handleKeyDown(event, 5)">
            <input type="text" maxlength="1" id="digit6" oninput="moveFocus(6)" onkeydown="handleKeyDown(event, 6)">
        </div>
        <#if invalid?has_content>
            <p style="color:#e12e2e;font-size: 14px;">
                ${invalid}
            </p>
        </#if>
        <input type="hidden" name="fullCode" id="fullCode">
        <button class="btn" type="submit">Подтвердить код</button>
    </form>
</div>

<#noparse>
<script>
    function moveFocus(current) {
        var currentInput = document.getElementById('digit' + current);
        if (currentInput.value.length === 1 && current < 6) {
            document.getElementById('digit' + (current + 1)).focus();
        }
    }

    function handleKeyDown(event, current) {
        var input = document.getElementById('digit' + current);

        if (event.key === "Backspace" && input.value === "" && current > 1) {
            document.getElementById('digit' + (current - 1)).focus();
        }
    }

    function combineCode() {
        var code =
            document.getElementById('digit1').value +
            document.getElementById('digit2').value +
            document.getElementById('digit3').value +
            document.getElementById('digit4').value +
            document.getElementById('digit5').value +
            document.getElementById('digit6').value;

        document.getElementById('fullCode').value = code;
        return true;
    }
</script>
</#noparse>

</body>

</html>
