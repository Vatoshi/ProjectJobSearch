<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/resume.css">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <title><@spring.message "form.vacancy.createVacancy"/></title>
</head>

<body>
<div class="resume-container">
    <div class="resume-header">
        <h1 class="resume-title"><@spring.message "form.vacancy.createVacancy"/></h1>
    </div>

    <form id="resumeForm" method="post">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>
        <div class="form-section">
            <div class="form-group">
                <label for="name"><@spring.message "form.vacancy.vacancyName"/></label>
                <@spring.formInput "vacancyDto.name" 'placeholder="Название вакансии"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <label for="categoryId"><@spring.message "category"/></label>
                <select id="categoryId" name="categoryId">
                    <option value="" disabled selected><@spring.message "selectCategory"/></option>
                    <option value="2">IT</option>
                    <option value="3"><@spring.message "marketing"/></option>
                    <option value="4"><@spring.message "design"/></option>
                    <option value="5"><@spring.message "finance"/></option>
                    <option value="6"><@spring.message "management"/></option>
                </select>
                <#if errorVacancy?has_content>
                    <p style="color:#e12e2e;font-size: 14px;">
                        ${errorVacancy}
                    </p>
                </#if>
            </div>

            <div class="form-group">
                <label for="salary"><@spring.message "form.vacancy.expectedSalaryLevel"/></label>
                <@spring.formInput "vacancyDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="form-group">
                <label for="description"><@spring.message "description"/></label>
                <@spring.formInput "vacancyDto.description" 'placeholder="Описание"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group experience-block">
                <label><@spring.message "form.vacancy.requiredExperience"/></label>
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
            <span class="switch-label"><@spring.message "actively"/></span>
        </div>

        <button type="submit" class="submit-btn"><@spring.message "edit.vacancy.saveChanges"/></button>
    </form>
</div>
</body>
</html>