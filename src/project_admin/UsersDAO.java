package project_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;





public class UsersDAO {
	private static SqlSession ss;
	
	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized  static SqlSession getSession() {
		if(ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	
	
	public static List<UserVO> getList(){
		List<UserVO> list = null;
		// selectList() : 결과가 하나이상일때 
		// selectOne()  : 반드시 결과가 하나일때
		// 파라미터가 있는 메서드와 파라미터가 없는메서드로 나눈다.
		// 파라미터가 있는 메서드 : selectList("mepper의 id",파라미터);
		// 파라미터가 없는 메서드  : selectList("mepper의 id")
		list = getSession().selectList("membersList");
		return list;
	}
	
	public static UserVO getOneId(String m_Id) {
		UserVO vo = getSession().selectOne("memberId", m_Id);
		return vo;
	}
	public static List<UserVO> getOneName(String m_name) {
		List<UserVO> list =null;
		list = getSession().selectList("memberName", m_name);
		return list;
	}
	public static List<UserVO> getOneEmail(String m_Email) {
		List<UserVO> list =null;
		list = getSession().selectList("memberEmail", m_Email);
		return list;
	}
	
	public static int getDelete(UserVO vo) {
		int result = getSession().delete("memberDel", vo);
		ss.commit();
		return result;
	}
	
	
	public static boolean getIdChk(String m_Id) {
		boolean result = false;
		UserVO vo = getSession().selectOne("idChk", m_Id);
		if(vo == null) {
			result = true;
		}
		return result;
	}
	
	
	public static int getInsert(UserVO vo) {
        int result = getSession().insert("userIns", vo);
        ss.commit();
        return result;
    }
	
}












