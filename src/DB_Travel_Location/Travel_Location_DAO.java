package DB_Travel_Location;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

public class Travel_Location_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	public static Travel_Location_VO getLocation(String TL_NUM) {
		Travel_Location_VO vo = getSession().selectOne("location", TL_NUM);
		return vo;
	}

}