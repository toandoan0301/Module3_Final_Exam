CREATE DATABASE studentManager;
USE studentManager;

CREATE TABLE classroom(
    id int primary key auto_increment,
    name varchar(50)
);
CREATE TABLE student(
    id int primary key auto_increment,
    name varchar(50) not null,
    dateOfBirth date,
    address varchar(255),
    phone varchar(11),
    email varchar(255),
    classroomId int,
    foreign key (classroomId) references classroom(id)
);

INSERT INTO classroom (name) values ('C0723G1'), ('C0823H1'),('C0923H1'),('C1023I1');

INSERT INTO student (name,dateOfBirth,address,phone,email,classroomId)
VALUES ('Doan Toan','1998-01-03','Ha Noi','0987654321','toan@gmai.com',1),
       ('Doan Linh','1998-01-03','Ha Noi','0987654331','linh@gmai.com',1),
       ('Duong Son','1995-01-03','Ha Noi','0987654321','Son@gmai.com',2);

SELECT s.*, c.name FROM student s JOIN classroom c ON s.classroomId = c.id;
DELETE FROM student where id= 4;