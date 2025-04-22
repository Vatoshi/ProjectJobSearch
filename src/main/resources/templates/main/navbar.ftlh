<#macro navbar>
    <#if user?has_content>
        <div class="top-bar">
            <a href="/profile" class="profile">
                <div class="div-img">
                    <img src="user/avatars/${user.avatar}?t=${.now}" onerror="this.onerror=null; this.src='/image/Default.png'" alt="Фото профиля" class="avatar">
                </div>
                <p class="profile-text">Ваш <br> профиль</p>
            </a>
        </div>
    <#else>
        <div class="top-bar">
            <a href="/auth/login" class="profile" style="justify-content: center" >
                <div style="font-weight: 600;">Войти в аккаунт</div>
            </a>
        </div>
    </#if>
</#macro>
