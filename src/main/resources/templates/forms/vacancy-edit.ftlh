<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <link rel="stylesheet" href="/css/resume.css">
    <title><@spring.message "edit.vacancy.vacancyEdit"/></title>
</head>

<body>
<div class="resume-container">
    <div class="resume-header">
        <h1 class="resume-title"><@spring.message "edit.vacancy.vacancyEdit"/></h1>
    </div>

    <form id="resumeForm" method="post">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>
        <div class="form-section">
            <div class="form-group">
                <label for="name"><@spring.message "edit.vacancy.vacancyName"/></label>
                <@spring.formInput "vacancyEditDto.name" 'placeholder="Название вакансии"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group">
                <label for="categoryId"><@spring.message "category"/></label>
                <select id="categoryId" name="categoryId" required>
                    <option value="" disabled <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId?string == "">selected</#if>><@spring.message "selectCategory"/></option>
                    <option value="2" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 2>selected</#if>>IT</option>
                    <option value="3" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 3>selected</#if>>Маркетинг</option>
                    <option value="4" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 4>selected</#if>>Дизайн</option>
                    <option value="5" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 5>selected</#if>>Финансы</option>
                    <option value="6" <#if vacancyEditDto.categoryId?? && vacancyEditDto.categoryId == 6>selected</#if>>Менеджмент</option>
                </select>
            </div>

            <div class="form-group">
                <label for="salary"><@spring.message "edit.resume.salaryLevel"/></label>
                <@spring.formInput "vacancyEditDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="form-group">
                <label for="description"><@spring.message "description"/></label>
                <@spring.formInput "vacancyEditDto.description" 'placeholder="Описание"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>

            <div class="form-group experience-block">
                <label><@spring.message "edit.vacancy.requiredExperience"/></label>
                <div class="experience-range">
                    <label for="experienceFrom"><@spring.message "from"/></label>
                    <@spring.formInput "vacancyEditDto.expFrom" 'type="number" id="experienceFrom" name="experienceFrom" min="0" step="1" placeholder="лет"'></@spring.formInput>
                    <@spring.showErrors "<br>" "error"/>

                    <label for="experienceTo"><@spring.message "to"/></label>
                    <@spring.formInput "vacancyEditDto.expTo" 'type="number" id="experienceTo" name="experienceTo" min="0" step="1" placeholder="лет"'></@spring.formInput>
                    <@spring.showErrors "<br>" "error"/>
                </div>
            </div>
        </div>

        <div class="switch-container">
            <label class="switch">
                <#if vacancyEditDto.isActive?? && vacancyEditDto.isActive == true>
                    <input type="checkbox" id="isActive" name="isActive" checked/>
                <#else>
                    <input type="checkbox" id="isActive" name="isActive"/>
                </#if>
                <span class="slider"></span>
            </label>
            <span class="switch-label"><@spring.message "actively"/></span>
        </div>

        <button type="submit" class="submit-btn"><@spring.message "edit.vacancy.saveChanges"/></button>
    </form>
</div>
</body>
</html>