<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/resume.css">
    <link rel="icon" href="/image/icon.png" type="image/png">
    <title><@spring.message "form.resume.createResume"/></title>
</head>

<body>
<div class="resume-container">
    <div class="resume-header">
        <h1 class="resume-title"><@spring.message "form.resume.createResume"/></h1>
    </div>

    <form id="resumeForm" method="post">
        <#if _csrf??><input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/></#if>
        <div class="form-section">
            <div class="form-group">
                <label for="name"><@spring.message "form.resume.resumeName"/></label>
                <@spring.formInput "resumeDto.name" 'placeholder="Название резюме"'></@spring.formInput>
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
                <#if errorResume?has_content>
                    <p style="color:#e12e2e;font-size: 14px;">
                        ${errorResume}
                    </p>
                </#if>
            </div>

            <div class="form-group">
                <label for="salary"><@spring.message "form.resume.expectedSalaryLevel"/></label>
                <@spring.formInput "resumeDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
        </div>

        <div class="form-section">
            <h3 class="section-title"><@spring.message "form.resume.exp"/></h3>
                <div class="experience-item">
                    <div class="form-group">
                        <label for="workExperienceInfo[0].years"><@spring.message "form.resume.expTo"/></label>
                        <@spring.formInput "resumeDto.workExperienceInfo[0].years" 'placeholder="Сколько лет вы отработали"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="workExperienceInfo[0].companyName"><@spring.message "form.resume.companyName"/></label>
                        <@spring.formInput "resumeDto.workExperienceInfo[0].companyName" 'placeholder="Введите название компании"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="workExperienceInfo[0].position"><@spring.message "form.resume.position"/></label>
                        <@spring.formInput "resumeDto.workExperienceInfo[0].position" 'placeholder="Ваша должность"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="workExperienceInfo[0].responsibilities"><@spring.message "form.resume.responsibilities"/></label>
                        <@spring.formTextarea "resumeDto.workExperienceInfo[0].responsibilities" 'placeholder="Опишите свои обязанности"'></@spring.formTextarea>
                        <@spring.showErrors "<br>" "error"/>
                    </div>
                </div>
        </div>

        <div class="form-section">
            <h3 class="section-title"><@spring.message "form.resume.education"/></h3>
                <div class="education-item">
                    <div class="form-group">
                        <label for="educationInfo[0].institution"><@spring.message "form.resume.educationInstitution"/></label>
                        <@spring.formInput "resumeDto.educationInfo[0].institution" 'placeholder="Учебное заведение"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].program"><@spring.message "form.resume.educationProgram"/></label>
                        <@spring.formInput "resumeDto.educationInfo[0].program" 'placeholder="Программа обучения"'/>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].startDate"><@spring.message "form.resume.dateStart"/></label>
                        <@spring.formInput "resumeDto.educationInfo[0].startDate" 'type="date"' 'date'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].endDate"><@spring.message "form.resume.dateEnd"/></label>
                        <@spring.formInput "resumeDto.educationInfo[0].endDate" 'type="date"' 'date'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].degree"><@spring.message "form.resume.level"/></label>
                        <@spring.formInput "resumeDto.educationInfo[0].degree" 'placeholder="Степень образования"'></@spring.formInput>
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

        <button type="submit" class="submit-btn"><@spring.message "form.resume.saveResume"/></button>
    </form>
</div>
</body>
</html>