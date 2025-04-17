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
        <h1 class="resume-title">Создание резюме</h1>
    </div>

    <form id="resumeForm" method="post">
        <div class="form-section">
            <div class="form-group">
                <label for="name">Название резюме:</label>
                <@spring.formInput "resumeDto.name" 'placeholder="Название резюме"'></@spring.formInput>
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
                <@spring.formInput "resumeDto.salary" 'placeholder="Зарплата"'></@spring.formInput>
                <@spring.showErrors "<br>" "error"/>
            </div>
        </div>

        <div class="form-section">
            <h3 class="section-title">Опыт работы</h3>
                <div class="experience-item">
                    <div class="form-group">
                        <label for="workExperienceInfo[0].years">Год работы:</label>
                        <@spring.formInput "resumeDto.workExperienceInfo[0].years" 'placeholder="Сколько лет вы отработали"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="workExperienceInfo[0].companyName">Название компании:</label>
                        <@spring.formInput "resumeDto.workExperienceInfo[0].companyName" 'placeholder="Введите название компании"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="workExperienceInfo[0].position">Должность:</label>
                        <@spring.formInput "resumeDto.workExperienceInfo[0].position" 'placeholder="Ваша должность"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="workExperienceInfo[0].responsibilities">Обязанности:</label>
                        <@spring.formTextarea "resumeDto.workExperienceInfo[0].responsibilities" 'placeholder="Опишите свои обязанности"'></@spring.formTextarea>
                        <@spring.showErrors "<br>" "error"/>
                    </div>
                </div>
        </div>

        <div class="form-section">
            <h3 class="section-title">Образование</h3>
                <div class="education-item">
                    <div class="form-group">
                        <label for="educationInfo[0].institution">Учебное заведение:</label>
                        <@spring.formInput "resumeDto.educationInfo[0].institution" 'placeholder="Учебное заведение"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].program">Программа обучения:</label>
                        <@spring.formInput "resumeDto.educationInfo[0].program" 'placeholder="Программа обучения"'></@spring.formInput>
                        <@spring.showErrors "<br>" "error"/>
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].startDate">Дата начала:</label>
<#--                        <@spring.formInput "educationInfo[0].startDate" 'type="date"'></@spring.formInput>-->
                        <input name="resumeDto.educationInfo[0].startDate" type="date">
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].endDate">Дата окончания:</label>
                        <input name="resumeDto.educationInfo[0].endDate" type="date">
                    </div>

                    <div class="form-group">
                        <label for="educationInfo[0].degree">Степень:</label>
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
            <span class="switch-label">Активно</span>
        </div>

        <button type="submit" class="submit-btn">Сохранить резюме</button>
    </form>
</div>
</body>
</html>