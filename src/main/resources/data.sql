INSERT INTO CATEGORY (name)
VALUES ('obowiązki domowe'), ('praca');

INSERT INTO TASK_DURATION (start_time, end_time, duration)
VALUES ('2023-05-10 08:00:00', '2023-05-10 12:00:00', '04:00:00');

INSERT INTO TASK (title, description, due_date, done, category_id, duration_id)
VALUES ('zrobić prezentację', 'opisać działanie aplikacji', '2023-05-12', 1, 2, 1),
       ('zrobić testy', 'przetestować nową funkcjonalność', '2023-05-25', 0, 2, null),
       ('posprzątać', 'posprzątać mieszkanie', '2023-05-16', 0, 1, null);