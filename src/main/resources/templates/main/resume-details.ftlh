<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        :root {
            --cv-primary: #6a46eacc;
            --cv-secondary: #8739fb;
            --cv-light: #f8f9fa;
            --cv-dark: #2d3748;
            --cv-gray: #718096;
            --cv-light-gray: #e2e8f0;
            --cv-error: #e53e3e;
            --cv-success: #38a169;
        }

        .cv-resume-page * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .cv-resume-page {
            background: #f5f7fa;
            min-height: 100vh;
            padding: 40px 20px;
            color: var(--cv-dark);
            display: flex;
            flex-direction: column;
        }

        .cv-page-container {
            margin-top: 50px;
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .cv-resume-container {
            max-width: 800px;
            width: 100%;
            margin: 0 auto;
            background: white;
            border-radius: 12px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
            padding: 40px;
            animation: cv-fadeIn 0.5s ease-out;
            flex: 1;
            position: relative;
            margin-bottom: 80px;
        }

        .cv-back-button-container {
            position: fixed;
            bottom: 20px;
            left: 0;
            right: 0;
            display: flex;
            justify-content: center;
            z-index: 100;
        }

        .cv-back-button {
            background: rgba(106, 70, 234, 0.9);
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 30px;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s;
            display: flex;
            align-items: center;
            text-decoration: none;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        .cv-back-button:hover {
            background: rgba(106, 70, 234, 1);
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.25);
        }

        .cv-back-button::before {
            content: "←";
            margin-right: 8px;
            font-weight: bold;
        }

        @keyframes cv-fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .cv-resume-header {
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 1px solid var(--cv-light-gray);
        }

        .cv-resume-title {
            font-size: 28px;
            color: var(--cv-dark);
            margin-bottom: 10px;
            font-weight: 600;
        }

        .cv-resume-subtitle {
            color: var(--cv-gray);
            font-size: 16px;
            margin-bottom: 15px;
        }

        .cv-status-badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: 500;
            background-color: ${resume.isActive?then('#38a16920', '#e53e3e20')};
            color: ${resume.isActive?then('var(--cv-success)', 'var(--cv-error)')};
        }

        .cv-form-section {
            margin-bottom: 30px;
        }

        .cv-section-title {
            font-size: 20px;
            font-weight: 600;
            color: var(--cv-primary);
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            position: relative;
            padding-left: 15px;
        }

        .cv-section-title::before {
            content: '';
            position: absolute;
            left: 0;
            top: 2px;
            width: 4px;
            height: 20px;
            background: linear-gradient(to bottom, var(--cv-primary), var(--cv-secondary));
            border-radius: 2px;
        }

        .cv-info-item {
            margin-bottom: 25px;
        }

        .cv-info-item h3 {
            font-size: 18px;
            color: var(--cv-dark);
            margin-bottom: 5px;
        }

        .cv-info-item .cv-company {
            font-weight: 600;
            color: var(--cv-secondary);
        }

        .cv-info-item .cv-period {
            color: var(--cv-gray);
            font-size: 14px;
            margin: 5px 0;
            display: flex;
            align-items: center;
        }

        .cv-info-item .cv-period::before {
            content: '📅';
            margin-right: 6px;
        }

        .cv-info-item .cv-description {
            margin-top: 10px;
            line-height: 1.6;
            color: var(--cv-dark);
        }

        .cv-skills-container {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-top: 15px;
        }

        .cv-skill-tag {
            background: rgba(106, 70, 234, 0.1);
            color: var(--cv-primary);
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 14px;
        }

        .cv-salary-value {
            font-size: 20px;
            font-weight: 600;
            color: var(--cv-success);
        }

        .cv-divider {
            height: 1px;
            background: var(--cv-light-gray);
            margin: 30px 0;
        }

        @media (max-width: 768px) {
            .cv-resume-container {
                padding: 30px 20px;
                margin-bottom: 70px;
            }

            .cv-resume-title {
                font-size: 24px;
            }

            .cv-section-title {
                font-size: 18px;
            }

            .cv-back-button {
                padding: 10px 20px;
                font-size: 15px;
            }
        }
    </style>

<div class="cv-page-container">
    <div class="cv-resume-container">
        <div class="cv-resume-header">
            <h1 class="cv-resume-title">${resume.name!''}</h1>

            <#if resume.salary??>
                <p class="cv-resume-subtitle">
                    Желаемая зарплата: <span class="cv-salary-value">${resume.salary?string["#,###"]} ₽</span>
                </p>
                <#if resume.author??>
                <p class="cv-resume-subtitle">
                    Пользователь: <span class="cv-salary-value">${resume.author}</span>
                </p>
                </#if>
            </#if>

            <span class="cv-status-badge">
                ${resume.isActive?then('Активно ищет работу', 'Не ищет работу')}
            </span>
        </div>

        <div class="cv-form-section">
            <h2 class="cv-section-title">Основная информация</h2>
            <div class="cv-info-item">
                <p><strong>Дата создания резюме:</strong> ${(resume.createdDate!''?string["dd.MM.yyyy"])!''}</p><br>
                <p><strong>Последнее обновление:</strong> ${(resume.updateTime!''?string["dd.MM.yyyy"])!''}</p>
            </div>
        </div>

        <div class="cv-divider"></div>

        <#if resume.workExperienceInfo?? && resume.workExperienceInfo?size gt 0>
            <div class="cv-form-section">
                <h2 class="cv-section-title">Опыт работы</h2>
                <#list resume.workExperienceInfo as experience>
                    <div class="cv-info-item">
                        <h3><span class="cv-company">${experience.companyName!''}</span></h3>
                        <p><strong>Должность:</strong> ${experience.position!''}</p>
                        <#if experience.responsibilities??>
                            <div class="cv-description">
                                <strong>Обязанности:</strong>
                                <p>${experience.responsibilities!''}</p>
                            </div>
                        </#if>
                    </div>
                    <#if experience?has_next><div class="cv-divider"></div></#if>
                </#list>
            </div>
        </#if>

        <#if resume.educationInfo?? && resume.educationInfo?size gt 0>
            <div class="cv-form-section">
                <h2 class="cv-section-title">Образование</h2>
                <#list resume.educationInfo as education>
                    <div class="cv-info-item">
                        <h3><span class="cv-company">${education.institution!''}</span></h3>
                        <p><strong>Специальность:</strong> ${education.program!''}</p>
                        <p><strong>Степень:</strong> ${education.degree!''}</p>
                        <div class="cv-period">
                            Год окончания: ${(education.endDate!'')}
                        </div>
                    </div>
                    <#if education?has_next><div class="cv-divider"></div></#if>
                </#list>
            </div>
        </#if>

        <#if user.id != 1>
        <a href="#" class="cv-back-button" style="padding: 8px 16px; font-size: 14px; border-radius: 20px;">
            Откликнуться
        </a>
        </#if>
    </div>

    <div class="cv-back-button-container">
        <a href="javascript:history.back()" class="cv-back-button">Вернуться назад</a>
    </div>
</div>
</@main.layout>