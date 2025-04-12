<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile-edit.css">
    <title>Редактирование профиля</title>
</head>
<body>
    <div class="profile-edit-container">
        <div class="profile-header">
            <div class="avatar-container">
                <img src="https://i.pravatar.cc/150?img=30" alt="Аватар" class="avatar">
                <label class="avatar-upload" title="Изменить фото">
                    <input type="file" name="avatar" accept="image/*">
                    <span>+</span>
                </label>
            </div>
            <h2 class="profile-title">Редактирование профиля</h2>
        </div>
        
        <form id="profileForm" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Имя:</label>
                <input type="text" id="name" name="name" 
                       pattern="^\p{L}+$"
                       title="Только буквы"
                       minlength="1" maxlength="20"
                       value="Иван">
                <div class="error" id="nameError"></div>
            </div>
            
            <div class="form-group">
                <label for="surname">Фамилия:</label>
                <input type="text" id="surname" name="surname"
                       pattern="^\p{L}+$"
                       title="Только буквы"
                       minlength="1" maxlength="20"
                       value="Иванов">
                <div class="error" id="surnameError"></div>
            </div>
            
            <div class="form-group">
                <label for="age">Возраст:</label>
                <input type="number" id="age" name="age" min="1" max="120"
                       value="55">
                <div class="error" id="ageError"></div>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email"
                       value="ivanov@example.com">
                <div class="error" id="emailError"></div>
            </div>
            
            <div class="form-group">
                <label for="phoneNumber">Номер телефона:</label>
                <input type="text" id="phoneNumber" name="phoneNumber"
                       value="+7 (123) 456-78-90">
                <div class="error" id="phoneError"></div>
            </div>
            
            <div class="form-group">
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" disabled
                       placeholder="••••••••">
                <div class="error" id="passwordError"></div>
                <div class="password-change">
                    <a href="/change-password">Изменить пароль</a>
                </div>
            </div>
            
            <button type="submit" class="submit-btn">Сохранить изменения</button>
        </form>
    </div>

</body>
</html>