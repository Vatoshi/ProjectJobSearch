<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/resume.css">
    <title>Создание вакансии</title>
</head>

<body>
<div class="resume-container">
    <div class="resume-header">
        <h1 class="resume-title">Создание вакансии</h1>
    </div>

    <form id="resumeForm" method="post">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>
        <div class="form-section">
            <div class="form-group">
                <label for="name">Название резюме:</label>
                <@spring.formInput "vacancyDto.name" 'placeholder="Название вакансии"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <label for="categoryId">Категория:</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="" disabled selected>Выберите категорию</option>
                    <option value="2">IT</option>
                    <option value="3">Маркетинг</option>
                    <option value="4">Дизайн</option>
                    <option value="5">Финансы</option>
                    <option value="6">Менеджмент</option>
                </select>
            </div>

            <div class="form-group">
                <label for="salary">Ожидаемый уровень оклада:</label>
                <@spring.formInput "vacancyDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="form-group">
                <label for="description">Описание</label>
                <@spring.formInput "vacancyDto.description" 'placeholder="Описание"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group experience-block">
                <label>Требуемый опыт:</label>
                <div class="experience-range">
                    <label for="experienceFrom">от</label>
                    <@spring.formInput "vacancyDto.expFrom" 'type="number" id="experienceFrom" name="experienceFrom" min="0" step="1" placeholder="лет"'></@spring.formInput>
                    <@spring.showErrors "<br>" "error"/>

                    <label for="experienceTo">до</label>
                    <@spring.formInput "vacancyDto.expTo" 'type="number" id="experienceTo" name="experienceTo" min="0" step="1" placeholder="лет"'></@spring.formInput>
                    <@spring.showErrors "<br>" "error"/>
                </div>
            </div>
        </div>

        <div class="switch-container">
            <label class="switch">
                <input type="checkbox" id="isActive" name="isActive" checked>
                <span class="slider"></span>
            </label>
            <span class="switch-label">Активно</span>
        </div>

        <button type="submit" class="submit-btn">Сохранить резюме</button>
    </form>
</div>
</body>
</html>