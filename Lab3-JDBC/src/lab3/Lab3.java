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

	public Lab3() {	// ������ 
		// JDBC ����̹� �ε� �� ���
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

		// DBMS���� ���� ȹ��
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
	
	public static int printDeptInfo(String deptName) {
		// deptName �μ��� ���� ������ �� �μ��� ���� ��� ���� ����Ͽ� ȭ�鿡 ���
		Connection conn = null;
		PreparedStatement pStmt = null;			// PreparedStatment ���� ���� ����
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
				
				System.out.println("�μ���ȣ: " + deptNo);
				System.out.println("�μ���: " + deptName);
				System.out.println("�����ڻ��: " + mgrNo);
				System.out.println("��� ��: " + numOfEmp);
				System.out.println();
				
				return deptNo;
			}
			else {
				System.out.println("�μ��� ã�� ���߽��ϴ�.");
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
		// deptNo �μ��� ���� ��� ����鿡 ���� �ʿ��� ������ �˻��Ͽ� ȭ�鿡 ���
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsMetaData = null;
		String query = "SELECT empno AS ���, ename AS �̸�, job AS ����, TO_CHAR(sal, '9990.99') AS �޿�, TO_CHAR(NVL(comm, 0), '9990.99') AS ���� "
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
		// deptNo �μ��� �����ڻ���� �־��� mgrNo�� �����ϰ�, �� ����� ������ mgrComm����ŭ ������Ŵ
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
		// empNo�� �ش��ϴ� ����� ���� ����(�ҼӺμ��� ����)�� �˻��Ͽ� ȭ�鿡 ���
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
				System.out.println("�����ڸ� ã�� ���߽��ϴ�.");
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
		
		System.out.print("�μ����� �Է��Ͻÿ�: ");
		String deptName = scanner.next();
		
		// printDeptInfo �޼ҵ� ȣ��
		int deptNo = printDeptInfo(deptName);
		
		// printEmployeesInDept �޼ҵ� ȣ��
		printEmployeesInDept(deptNo);

		/* ������ ���(int)�� ������ ����(double)�� �Է¹��� */
		System.out.print("�� ������ ����� ������ ���������� �Է��Ͻÿ�: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
			
		// replaceManagerOfDept �޼ҵ� ȣ�� 
		replaceManagerOfDept(deptNo, managerNo, commission);

		System.out.println("�� ������ ����: ");

		// printEmpInfo �޼ҵ� ȣ��
		printEmpInfo(managerNo);
		
		scanner.close();
	}
}
