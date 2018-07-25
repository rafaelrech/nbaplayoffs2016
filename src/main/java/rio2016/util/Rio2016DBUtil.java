package rio2016.util;

import java.sql.Connection;
import java.util.Calendar;

import rech.bolao.dao.CommonDao;
import rech.bolao.util.BaseDBUtil;
import rech.bolao.util.GenderEnum;
import rio2016.bean.Rio2016Competitor;
import rio2016.bean.Rio2016Participation;
import rio2016.bean.Rio2016Sport;
import rio2016.bean.Rio2016User;
import rio2016.dao.Rio2016CompetitorDao;
import rio2016.dao.Rio2016MedalBetDao;
import rio2016.dao.Rio2016ParticipationDao;
import rio2016.dao.Rio2016SportDao;
import rio2016.dao.Rio2016UserDao;

public class Rio2016DBUtil {

	public static final String USER_BEAN = "RIO2016-USER";
	public static final String SPORT_BEAN = "RIO2016-SPORT";
	public static final String COMPETITOR_BEAN = "RIO2016-COMPETITOR";
	public static final String PARTICIPATION_BEAN = "RIO2016-PARTICIPATION";
	public static final String MEDAL_BET_BEAN = "RIO2016-MEDAL-BET";

	public static final String[] beans = { USER_BEAN, SPORT_BEAN, COMPETITOR_BEAN, PARTICIPATION_BEAN, MEDAL_BET_BEAN };

	public static Connection getConnection() {
		return BaseDBUtil.getConnection();
	}

	public static CommonDao<?> getDaoFromBean(String bean) {
		if (bean.equals(USER_BEAN)) {
			return Rio2016UserDao.getInstance();
		}
		if (bean.equals(SPORT_BEAN)) {
			return Rio2016SportDao.getInstance();
		}
		if (bean.equals(COMPETITOR_BEAN)) {
			return Rio2016CompetitorDao.getInstance();
		}
		if (bean.equals(PARTICIPATION_BEAN)) {
			return Rio2016ParticipationDao.getInstance();
		}
		if (bean.equals(MEDAL_BET_BEAN)) {
			return Rio2016MedalBetDao.getInstance();
		}
		return null;
	}

	public static void createTables() {
		for (String bean : beans) {
			getDaoFromBean(bean).createTable();
		}
	}

	public static void dropTables() {
		for (String bean : beans) {
			getDaoFromBean(bean).dropTable();
		}
	}

	public static void createTable(String bean) {
	}

	public static void dropTable(String bean) {
		getDaoFromBean(bean).dropTable();
	}

	public static void loadUsers() {
		Rio2016UserDao.getInstance().insert(new Rio2016User(1, 0, 0, 0, 0));
		Rio2016UserDao.getInstance().insert(new Rio2016User(2, 0, 0, 0, 0));
	}

	public static void loadSports() {
		Calendar cs = Calendar.getInstance();
		Calendar ce = Calendar.getInstance();

		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(110, "ATLETISMO - 10.000M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 13);
		ce.set(2016, 7, 13);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(111, "ATLETISMO - 10.000M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(112, "ATLETISMO - 100M COM BARREIRAS FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(113, "ATLETISMO - 100M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 13);
		ce.set(2016, 7, 13);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(114, "ATLETISMO - 100M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(115, "ATLETISMO - 110M COM BARREIRAS MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(116, "ATLETISMO - 1500M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(117, "ATLETISMO - 1500M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(118, "ATLETISMO - 200M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(119, "ATLETISMO - 200M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 13);
		ce.set(2016, 7, 13);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(120, "ATLETISMO - 3000M COM OBSTÁCULOS FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(121, "ATLETISMO - 3000M COM OBSTÁCULOS MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(122, "ATLETISMO - 400M COM BARREIRAS MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(123, "ATLETISMO - 400M COM BARREIRAS FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 13);
		ce.set(2016, 7, 13);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(124, "ATLETISMO - 400M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(125, "ATLETISMO - 400M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 18);
		ce.set(2016, 7, 18);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(126, "ATLETISMO - 4X100M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 18);
		ce.set(2016, 7, 18);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(127, "ATLETISMO - 4X100M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 19);
		ce.set(2016, 7, 19);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(128, "ATLETISMO - 4X400M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 19);
		ce.set(2016, 7, 19);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(129, "ATLETISMO - 4X400M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(130, "ATLETISMO - 5.000M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 17);
		ce.set(2016, 7, 17);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(131, "ATLETISMO - 5.000M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(132, "ATLETISMO - 800M MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 17);
		ce.set(2016, 7, 17);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(133, "ATLETISMO - 800M FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 17);
		ce.set(2016, 7, 17);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(134, "ATLETISMO - DECATLO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(135, "ATLETISMO - HEPTATLO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(136, "ATLETISMO - LANÇAMENTO DE DARDO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 17);
		ce.set(2016, 7, 17);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(137, "ATLETISMO - LANÇAMENTO DE DARDO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(138, "ATLETISMO - LANÇAMENTO DE DISCO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(139, "ATLETISMO - LANÇAMENTO DE DISCO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(140, "ATLETISMO - LANÇAMENTO DE MARTELO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 17);
		ce.set(2016, 7, 17);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(141, "ATLETISMO - LANÇAMENTO DE MARTELO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(142, "ATLETISMO - LANÇAMENTO DE PESO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 18);
		ce.set(2016, 7, 18);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(143, "ATLETISMO - LANÇAMENTO DE PESO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 14);
		ce.set(2016, 7, 14);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(144, "ATLETISMO - MARATONA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 20);
		ce.set(2016, 7, 20);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(145, "ATLETISMO - MARATONA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 19);
		ce.set(2016, 7, 19);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(146, "ATLETISMO - MARCHA ATLÉTICA 20KM FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(147, "ATLETISMO - MARCHA ATLÉTICA 20KM MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 19);
		ce.set(2016, 7, 19);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(148, "ATLETISMO - MARCHA ATLÉTICA 50KM MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 13);
		ce.set(2016, 7, 13);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(149, "ATLETISMO - SALTO COM VARA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(150, "ATLETISMO - SALTO COM VARA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 14);
		ce.set(2016, 7, 14);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(151, "ATLETISMO - SALTO EM ALTURA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 18);
		ce.set(2016, 7, 18);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(152, "ATLETISMO - SALTO EM ALTURA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(153, "ATLETISMO - SALTO EM DISTÂNCIA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 16);
		ce.set(2016, 7, 16);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(154, "ATLETISMO - SALTO EM DISTÂNCIA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 13);
		ce.set(2016, 7, 13);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(155, "ATLETISMO - SALTO TRIPLO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 15);
		ce.set(2016, 7, 15);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(156, "ATLETISMO - SALTO TRIPLO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(159, "BASQUETE FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(177, "BASQUETE MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 3);
		ce.set(2016, 7, 3);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(199, "FUTEBOL FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 4);
		ce.set(2016, 7, 4);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(101, "FUTEBOL MASCULINO", GenderEnum.MALE.getId(), cs, ce,
				SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(209, "HANDEBOL FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(210, "HANDEBOL MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(211, "HÓQUEI SOBRE GRAMA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(212, "HÓQUEI SOBRE GRAMA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 11);
		ce.set(2016, 7, 11);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(213, "JUDO 100KG MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(214, "JUDO 48KG FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(215, "JUDO 52KG FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 8);
		ce.set(2016, 7, 8);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(216, "JUDO 57KG FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(217, "JUDO 60KG MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(218, "JUDO 63KG FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(219, "JUDO 66KG MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(220, "JUDO 70KG FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 8);
		ce.set(2016, 7, 8);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(221, "JUDO 73KG MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 11);
		ce.set(2016, 7, 11);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(222, "JUDO 78KG FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(223, "JUDO 81KG MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(224, "JUDO 90KG MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(225, "JUDO ACIMA 100KG MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(226, "JUDO ACIMA 78KG FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(231, "NATAÇÃO 100M BORBOLETA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 11);
		ce.set(2016, 7, 11);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(232, "NATAÇÃO 100M BORBOLETA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(233, "NATAÇÃO 100M COSTAS FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(234, "NATAÇÃO 100M COSTAS MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(235, "NATAÇÃO 100M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(236, "NATAÇÃO 100M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 8);
		ce.set(2016, 7, 8);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(241, "NATAÇÃO 100M PEITO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(242, "NATAÇÃO 100M PEITO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(244, "NATAÇÃO 1500M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(245, "NATAÇÃO 200M BORBOLETA FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 8);
		ce.set(2016, 7, 8);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(246, "NATAÇÃO 200M BORBOLETA MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 11);
		ce.set(2016, 7, 11);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(247, "NATAÇÃO 200M COSTAS FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(248, "NATAÇÃO 200M COSTAS MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 8);
		ce.set(2016, 7, 8);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(249, "NATAÇÃO 200M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(250, "NATAÇÃO 200M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 8);
		ce.set(2016, 7, 8);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(251, "NATAÇÃO 200M MEDLEY FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(252, "NATAÇÃO 200M MEDLEY MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(256, "NATAÇÃO 200M PEITO FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(257, "NATAÇÃO 200M PEITO MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(258, "NATAÇÃO 400M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(259, "NATAÇÃO 400M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(263, "NATAÇÃO 400M MEDLEY FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(264, "NATAÇÃO 400M MEDLEY MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(265, "NATAÇÃO 4X100M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(266, "NATAÇÃO 4X100M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(267, "NATAÇÃO 4X100M MEDLEY FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(268, "NATAÇÃO 4X100M MEDLEY MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 10);
		ce.set(2016, 7, 10);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(269, "NATAÇÃO 4X200M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(270, "NATAÇÃO 4X200M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(21, "NATAÇÃO 50M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 11);
		ce.set(2016, 7, 11);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(22, "NATAÇÃO 50M LIVRE MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 11);
		ce.set(2016, 7, 11);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(23, "NATAÇÃO 800M LIVRE FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(24, "RUGBY DE 7 FEMININO", GenderEnum.FEMALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 9);
		ce.set(2016, 7, 9);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(25, "RUGBY DE 7 MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(26, "TENIS DE MESA EQUIPES FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 12);
		ce.set(2016, 7, 12);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(27, "TENIS DE MESA EQUIPES MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(28, "TENIS DE MESA FEMININO", GenderEnum.FEMALE.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(29, "TENIS DE MESA MASCULINO", GenderEnum.MALE.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(30, "TENIS DUPLAS FEMININO", GenderEnum.FEMALE.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(31, "TENIS DUPLAS MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(32, "TENIS DUPLAS MISTAS MISTO", GenderEnum.MIXED.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(33, "TENIS FEMININO", GenderEnum.FEMALE.getId(), cs, ce,
				SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(34, "TENIS MASCULINO", GenderEnum.MALE.getId(), cs, ce,
				SportTypeEnum.TEAM.getId(), 0, 1, 1, 2));
		cs.set(2016, 7, 7);
		ce.set(2016, 7, 7);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(2, "TIRO COM ARCO EQUIPES FEMININO",
				GenderEnum.FEMALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(1, "TIRO COM ARCO EQUIPES MASCULINO",
				GenderEnum.MALE.getId(), cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 5);
		ce.set(2016, 7, 5);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(4, "TIRO COM ARCO FEMININO", GenderEnum.FEMALE.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 5);
		ce.set(2016, 7, 5);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(3, "TIRO COM ARCO MASCULINO", GenderEnum.MALE.getId(), cs,
				ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(35, "VOLEI DE PRAIA FEMININO", GenderEnum.FEMALE.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(36, "VOLEI DE PRAIA MASCULINO", GenderEnum.MALE.getId(),
				cs, ce, SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(37, "VOLEI FEMININO", GenderEnum.FEMALE.getId(), cs, ce,
				SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));
		cs.set(2016, 7, 6);
		ce.set(2016, 7, 6);
		Rio2016SportDao.getInstance().insert(new Rio2016Sport(38, "VOLEI MASCULINO", GenderEnum.MALE.getId(), cs, ce,
				SportTypeEnum.TEAM.getId(), 0, 1, 1, 1));

	}

	public static void loadCompetitors() {
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(1001, "Lisa Unruh", "GER", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(1002, "Florian Floto", "GER", ""));

		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(1, "Brasil", "BRA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(2, "Estados Unidos", "USA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(3, "Austrália", "AUS", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(4, "Alemanha", "GER", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(5, "Grã-Bretanha", "GBR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(6, "Rússia", "RUS", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(7, "China", "CHN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(8, "França", "FRA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(9, "Japão", "JPN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(10, "Argentina", "ARG", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(11, "Canadá", "CAN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(12, "Nova Zelândia", "NZL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(13, "Espanha", "ESP", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(14, "Holanda", "NED", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(15, "Itália", "ITA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(16, "Coreia do Sul", "KOR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(17, "Egito", "EGY", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(18, "Polônia", "POL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(19, "África do Sul", "RSA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(20, "Sérvia", "SRB", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(21, "Suécia", "SWE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(22, "Ucrânia", "UKR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(23, "Dinamarca", "DEN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(24, "Bielorrússia", "BLR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(25, "Colômbia", "COL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(26, "Índia", "IND", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(27, "México", "MEX", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(28, "Fiji", "FIJ", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(29, "Bélgica", "BEL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(30, "Nigéria", "NGR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(31, "Hungria", "HUN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(32, "Suíça", "SUI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(33, "Croácia", "CRO", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(34, "Cuba", "CUB", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(35, "Irlanda", "IRL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(36, "Portugal", "POR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(37, "Lituânia", "LTU", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(38, "Cazaquistão", "KAZ", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(39, "Noruega", "NOR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(40, "Grécia", "GRE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(41, "Azerbaijão", "AZE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(42, "Argélia", "ALG", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(43, "República Tcheca", "CZE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(44, "Irã", "IRI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(45, "Quênia", "KEN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(46, "Jamaica", "JAM", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(47, "Coreia do Norte", "PRK", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(48, "Venezuela", "VEN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(49, "Zimbábue", "ZIM", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(50, "Romênia", "ROU", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(51, "Turquia", "TUR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(52, "Áustria", "AUT", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(53, "Qatar", "QAT", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(54, "Trinidad e Tobago", "TRI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(55, "Angola", "ANG", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(56, "Honduras", "HON", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(57, "Iraque", "IRQ", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(58, "Bulgária", "BUL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(59, "Taipei", "TPE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(60, "Eslováquia", "SVK", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(61, "Tailândia", "THA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(62, "Eslovênia", "SLO", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(63, "Montenegro", "MNE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(64, "Senegal", "SEN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(65, "Georgia", "GEO", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(66, "Israel", "ISR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(67, "Chile", "CHI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(68, "Uzbequistão", "UZB", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(69, "Finlândia", "FIN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(70, "Armênia", "ARM", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(71, "Estônia", "EST", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(72, "Mongólia", "MGL", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(73, "Cingapura", "SIN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(74, "Tunísia", "TUN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(75, "Indonésia", "INA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(76, "Marrocos", "MAR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(77, "Moldávia", "MDA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(78, "Bahamas", "BAH", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(79, "Botsuana", "BOT", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(80, "Guatemala", "GUA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(81, "Kuwait", "KUW", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(82, "Malásia", "MAS", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(83, "São Cristóvão e Nevis", "SKN", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(84, "Costa do Marfim", "CIV", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(85, "Equador", "ECU", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(86, "Uruguai", "URU", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(87, "Vietnã", "VIE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(88, "Ilhas Cook", "COK", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(89, "Chipre", "CYP", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(90, "República Dominicana", "DOM", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(91, "Emirados Árabes Unidos", "UAE", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(92, "Aruba", "ARU", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(93, "Hong Kong", "HKG", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(94, "Letônia", "LAT", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(95, "Peru", "PER", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(96, "Porto Rico", "PUR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(97, "San Marino", "SMR", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(98, "Congo", "CGO", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(99, "El Salvador", "ESA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(100, "Namíbia", "NAM", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(101, "Filipinas", "PHI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(102, "Seychelles", "SEY", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(103, "Bermuda", "BER", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(104, "Rep. Dem. Congo", "COD", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(105, "Cabo Verde", "CPV", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(106, "Costa Rica", "CRC", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(107, "Eritreia", "ERI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(108, "Etiópia", "ETH", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(109, "Gabão", "GAB", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(110, "Ilhas Virgens", "ISV", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(111, "Quirquistão", "KGZ", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(112, "Arábia Saudita", "KSA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(113, "Líbia", "LBA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(114, "Luxemburgo", "LUX", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(115, "Mali", "MLI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(116, "Mauricio", "MRI", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(117, "Mianmar", "MYA", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(118, "Níger", "NIG", ""));
		Rio2016CompetitorDao.getInstance().insert(new Rio2016Competitor(119, "Ruanda", "RWA", ""));
	}

	public static void loadParticipations() {
		// ARCHERY
		// MASC - EQUIPES
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(55, 1)); // aus
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(1, 1)); // bra
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(70, 1)); // chn
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(29, 1)); // esp
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(92, 1)); // fra
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(82, 1)); // indonesia
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(24, 1)); // italia
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(79, 1)); // kor
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(72, 1)); // malasia
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(103, 1)); // nederlands
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(88, 1)); // taipei
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(4, 1)); // usas
		// FEM - EQUIPES
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(1, 2)); // bra
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(70, 2)); // chn
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(73, 2)); // colom
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(35, 2)); // georgia
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(25, 2)); // india
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(24, 2)); // italia
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(3, 2)); // japao
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(79, 2)); // kor
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(98, 2)); // mexico
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(10, 2)); // russia
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(88, 2)); // taipei
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(67, 2)); // ukrania
		// MASC - IND
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(1001, 3)); //
		// FEM - IND
		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(1002, 4)); //

		// FOOTBALL - MASC
		//Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(1, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(105, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(7, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(93, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(3, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(13, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(38, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(47, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(52, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(56, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(73, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(79, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(84, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(98, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(99, 101));
//		Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(112, 101));

	}

}
