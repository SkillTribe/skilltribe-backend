INSERT INTO skill (id, name, description, skill_level, created_at, updated_at, created_by, is_deleted)
    VALUES (1, 'skill1', 'pretty useful skill', 'BEGINNER', '2024-02-01 12:11:10', '2024-02-01 12:11:10', 'SEEDER', false);
INSERT INTO skill (id, name, description, skill_level, created_at, updated_at, created_by, is_deleted)
    VALUES (2, 'skill2', 'another pretty useful skill', 'BEGINNER', '2024-02-02 12:11:10', '2024-02-02 12:11:10', 'SEEDER', false);
INSERT INTO skill (id, name, description, skill_level, created_at, updated_at, created_by, is_deleted)
    VALUES (3, 'skill3', 'useless skill', 'BEGINNER', '2024-02-03 12:11:10', '2024-02-03 12:11:10', 'SEEDER', false);

INSERT INTO guide (id, name, description, created_at, updated_at, created_by, is_deleted)
    VALUES (1, 'guide1', 'usefull guide', '2024-02-02 12:11:10', '2024-02-02 12:11:10', 'SEEDER', false);
INSERT INTO guide (id, name, description, created_at, updated_at, created_by, is_deleted)
    VALUES (2, 'guide2', 'usefull guide', '2024-02-02 12:11:10', '2024-02-02 12:11:10', 'SEEDER', false);
INSERT INTO guide_plan (id, guide_id) VALUES (1, 1);
INSERT INTO guide_plan (id, guide_id) VALUES (2, 2);

INSERT INTO guide_task (id, name, description, task_goal, repetition, guide_plan_id) VALUES (1, 'test task', 'this task test the guide tas seed', 'TUTORIAL', 3, 1);
INSERT INTO guide_task (id, name, description, task_goal, repetition, guide_plan_id) VALUES (2, 'test task2', 'this task test the guide tas seed', 'TUTORIAL', 3, 1);
INSERT INTO guide_task (id, name, description, task_goal, repetition, guide_plan_id) VALUES (3, 'test task3', 'this task test the guide tas seed', 'TUTORIAL', 3, 1);
INSERT INTO guide_task (id, name, description, task_goal, repetition, guide_plan_id) VALUES (4, 'test task4', 'this task test the guide tas seed', 'TUTORIAL', 3, 2);

SELECT setval('skill_id_seq', (SELECT MAX(id) FROM skill));
SELECT setval('guide_id_seq', (SELECT MAX(id) FROM guide));
SELECT setval('guide_task_id_seq', (SELECT MAX(id) FROM guide_task));