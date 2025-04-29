
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile.css">
    <title>Личный кабинет <#if user.role.id==1> соискателя <#else> работодателя </#if> </title>
</head>

<body>
<div class="container">
    <div class="sidebar">
        <div class="profile-header">
            <img src="user/avatars/${user.avatar}" onerror="this.onerror=null; this.src='/image/Default.png'" alt="Фото профиля" class="avatar">
            <h2 class="profile-name">${user.name}</h2>
            <div class="profile-age">Возраст: ${user.age}</div>
            <a href="/profile/edit" class="btn" id="edit-profile-btn">Редактировать</a>
        </div>
        <ul class="nav-menu">
            <li class="nav-item">
                <a href="/profile" class="nav-link" data-target="resumes">
                    <span class="nav-icon">📄</span>
                    <span>Мои <#if user.role.id==1> резюме <#else> вакансии </#if></span>
                </a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link active" data-target="responses">
                    <span class="nav-icon">✉️</span>
                    <span>Отклики</span>
                </a>
            </li>
            <li class="nav-item">
                <a <#if user.role.id == 1> href="/profile/create-resume" <#else> href="/profile/create-vacancy" </#if> class="nav-link" data-target="create">
                    <span class="nav-icon">➕</span>
                    <span>Создать <#if user.role.id==1> резюме <#else> вакансию </#if></span>
                </a>
            </li>
            <#if user.role.id == 2>
                <li class="nav-item">
                    <a href="/resumes" class="nav-link">
                        <span class="nav-icon">📌</span>
                        <span> Доступные резюме </span>
                    </a>
                </li>
            </#if>
            <li class="nav-item">
                <a href="/" class="nav-link" data-target="resumes">
                    <span class="nav-icon">🔄</span>
                    <span> К вакансиям </span>
                </a>
            </li>
        </ul>
    </div>

    <div class="main-content" id="responses">
        <h2 class="section-title">Отклики</h2>
        <p>Пока нет откликов.</p>
    </div>

</div>
</body>

</html>

