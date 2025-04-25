<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/resume.css">
    <title>Создание резюме</title>
</head>

<body>
<div class="resume-container">
    <div class="resume-header">
        <h1 class="resume-title">Редактирование резюме</h1>
    </div>

    <form id="resumeForm" method="post">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>
        <div class="form-section">
            <div class="form-group">
                <label for="name">Название резюме:</label>
                <@spring.formInput "resumeDto.name" 'placeholder="Название резюме"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <label for="categoryId">Категория:</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="" disabled <#if resumeDto.categoryId?? && resumeDto.categoryId?string == "">selected</#if>>Выберите категорию</option>
                    <option value="2" <#if resumeDto.categoryId?? && resumeDto.categoryId == 2>selected</#if>>IT</option>
                    <option value="3" <#if resumeDto.categoryId?? && resumeDto.categoryId == 3>selected</#if>>Маркетинг</option>
                    <option value="4" <#if resumeDto.categoryId?? && resumeDto.categoryId == 4>selected</#if>>Дизайн</option>
                    <option value="5" <#if resumeDto.categoryId?? && resumeDto.categoryId == 5>selected</#if>>Финансы</option>
                    <option value="6" <#if resumeDto.categoryId?? && resumeDto.categoryId == 6>selected</#if>>Менеджмент</option>
                </select>
            </div>

            <div class="form-group">
                <label for="salary">уровень оклада:</label>
                <@spring.formInput "resumeDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
        </div>

        <div class="switch-container">
            <label class="switch">
                <#if resumeDto.isActive?? && resumeDto.isActive == true>
                <input type="checkbox" id="isActive" name="isActive" checked/>
                <#else>
                    <input type="checkbox" id="isActive" name="isActive"/>
                </#if>
                <span class="slider"></span>
            </label>
            <span class="switch-label">Активно</span>
        </div>

        <button type="submit" class="submit-btn">Сохранить изменения</button>
    </form>
</div>
</body>
</html>