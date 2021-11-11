package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Lab3 {

	public Lab3() {	// 생성자 
		// JDBC 드라이버 로딩 및 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
	}
	
	private static Connection getConnection() {
		String url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";	
		String user = "dbpro";
		String passwd = "dbpro";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
	
	public static int printDeptInfo(String deptName) {
		// deptName 부서에 관한 정보와 그 부서에 속한 사원 수를 계산하여 화면에 출력
		Connection conn = null;
		PreparedStatement pStmt = null;			// PreparedStatment 참조 변수 생성
		ResultSet rs = null;
		String query = "SELECT deptno, manager, COUNT(empno) AS num "
				+ "FROM DEPT191018 JOIN EMP191018 USING(deptno) "
				+ "WHERE dname = ? "
				+ "GROUP BY deptno, manager";
		
		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, deptName);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				int deptNo = rs.getInt("deptno");
				int mgrNo = rs.getInt("manager");
				int numOfEmp = rs.getInt("num"); // 3
				
				System.out.println("부서번호: " + deptNo);
				System.out.println("부서명: " + deptName);
				System.out.println("관리자사번: " + mgrNo);
				System.out.println("사원 수: " + numOfEmp);
				System.out.println();
				
				return deptNo;
			}
			else {
				System.out.println("부서를 찾지 못했습니다.");
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return 0;
	}
	
	public static void printEmployeesInDept(int deptNo) {
		// deptNo 부서에 속한 모든 사원들에 대해 필요한 정보를 검색하여 화면에 출력
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsMetaData = null;
		String query = "SELECT empno AS 사번, ename AS 이름, job AS 직무, TO_CHAR(sal, '9990.99') AS 급여, TO_CHAR(NVL(comm, 0), '9990.99') AS 수당 "
				+ "FROM EMP191018 "
				+ "WHERE deptno = " + deptNo;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			for(int i = 1; i <= columnCount; i++) {
					System.out.print(rsMetaData.getColumnName(i) + "\t");
			}
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
			while(rs.next()) {
				for(int i = 1; i <= columnCount; i++) {
					System.out.print(rs.getObject(rsMetaData.getColumnName(i)) + "\t");  
				}
				System.out.println();
			}
			System.out.println();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (stmt != null) 
				try { 
					stmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
	
	public static void replaceManagerOfDept(int deptNo, int mgrNo, double mgrComm) {
		// deptNo 부서의 관리자사번을 주어진 mgrNo로 변경하고, 그 사원의 수당을 mgrComm값만큼 증가시킴
		Connection conn = null;
		Statement stmt = null;
		int recordCount = 0;
		String query = "UPDATE DEPT191018 "
				+ "SET manager = " + mgrNo
				+ " WHERE deptno = " + deptNo;
		String query2 = "UPDATE EMP191018 "
				+ "SET comm = TO_CHAR(NVL(comm, 0), '9990.99') + " + mgrComm
				+ " WHERE empno = " + mgrNo;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			recordCount = stmt.executeUpdate(query);
			stmt.close();
			stmt = conn.createStatement();
			recordCount = stmt.executeUpdate(query2);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (stmt != null) 
				try { 
					stmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
	
	public static void printEmpInfo(int empNo) {
		// empNo에 해당하는 사원에 관한 정보(소속부서명 포함)를 검색하여 화면에 출력
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT ename, job, sal, comm, dname "
				+ "FROM DEPT191018 JOIN EMP191018 USING(deptno) "
				+ "WHERE empno = " + empNo;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				float sal = rs.getFloat("sal");
				float comm = rs.getFloat("comm");
				String dname = rs.getString("dname");
				
				System.out.print(ename + "\t" + job + "\t");
				System.out.printf("%4.2f", sal);
				System.out.print("\t");
				System.out.printf("%4.2f", comm);
				System.out.print("\t");
				System.out.println(dname);
			}
			else {
				System.out.println("관리자를 찾지 못했습니다.");
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (stmt != null) 
				try { 
					stmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);	
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		
		// printDeptInfo 메소드 호출
		int deptNo = printDeptInfo(deptName);
		
		// printEmployeesInDept 메소드 호출
		printEmployeesInDept(deptNo);

		/* 관리자 사번(int)과 관리자 수당(double)을 입력받음 */
		System.out.print("새 관리자 사번과 관리자 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
			
		// replaceManagerOfDept 메소드 호출 
		replaceManagerOfDept(deptNo, managerNo, commission);

		System.out.println("새 관리자 정보: ");

		// printEmpInfo 메소드 호출
		printEmpInfo(managerNo);
		
		scanner.close();
	}
}
