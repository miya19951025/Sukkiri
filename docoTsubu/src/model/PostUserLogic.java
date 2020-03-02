package model;

import dao.UserDAO;

public class PostUserLogic {
	public void exexute(User user) {
		UserDAO dao = new UserDAO();
		dao.create(user);
	}
}
