package controller;

import db.DBConnection;
import model.Admin;
import model.Member;
import model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginController implements LoginService {
    private static LoginController instance;

    public static LoginController getInstance() {
        return instance == null ? instance = new LoginController() : instance;
    }

    @Override
    public List<Member> getAllMember() {
        List<Member> allMember = new ArrayList<>();

        try {
            ResultSet resultSet = DBConnection
                    .getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery("SELECT * FROM Member");
            while (resultSet.next()) {
                allMember.add(new Member(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getString(5),
                        resultSet.getString(6)

                ));
            }
            return allMember;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> allAdmin = new ArrayList<>();

        try {
            ResultSet resultSet = DBConnection
                    .getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery("SELECT * FROM admin");
            while (resultSet.next()) {
                allAdmin.add(new Admin(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Role.Admin,
                        resultSet.getString(6),
                        resultSet.getString(7)

                ));
            }
            return allAdmin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
