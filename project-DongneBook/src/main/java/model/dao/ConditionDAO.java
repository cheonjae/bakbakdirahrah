package model.dao;

import java.sql.SQLException;

import model.Condition;

public class ConditionDAO {
private JDBCUtil jdbcUtil = null;
	
	public ConditionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public int insert(Condition condition) throws SQLException {
		String sql = "INSERT INTO CONDITION VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {condition.getBookId(), condition.getPageDiscoloration(), 
						condition.getCoverDamage(), condition.getPageDamage(), condition.getWriting()};			
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
}
