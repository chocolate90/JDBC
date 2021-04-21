package jdbc.example.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCSelect2 {

	public static void main(String[] args) {
		
		// 회원 아이디를 받아서 해당 회원의 정보만 조회
		Scanner scan = new Scanner(System.in);
		
		System.out.println("조회할 아이디>");
		String id = scan.next();
		
		// DB에 필요한 변수 선언
		
		String url = "jdbc:oracle:thin:@221.155.6.131:1521/XEPDB1";
		String uid = "hr";
		String upw = "hr";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// sql문에 전달할 값이 있다면 ?로 표시한다.
		String sql = "select * from employees where employee_id = ?";
		
		try {
			
			// 드라이버 호출
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 커넥션 객체
			conn = DriverManager.getConnection(url, uid, upw);
			
			// pstmt 객체
			// ?는 순서대로 index를 가지고 순서대로 채우는 작업을 해야한다.
			// 문자열 - setString (인덱스, 값)
			// 숫자 - setInt (인덱스, 값)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//결과가 1행이기 때문에
			if(rs.next()) { // 있는 경우
				
				String name = rs.getString("last_name");
				int salary = rs.getInt("salary");
				
				System.out.println(name + "의 급여:" + salary);
				
			} else { // 없는 경우
				System.out.println(id + "는 존재하지 않는 아이디 입니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				
			}
		}
	}
}
