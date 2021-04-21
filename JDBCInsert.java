package jdbc.example.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCInsert {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("회원정보 입력>");
		System.out.print("ID>");
		String id = scan.next();
		
		System.out.print("NAME>");
		String name = scan.next();
		
		System.out.print("EMAIL>");
		String email = scan.next();
		
		// DB 연결에 필요한 변수
		String url = "jdbc:oracle:thin:@221.155.6.131:1521/XEPDB1";
		String uid = "hr";
		String upw = "hr";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		// rs는 select가 아니라면 필요 없다.
		
		String sql = "insert into emps(employee_id, last_name, email, hire_date, job_id) "
				   + "values (?, ?, ?, sysdate, 100)";
		
		try {
			
			// 드라이버 호출
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 커넥션 생성
			conn = DriverManager.getConnection(url, uid, upw);
			
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			
			// sql 실행 - insert, update, delete는 executeUpdate()로 실행한다.
			int result = pstmt.executeUpdate(); // 성공하면 1 반환, 실패하면 0 반환
			
			if (result == 1) { // 성공
				System.out.println("성공적으로 입력되었습니다.");
			} else { // 실패
				System.out.println("입력에 실패했습니다. 다시 시도하세요.");
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
