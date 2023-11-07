package test.service;

import test.model.Classroom;
import test.model.Student;
import test.service.iservice.IService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IService<Student> {
    private Connection connection = ConnectToMySql.getConnection();

    @Override
    public void add(Student student) {
        String sql = "INSERT INTO student (name, dateOfBirth, address, phone, email, classroomId) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getEmail());
            statement.setInt(6, student.getClassroom().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(Student student) {
        String sql = "UPDATE student SET name = ?, dateOfBirth = ?, address = ?, phone = ?, email = ?, classroomId = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getEmail());
            statement.setInt(6, student.getClassroom().getId());
            statement.setInt(7, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<Student>();
        String sql = "SELECT s.*, c.name FROM student s JOIN classroom c ON s.classroomId = c.id;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student student = getStudent(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<Student>();
        String sql = "SELECT s.*, c.name FROM student s JOIN classroom c ON s.classroomId = c.id and s.name like ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%"+name+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student student = getStudent(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT s.*, c.name FROM student s JOIN classroom c ON s.classroomId = c.id and s.id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
               return getStudent(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Student getStudent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("s.name");
        LocalDate date = rs.getDate("dateOfBirth").toLocalDate();
        String address = rs.getString("address");
        String phone= rs.getString("phone");
        String email = rs.getString("email");
        int classroomId= rs.getInt("classroomId");
        String className = rs.getString("c.name");
        Classroom classroom = new Classroom(classroomId,className);
        return new Student(id,name,date,address,phone,email,classroom);

    }
}
