package test.controller;

import test.model.Classroom;
import test.model.Student;
import test.service.ClassroomService;
import test.service.StudentService;
import test.service.iservice.IService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private final IService<Student> studentService = new StudentService();
    private final IService<Classroom> classService = new ClassroomService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "showAll";
        }
        switch (action) {
            case "showAll":
                showAll(request, response);
                break;
            case "add":
                showFormAdd(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            case "search":{
                showStudentByName(request, response);
            }
            default:
                showAll(request, response);
        }
    }

    private void showStudentByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Student> list = studentService.findByName(name);
        request.setAttribute("students", list);
        request.getRequestDispatcher("showAll.jsp").forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Classroom> classes = classService.findAll();
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        request.setAttribute("classrooms", classes);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classroom> classes = classService.findAll();
        request.setAttribute("classrooms", classes);
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.findAll();
        request.setAttribute("students", list);
        request.getRequestDispatcher("showAll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addStudent(request, response);
                break;
            case "edit":
                doEdit(request, response);
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        LocalDate date = LocalDate.parse(request.getParameter("dateOfBirth"));
        int classroomId = Integer.parseInt(request.getParameter("classroomId"));
        Classroom classroom = new Classroom(classroomId, "");
        Student student = new Student(id, name, date, address, phone, email, classroom);
        studentService.edit(student);
        response.sendRedirect("/student");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        studentService.delete(studentId);
        response.sendRedirect("/student");
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        LocalDate date = LocalDate.parse(request.getParameter("dateOfBirth"));
        int id = Integer.parseInt(request.getParameter("classroomId"));
        Classroom classroom = new Classroom(id, "");
        Student student = new Student(name, date, address, phone, email, classroom);
        studentService.add(student);
        response.sendRedirect("/student");
    }
}