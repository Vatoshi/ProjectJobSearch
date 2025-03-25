insert into categories (name)
values ('основные категории');

insert into categories (name, parent_id)
values
    ('it', (select id from categories where name = 'основные категории')),
    ('маркетинг', (select id from categories where name = 'основные категории'));

insert into users (name, surname, age, email, password, phone_number, avatar, account_type)
values
    ('иван', 'иванов', 30, 'ivan@example.com', 'password123', '+1234567890', 'avatar1.png', 'applicant'),
    ('петр', 'петров', 40, 'petr@example.com', 'password456', '+0987654321', 'avatar2.png', 'employer');

insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select
    (select id from users where email = 'ivan@example.com'),
    'резюме ивана (it)',
    (select id from categories where name = 'it'),
    50000.00,
    true,
    current_timestamp,
    current_timestamp;

insert into resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
select
    (select id from users where email = 'ivan@example.com'),
    'резюме ивана (маркетинг)',
    (select id from categories where name = 'маркетинг'),
    60000.00,
    true,
    current_timestamp,
    current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select
    'вакансия 1 (it)',
    'описание вакансии 1',
    (select id from categories where name = 'it'),
    70000.00,
    2,
    5,
    true,
    (select id from users where email = 'petr@example.com'),
    current_timestamp,
    current_timestamp;

insert into vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
select
    'вакансия 2 (маркетинг)',
    'описание вакансии 2',
    (select id from categories where name = 'маркетинг'),
    80000.00,
    3,
    6,
    true,
    (select id from users where email = 'petr@example.com'),
    current_timestamp,
    current_timestamp;

insert into contact_types (type) values
                                     ('email'),
                                     ('phone');

insert into responded_applicants (resume_id, vacancy_id, confirmation)
select
    (select id from resumes where name = 'резюме ивана (it)'),
    (select id from vacancies where name = 'вакансия 1 (it)'),
    true;

insert into education_info (resume_id, institution, program, start_date, end_date, degree)
select
    (select id from resumes where name = 'резюме ивана (it)'),
    'университет 1',
    'программа 1',
    '2010-09-01',
    '2014-06-30',
    'бакалавр';

insert into work_experience_info (resume_id, years, company_name, position, responsibilities)
select
    (select id from resumes where name = 'резюме ивана (it)'),
    5,
    'компания 1',
    'должность 1',
    'обязанности 1';

insert into contacts_info (type_id, resume_id, value_)
select
    (select id from contact_types where type = 'email'),
    (select id from resumes where name = 'резюме ивана (it)'),
    'ivan@example.com';