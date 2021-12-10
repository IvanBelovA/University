INSERT INTO groups(group_name) VALUES('AA-11');
INSERT INTO groups(group_name) VALUES('BB-22');
INSERT INTO students(first_name, last_name, group_id) VALUES ('Ivan', 'Petrov', 1);
INSERT INTO students(first_name, last_name, group_id) VALUES ('Petr', 'Ivanov', 1);
INSERT INTO courses(course_name) VALUES ('math');
INSERT INTO courses(course_name) VALUES ('biology');
INSERT INTO students_courses(student_id, course_id) VALUES(1, 1);
INSERT INTO students_courses(student_id, course_id) VALUES(1, 2);
INSERT INTO students_courses(student_id, course_id) VALUES(2, 1);
INSERT INTO teachers(name, last_name) VALUES('Ivan', 'Petrov');
INSERT INTO teachers(name, last_name) VALUES('Petr', 'Ivanov');
INSERT INTO teachers_courses(teacher_id, course_id) VALUES(1, 1);
INSERT INTO teachers_courses(teacher_id, course_id) VALUES(1, 2);
INSERT INTO teachers_courses(teacher_id, course_id) VALUES(2, 1);
INSERT INTO class_room(number, capacity) VALUES(1, 10);
INSERT INTO class_room(number, capacity) VALUES(2, 20);
INSERT INTO time_lesson(times) VALUES('2020-12-12 10:00:00');
INSERT INTO lessons(number) VALUES(1);
INSERT INTO lessons(number) VALUES(2);
INSERT INTO shedule(lesson_id, group_id, course_id, teacher_id, classroom_id) VALUES(1, 1, 1, 1, 1);
INSERT INTO shedule(lesson_id, group_id, course_id, teacher_id, classroom_id) VALUES(2, 2, 2, 2, 2);