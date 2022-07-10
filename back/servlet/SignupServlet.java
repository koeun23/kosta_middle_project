import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.sql.MyConnection;


public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
//		System.out.println(id + ":" + pwd + ":" + name + ":" + addr + ":" + buildingno);
		
		//-------------------------------------------------------
		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
		//DB연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null; //executeUpdate()
		String insertSQL = "INSERT INTO customer(user_name,user_id,user_pwd, user_email, status, ) VALUES(?,?,?,?,1,)";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_pwd);
			pstmt.setString(4, user_email);
			pstmt.executeUpdate();result = "{\"status\":0, \"msg\": \"가입성공\"}";
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}finally {
		//DB연결닫기
			MyConnection.close(pstmt, con);
		}
		//-------------------------------------------------------
		
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}