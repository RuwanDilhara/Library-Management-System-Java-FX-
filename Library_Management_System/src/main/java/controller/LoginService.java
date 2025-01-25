package controller;

import model.Admin;
import model.Member;

import java.util.List;

public interface LoginService {
    List<Member> getAllMember();
    List<Admin> getAllAdmin();
}
