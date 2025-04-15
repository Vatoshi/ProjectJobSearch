insert into users(name, surname, age, email, password, phone_number, avatar, enabled, role_id)
values
    ('Иван', 'Иванов', 30, 'ivan@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1234567890', 'Default.png', true, 1),
    ('Петр', 'Петров', 40, 'petr@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+0987654321', 'Default.png', true, 2),
    ('Эмир', 'Кадыров', 16, 'emirchik@gmail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1231342345', 'Default.png', true, 1),
    ('Александр', 'Сидоров', 35, 'alexandr.sidorov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1122334455', 'Default.png', true, 1),
    ('Анна', 'Петрова', 28, 'anna.petrovna@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1222333445', 'Default.png', true, 2),
    ('Дмитрий', 'Смирнов', 32, 'dmitriy.smirnov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1332444556', 'Default.png', true, 1),
    ('Мария', 'Михайлова', 25, 'maria.mikhaylova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1443556677', 'Default.png', true, 2),
    ('Николай', 'Горбунов', 40, 'nikolay.gorbunov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1554667788', 'Default.png', true, 1),
    ('Светлана', 'Орлова', 29, 'svetlana.orlova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1665778899', 'Default.png', true, 2),
    ('Ирина', 'Соколова', 26, 'irina.sokolova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1776889900', 'Default.png', true, 2),
    ('Максим', 'Лебедев', 37, 'maksim.lebedev@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1887990011', 'Default.png', true, 1),
    ('Татьяна', 'Новикова', 31, 'tatiana.novikova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1999001122', 'Default.png', true, 2),
    ('Екатерина', 'Сухова', 23, 'ekaterina.sukhova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1000112233', 'Default.png', true, 1),
    ('Константин', 'Рыбаков', 33, 'konstantin.rybakov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+1112233444', 'Default.png', true, 2);

insert into categories (name) values ('основные категории');
insert into categories (name, parent_id) values
                                             ('it', (select id from categories where name = 'основные категории')),
                                             ('маркетинг', (select id from categories where name = 'основные категории')),
                                             ('дизайн', (select id from categories where name = 'основные категории')),
                                             ('финансы', (select id from categories where name = 'основные категории')),
                                             ('менеджмент', (select id from categories where name = 'основные категории'));

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Frontend Developer', 'Создание пользовательских интерфейсов с использованием HTML, CSS и JavaScript. Работы с фреймворками и оптимизация производительности.',
       (select id from categories where name = 'it'), 70000.00, 2, 5, true, (select id from users where email = 'ivan@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Backend Developer', 'Разработка серверной части приложений, работа с базами данных, оптимизация API и микросервисов.',
       (select id from categories where name = 'it'), 75000.00, 3, 6, true, (select id from users where email = 'petr@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Full-stack Developer', 'Создание полнофункциональных веб-приложений с фронтендом и бэкендом, интеграция различных сервисов и API.',
       (select id from categories where name = 'it'), 80000.00, 4, 7, true, (select id from users where email = 'alexandr.sidorov@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Marketing Manager', 'Разработка и реализация маркетинговых стратегий, управление рекламными кампаниями и анализ рынка.',
       (select id from categories where name = 'маркетинг'), 60000.00, 1, 3, true, (select id from users where email = 'anna.petrovna@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'SEO Specialist', 'Оптимизация сайтов для поисковых систем, анализ ключевых слов и улучшение позиций в поисковых системах.',
       (select id from categories where name = 'маркетинг'), 65000.00, 2, 5, true, (select id from users where email = 'dmitriy.smirnov@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Content Manager', 'Разработка контент-стратегий, написание текстов и управление социальными сетями компании.',
       (select id from categories where name = 'маркетинг'), 70000.00, 3, 6, true, (select id from users where email = 'dmitriy.smirnov@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Graphic Designer', 'Разработка визуальных концепций, дизайн рекламных материалов, создание логотипов и брендинга.',
       (select id from categories where name = 'дизайн'), 55000.00, 1, 3, true, (select id from users where email = 'irina.sokolova@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'UI/UX Designer', 'Проектирование удобных интерфейсов и пользовательских опытов для мобильных и веб-приложений.',
       (select id from categories where name = 'дизайн'), 60000.00, 2, 5, true, (select id from users where email = 'maksim.lebedev@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Financial Analyst', 'Анализ финансовых данных, составление отчетности и рекомендации для улучшения финансовых показателей компании.',
       (select id from categories where name = 'финансы'), 80000.00, 3, 6, true, (select id from users where email = 'svetlana.orlova@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Business Analyst', 'Анализ бизнес-процессов, выявление требований к системам и консультирование по вопросам автоматизации бизнеса.',
       (select id from categories where name = 'финансы'), 85000.00, 4, 7, true, (select id from users where email = 'svetlana.orlova@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Data Scientist', 'Анализ данных, создание предсказательных моделей и работа с большими данными для извлечения полезных инсайтов.',
       (select id from categories where name = 'it'), 90000.00, 5, 8, true, (select id from users where email = 'maksim.lebedev@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'DevOps Engineer', 'Автоматизация процессов разработки, управление инфраструктурой, настройка CI/CD и работа с облачными сервисами.',
       (select id from categories where name = 'it'), 95000.00, 4, 7, true, (select id from users where email = 'maksim.lebedev@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Cloud Engineer', 'Разработка и поддержка облачных решений, настройка инфраструктуры и работа с облачными провайдерами.',
       (select id from categories where name = 'it'), 100000.00, 5, 8, true, (select id from users where email = 'anna.petrovna@example.com'), current_timestamp, current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select 'Network Administrator', 'Управление и поддержка сетевой инфраструктуры компании, обеспечение безопасности и надежности работы сети.',
       (select id from categories where name = 'it'), 70000.00, 2, 5, true, (select id from users where email = 'anna.petrovna@example.com'), current_timestamp, current_timestamp;


insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'ivan@example.com'), 'резюме 1 (it)', (select id from categories where name = 'it'), 50000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'ivan@example.com'), 'резюме 2 (it)', (select id from categories where name = 'it'), 55000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'ivan@example.com'), 'резюме 3 (маркетинг)', (select id from categories where name = 'маркетинг'), 60000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'petr@example.com'), 'резюме 4 (маркетинг)', (select id from categories where name = 'маркетинг'), 65000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'petr@example.com'), 'резюме 5 (дизайн)', (select id from categories where name = 'дизайн'), 70000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'nikolay.gorbunov@example.com'), 'резюме 6 (дизайн)', (select id from categories where name = 'дизайн'), 75000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'svetlana.orlova@example.com'), 'резюме 7 (финансы)', (select id from categories where name = 'финансы'), 80000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'svetlana.orlova@example.com'), 'резюме 8 (финансы)', (select id from categories where name = 'финансы'), 85000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'konstantin.rybakov@example.com'), 'резюме 9 (менеджмент)', (select id from categories where name = 'менеджмент'), 90000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'konstantin.rybakov@example.com'), 'резюме 10 (менеджмент)', (select id from categories where name = 'менеджмент'), 95000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'alexandr.sidorov@example.com'), 'резюме 11 (it)', (select id from categories where name = 'it'), 100000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'alexandr.sidorov@example.com'), 'резюме 12 (it)', (select id from categories where name = 'it'), 105000.00, true, current_timestamp, current_timestamp;
insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select (select id from users where email = 'dmitriy.smirnov@example.com'), 'резюме 13 (маркетинг)', (select id from categories where name = 'маркетинг'), 110000.00, true, current_timestamp, current_timestamp;


