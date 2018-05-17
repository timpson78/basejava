INSERT INTO public.resume (uuid, full_name) VALUES ('c0cbce88-51aa-4361-b668-dbd89449d2be', 'фулнейм1');
INSERT INTO public.resume (uuid, full_name) VALUES ('7f4b71d8-0f91-407f-b36e-6653ba4dcb19', 'фулнейм2');
INSERT INTO public.resume (uuid, full_name) VALUES ('fc1339a0-8f54-4e28-849e-b0e485e5de26', 'фулнейм3');
INSERT INTO public.resume (uuid, full_name) VALUES ('23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'Сараев Тимофей Георгиевич');


INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20357, 'PHONE', '+7-929-82-86-887', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20358, 'SKYPE', 'timpson78', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20359, 'EMAIL', 'kubkredit@mail.ru', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20360, 'WEBSITE', 'нет', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20361, 'LINKEDIN', 'нет', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20362, 'GITHUB', 'https://github.com/timpson78', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20363, 'STACKOVERFLOW', 'нет', '23e7d2d4-b74d-4252-9b00-ed706eb7f595');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20313, 'PHONE', '+7-918-333-33-333', '7f4b71d8-0f91-407f-b36e-6653ba4dcb19');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20314, 'SKYPE', 'UUID3_SKYPE', '7f4b71d8-0f91-407f-b36e-6653ba4dcb19');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20315, 'EMAIL', 'FullName3@mail.ru', '7f4b71d8-0f91-407f-b36e-6653ba4dcb19');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20316, 'WEBSITE', 'http://www.UUID3.ru', '7f4b71d8-0f91-407f-b36e-6653ba4dcb19');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20309, 'PHONE', '+7-918-111-11-11', 'c0cbce88-51aa-4361-b668-dbd89449d2be');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20310, 'SKYPE', 'UUID1_SKYPE', 'c0cbce88-51aa-4361-b668-dbd89449d2be');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20311, 'EMAIL', 'FullName1@mail.ru', 'c0cbce88-51aa-4361-b668-dbd89449d2be');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20312, 'WEBSITE', 'http://www.UUID1.ru', 'c0cbce88-51aa-4361-b668-dbd89449d2be');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20317, 'PHONE', '+7-918-222-22-22', 'fc1339a0-8f54-4e28-849e-b0e485e5de26');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20318, 'SKYPE', 'UUID2_SKYPE', 'fc1339a0-8f54-4e28-849e-b0e485e5de26');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20319, 'EMAIL', 'FullName2@mail.ru', 'fc1339a0-8f54-4e28-849e-b0e485e5de26');
INSERT INTO public.contact (id, type, value, resume_uuid) VALUES (20320, 'WEBSITE', 'http://www.UUID2.ru', 'fc1339a0-8f54-4e28-849e-b0e485e5de26');

INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9544, 'c0cbce88-51aa-4361-b668-dbd89449d2be', 'OBJECTIVE', 'Objective: To be honerst, i want to find a great job where I can do anything what i want');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9545, 'c0cbce88-51aa-4361-b668-dbd89449d2be', 'PERSONAL', 'Personal information: I realy intelligent and creative person');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9546, 'c0cbce88-51aa-4361-b668-dbd89449d2be', 'ACHIEVEMENT', 'took part in the hard project
implemeted java anywhere
satistfied everybody
');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9547, 'c0cbce88-51aa-4361-b668-dbd89449d2be', 'QUALIFICATIONS', 'really great
best of the best
never give up
');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9568, '23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'OBJECTIVE', 'Начальник отдела ИТ отдела, инженер, системный администратор, специалист');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9569, '23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'PERSONAL', 'Аналитический склад ума, сильная логика, креативность, инициативность');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9570, '23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'ACHIEVEMENT', 'реализация проекта по созданию с нуля распределенной корпоративной сети Банка на базе Cisco Avaya
Участие в проекте по реализации   проекта по виртуализации сети предприятия на базе технологии vSphere
');
INSERT INTO public.section (id, resume_uuid, type, value) VALUES (9571, '23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'QUALIFICATIONS', 'Linux
Adobe Photoshop
MySQL
Cisco
Администрирование сайтов
CentOS
Nginx
CorelDRAW
Apache HTTP Server
3D Max
Информационные технологии
Проектирование ЛВС
Настройка серверов Apache
Администрирование сетевого оборудования
PHP5
Администрирование серверов Linux
Управление проектами
Ведение переговоров
Корпоративная этика
KVM
Docker
Английский язык
');


INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'Центр правительственной связи', 7273, '', 'EXPERIENCE', 'Инженер 1-й категории', '- Обслуживание специальной техники
- Составление документации      ', '2001-06-01 00:00:00.000000', '2003-06-01 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'СИА Интернейшнл Лтд', 7274, 'www.siamed.ru', 'EXPERIENCE', 'Системный администратор', ' Обслуживание всей ИТ инфраструктуры компании:
- администрирование ЛВС - 50 компьютеров
- администрирование серверов и рабочих станций
- администрирование БД, 1Cv7.7
- обслуживание кассового оборудования
- консультирование пользователей     ', '2003-07-07 00:00:00.000000', '2004-06-06 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('c0cbce88-51aa-4361-b668-dbd89449d2be', 'the best work place', 7250, '', 'EXPERIENCE', 'developer', 'coding   ', '2001-01-12 00:00:00.000000', '2003-02-26 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('c0cbce88-51aa-4361-b668-dbd89449d2be', 'the best work place', 7251, '', 'EXPERIENCE', 'developer2', 'coding2   ', '2003-01-12 00:00:00.000000', '2005-02-26 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('c0cbce88-51aa-4361-b668-dbd89449d2be', 'the best work place2', 7252, 'http://the bestplace.ru', 'EXPERIENCE', 'developer', 'coding   ', '2007-01-12 00:00:00.000000', '2010-02-02 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('c0cbce88-51aa-4361-b668-dbd89449d2be', 'The best Institute in the world', 7253, 'http://the bestEducation.ru', 'EDUCATION', 'Engineer', 'coding   ', '1996-01-12 00:00:00.000000', '2001-02-26 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('c0cbce88-51aa-4361-b668-dbd89449d2be', 'The best School in the world', 7254, 'http://the bestSchool.ru', 'EDUCATION', 'developer', 'coding   ', '2001-01-12 00:00:00.000000', '2003-02-26 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'Кубань Кредит, КБ, ООО', 7275, 'www.kubankredit.ru', 'EXPERIENCE', 'Начальник ОСАСТиТ, Зам.начальника ВЦ, специалист информационной безопасности, инженер', ' - Организация работы отдела
- Планирование и проектирование ИТ инфраструктуры банка
- Участие в проектах по созданию ЦОД банка
- Проведение деловых переговоров и переписки с партнерами
- Обслуживание корпоративной сети банка
- Администрирование Web-сервера Apache
- Администрирование корпоративного сайта банка
- Администрирование рабочих станций на базе ОС Windows 2000, XP
- Администрирование оборудования Cisco 18,28 38 series
- Администрирование серверов Linux( named, postfix, httpd,vsftpd, sshd, openssl CA, ngnix, mysql, nagios, freeradius, pppoe, gre, Docker, KVM, XEN,VmVare )
- Администрирование серверов Windows
- Администрирование сетевого оборудования cisco, linksys, dlink
- Программирование на php5, cakephp
- Организация связи с удаленными офисами банка    ', '2004-07-06 00:00:00.000000', '2015-01-01 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'Академия федерального агентства Правительственной связи (ФАПСИ)', 7276, 'academ.msk.rsnet.ru/', 'EDUCATION', 'Инженер АСОИУ', 'диплом с отличием   ', '1996-09-06 00:00:00.000000', '2001-07-06 00:00:00.000000');
INSERT INTO public.organization (resume_uuid, linkname, id, linkurl, type, title_position, description_position, startdate_position, enddate_position) VALUES ('23e7d2d4-b74d-4252-9b00-ed706eb7f595', 'Кубанский государственный университет, Краснодар', 7277, 'www.kubsu.ru', 'EDUCATION', 'Экономист, Финансы и Кредит', '  ', '2001-06-06 00:00:00.000000', '2004-06-06 00:00:00.000000');