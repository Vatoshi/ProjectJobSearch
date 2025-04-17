<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/resume.css">
    <title>Редактирование вакансии</title>
</head>

<body>
<div class="resume-container">
    <div class="resume-header">
        <h1 class="resume-title">Редактирование вакансии</h1>
    </div>

    <form id="resumeForm" method="post">
        <div class="form-section">
            <div class="form-group">
                <label for="name">Название резюме:</label>
                <@spring.formInput "vacancyEditDto.name" 'placeholder="Название вакансии"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <label for="categoryId">Категория:</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="" disabled <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId?string == "">selected</#if>>Выберите категорию</option>
                    <option value="2" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 2>selected</#if>>IT</option>
                    <option value="3" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 3>selected</#if>>Маркетинг</option>
                    <option value="4" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 4>selected</#if>>Дизайн</option>
                    <option value="5" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 5>selected</#if>>Финансы</option>
                    <option value="6" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 6>selected</#if>>Менеджмент</option>
                </select>
            </div>

            <div class="form-group">
                <label for="salary">уровень оклада:</label>
                <@spring.formInput "vacancyEditDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="form-group">
                <label for="description">Описание</label>
                <@spring.formInput "vacancyEditDto.description" 'placeholder="Описание"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group experience-block">
                <label>Требуемый опыт: (от не может быть больше чем до)</label>
                <div class="experience-range">
                    <label for="experienceFrom">от</label>
                    <@spring.formInput "vacancyEditDto.expFrom" 'type="number" id="experienceFrom" name="experienceFrom" min="0" step="1" placeholder="лет"'></@spring.formInput>
                    <@spring.showErrors "<br>" "error"/>

                    <label for="experienceTo">до</label>
                    <@spring.formInput "vacancyEditDto.expTo" 'type="number" id="experienceTo" name="experienceTo" min="0" step="1" placeholder="лет"'></@spring.formInput>
                    <@spring.showErrors "<br>" "error"/>
                </div>
            </div>
        </div>

        <div class="switch-container">
            <label class="switch">
                <input type="checkbox" id="isActive" name="isActive" value="true"
                       <#if vacancyEditDto.isActive?? && vacancyEditDto.isActive>checked</#if> />
                <input type="hidden" name="_isActive" value="on" />
                <span class="slider"></span>
            </label>
            <span class="switch-label">Активно</span>
        </div>

        <button type="submit" class="submit-btn">Сохранить резюме</button>
    </form>
</div>
</body>
</html>