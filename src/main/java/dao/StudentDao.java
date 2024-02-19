package dao;

import database.Database;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDao {
    public int registerEmployee(Student student) throws ClassNotFoundException {
        Database database = new Database();
        int result = 0;

        String INSERT_USERS_SQL = "INSERT INTO test" +
                "  (name, email, phone, password) VALUES " +
                " (?, ?, ?, ?);";

        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getPassword());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean loginEmployee(Student student) throws ClassNotFoundException {

        Database database = new Database();

        boolean status = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from test where email = ? and password = ? ");
            preparedStatement.setString(1, student.getEmail());
            preparedStatement.setString(2, student.getPassword());

            System.out.println(preparedStatement);
            ResultSet result = preparedStatement.executeQuery();
            status = result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Map<String, Object>> users() throws ClassNotFoundException {
        Database database = new Database();


        String GET_USERS_SQL = "SELECT * FROM test";

        List<Map<String, Object>> dataList = null;
        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_SQL);


            ResultSet resultSet = preparedStatement.executeQuery();
            dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", resultSet.getInt("id"));
                dataMap.put("name", resultSet.getString("name"));
                dataMap.put("email", resultSet.getString("email"));
                dataMap.put("phone", resultSet.getString("phone"));
                dataMap.put("password", resultSet.getString("password"));

                dataList.add(dataMap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public int deleteStudent(int userId) {
        Database database = new Database();
        int result = 0;
        try {
            int id = userId;
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM test " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public Student editStudent(int userId) {
        Database database = new Database();
        Student student = new Student();
        try {
            int id = userId;
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from test where id =?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                student.setName(name);
                student.setEmail(email);
                student.setPhone(phone);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
