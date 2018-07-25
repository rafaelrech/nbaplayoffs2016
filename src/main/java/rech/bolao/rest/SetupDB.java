package rech.bolao.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nbaplayoffs2016.util.Nba2016DBUtil;

@Path("/setupDB/{op}/{bean}")
public class SetupDB {

	@GET
	@Produces(value = "text/html")
	public String setupDB(@PathParam("op") String operation, @PathParam("bean") String bean) {
		if (operation.equals("DROP")) {
			// DBUtil.dropTables();
			Nba2016DBUtil.dropTable(bean);
			return "DROP " + bean;
		} else if (operation.equals("INITDB")) {
			// DBUtil.initDatabase();
		} else if (operation.equals("CREATE")) {
			Nba2016DBUtil.createTable(bean);
			return "CREATE " + bean;
		} else if (operation.equals("INSERT")) {
			return Nba2016DBUtil.DML(bean);
		} else if (operation.equals("FULL")) {
			// DBUtil.initDatabase(true);
			// return smoke(bean);
		} else if (operation.equals("LOAD_BRACKETS")) {
			// DBUtil.loadBrackets();
			// return smoke(bean);
		} else if (operation.equals("LOAD_BETS")) {
			// DBUtil.loadBracketBets();
			// return smoke(bean);
		} else if (operation.equals("SMOKE_TEST")) {
			// return smoke(bean);
		}

		return "{'ping': 'pong' }";
	}

}