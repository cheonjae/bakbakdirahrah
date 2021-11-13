package model.dao;

import java.sql.SQLException;

import model.Condition;

public class ConditionDAO {
private JDBCUtil jdbcUtil = null;
	
	public ConditionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public int insert(Condition condition) throws SQLException {
		String sql = "INSERT INTO CONDITION VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {condition.getBookId(), condition.getPageDiscoloration(), 
						condition.getCoverDamage(), condition.getPageDamage(), condition.getWriting()};			
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
}
