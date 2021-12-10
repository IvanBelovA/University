DROP TABLE if EXISTS courses CASCADE;
DROP TABLE if EXISTS students CASCADE;
DROP TABLE if EXISTS teachers CASCADE;
DROP TABLE if EXISTS teachers_courses CASCADE;
DROP TABLE if EXISTS class_room CASCADE;
DROP TABLE if EXISTS lessons CASCADE;
DROP TABLE if EXISTS time_lesson CASCADE;
DROP TABLE if EXISTS groups CASCADE;
DROP TABLE if EXISTS students_courses CASCADE;
DROP TABLE if EXISTS shedule CASCADE;

CREATE TABLE courses
(
    id SERIAL PRIMARY KEY,
    course_name varchar(250)
);

CREATE TABLE groups
(
    id SERIAL PRIMARY KEY,
    group_name varchar(250)
--    FOREIGN KEY id 
);

CREATE TABLE students
(
    id SERIAL PRIMARY KEY ,
    first_name varchar(250),
    last_name varchar(250),
    group_id integer,
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

CREATE TABLE students_courses
(
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    UNIQUE (student_id, course_id)
);

CREATE TABLE teachers
(
    id SERIAL PRIMARY KEY,
    name varchar(250),
    last_name varchar(250)
);

CREATE TABLE teachers_courses
(
    teacher_id integer NOT NULL,
    course_id integer NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    UNIQUE (teacher_id, course_id)
);

CREATE TABLE class_room
(
    id SERIAL PRIMARY KEY,
    number integer,
    capacity integer
);

CREATE TABLE time_lesson
(
    id SERIAL PRIMARY KEY,
    times TIMESTAMP
);

CREATE TABLE lessons
(
    id serial PRIMARY KEY,
    number integer,
    time_id integer REFERENCES time_lesson(id)
);

CREATE TABLE shedule
(
    id SERIAL PRIMARY KEY,
    lesson_id integer REFERENCES lessons(id) ON DELETE CASCADE,
    group_id integer REFERENCES groups(id) ON DELETE CASCADE,
    course_id integer REFERENCES courses(id) ON DELETE CASCADE,
    teacher_id integer REFERENCES teachers(id) ON DELETE CASCADE,
    classroom_id integer REFERENCES class_room(id) ON DELETE CASCADE
);

--INSERT INTO students(first_name, last_name) VALUES ('Ivanov', 'Petr');