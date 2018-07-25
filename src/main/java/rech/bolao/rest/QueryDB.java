package rech.bolao.rest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import rech.bolao.bean.User;
import rech.bolao.dao.UserDao;

@Path("/queryDB/{op}/{tableName}")
public class QueryDB {

	@GET
	@Produces(value = "text/html")
	public String queryDB(@PathParam("op") String operation, @PathParam("tableName") String tableName) {
		StringBuffer json = new StringBuffer("");
		if (operation.equals("LIST_ALL")) {
			json.append("{ ");
			if (tableName.equals("USERS")) {
				ArrayList<User> list = null;
				try {
					list = UserDao.getInstance().listAll();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (User user : list) {
					json.append(user.toJson().toString());
					json.append(",");
				}
			}
			json.append("}");
			return json.toString();
		}

		return "{'ping': 'pong' }";
	}
}