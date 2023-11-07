package test.service;

import test.model.Classroom;
import test.service.iservice.IService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomService implements IService<Classroom> {
    private Connection connection = ConnectToMySql. getConnection();
    @Override
    public void add(Classroom classroom) {
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Classroom findById(int id) {
        return null;
    }

    @Override
    public void edit(Classroom classroom) {

    }

    @Override
    public List<Classroom> findAll() {
        String sql = "SELECT * FROM Classroom;";
        List<Classroom> result = new ArrayList<Classroom>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                result.add(new Classroom(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Classroom> findByName(String name) {
        return null;
    }
}
