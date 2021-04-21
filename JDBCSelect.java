package jdbc.example.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class JDBCSelect {

	public static void main(String[] args) {
		
		// 1.데이터베이스 연결에 필요한 변수선언
		// 연결주소형식: 정해진이름:아이피주소:포트번호:데이터베이스명
		
		String url = "jdbc:oracle:thin:@221.155.6.131:1521/XEPDB1";
		String uid = "hr";
		String upw = "hr";
		
		String sql = "select employee_id, last_name, salary, hire_date, department_id "
					 + "from employees";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 데이터베이스 연결구문은 throws를 던지고 있기 때문에 try구문 안에서 작성한다.
		
		try {
			
			// 2. 드라이버 로드(호출)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 3. Connection 객체 생성 - DriverManager클래스가 제공하는 getConnection(주소, 아이디, 비밀번호)
			conn = DriverManager.getConnection(url, uid, upw);
			
			System.out.println(conn);
			
			// 4.Statement 객체 생성 - Connection객체로 부터 생성
			// pstmt는 매개변수로 실행할 sql문을 받는다.
			pstmt = conn.prepareStatement(sql);
			
			// 5.sql문 쿼리 실행 - select문은 executeQuery()로 실행, i, u, d문은 executeUpdate()로 실행
			rs = pstmt.executeQuery();
			
			// 6.select문의 실행 결과가 하나라도 존재하면 ResultSet의 next()메서드는 true를 반환한다.

			while (rs.next()) {
				// 한 행에 대해서 처리할 작업
				// 문자열 - getString
				// 숫자형 - getInt
				// 날짜형 - Date or TimeStamp
				String id = rs.getString("employee_id");
				String name = rs.getString("last_name");
				Timestamp date = rs.getTimestamp("hire_date");
				int salary = rs.getInt("salary");
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(date);
				System.out.println(salary);
				System.out.println("------------------");
			}

			
		} catch (Exception e) {
				
			e.printStackTrace(); //오류결과
			
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
