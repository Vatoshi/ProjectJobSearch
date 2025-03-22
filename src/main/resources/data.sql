INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('Иван', 'Иванов', 30, 'ivan@example.com', 'password123', '+1234567890', 'avatar1.png', 'applicant'),
    ('Петр', 'Петров', 40, 'petr@example.com', 'password456', '+0987654321', 'avatar2.png', 'employer');

INSERT INTO categories (name, parent_id)
VALUES
    ('IT', NULL),
    ('Маркетинг', NULL);

INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    (1, 'Резюме Ивана (IT)', 1, 50000.00, TRUE, NOW(), NOW()),
    (1, 'Резюме Ивана (Маркетинг)', 2, 60000.00, TRUE, NOW(), NOW());

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Вакансия 1 (IT)', 'Описание вакансии 1', 1, 70000.00, 2, 5, TRUE, 2, NOW(), NOW()),
    ('Вакансия 2 (Маркетинг)', 'Описание вакансии 2', 2, 80000.00, 3, 6, TRUE, 2, NOW(), NOW());

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, TRUE);

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES (1, 'Университет 1', 'Программа 1', '2010-09-01', '2014-06-30', 'Бакалавр');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES (1, 5, 'Компания 1', 'Должность 1', 'Обязанности 1');

INSERT INTO contact_types (type)
VALUES ('Email'), ('Phone');

INSERT INTO contacts_info (type_id, resume_id, "value")
VALUES (1, 1, 'ivan@example.com');