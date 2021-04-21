package jdbc.example.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCUpdate {

	public static void main(String[] args) {
		
		/*
		 * 스캐너를 이용해서
		 * emps에서 수정할 id를 받아서 이름, 이메일, 급여를 수정하는 JDBC프로그램을 작성
		 */
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수정할 회원아이디>");
		String id = scan.next();
		
		System.out.print("수정할 이름>");
		String name = scan.next();
		
		System.out.print("수정할 이메일>");
		String email = scan.next();
		
		System.out.print("수정할 급여>");
		int salary = scan.nextInt();
		
		// DB 연결에 필요한 변수
		String url = "jdbc:oracle:thin:@221.155.6.131:1521/XEPDB1";
		String uid = "hr";
		String upw = "hr";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update emps "
				   + "set last_name = ?, email = ?, salary = ? "
				   + "where employee_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setInt(3, salary);
			pstmt.setString(4, id);
			
			int result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				
			}
		}
	}
}















