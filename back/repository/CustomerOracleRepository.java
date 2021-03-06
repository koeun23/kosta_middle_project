import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.projectdto.Customer;
import com.my.sql.MyConnection;

public class CustomerOracleRepository implements CustomerRepository {

	@Override
	public void insert(Customer product) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Customer> selectAll() throws FindException {
		//List<Map<String,Object>> sample=new ArrayList<>();
		List<Customer> products=new ArrayList<>();
				
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String selectcustomerAllSQL="SELECT * FROM customer ORDER BY user_no ASC";
		try {
			con=MyConnection.getConnection();
			pstmt=con.prepareStatement(selectcustomerAllSQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int userNo=rs.getInt("user_no");
				String userId=rs.getString("user_id");
				String userPwd=rs.getString("user_pwd");
				String userName=rs.getString("user_name");
				String userEmail=rs.getString("user_email");
		
//						Map<String,Object> map1=new HashMap<>();
//						map1.put("prod_no", prod_no);
//						map1.put("prod_name", prod_name);
//						map1.put("prod_price", prod_price);
						Customer p=new Customer(userNo,userId,userPwd,userName,userEmail);
						
//						sample.add(map1);
				products.add(p);
			}
			if(products.size()==0) {
				throw new FindException("상품이 없습니다");
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs,pstmt,con);
		}
	}

	@Override
	public Customer selectByUserNo(String prodNo) throws FindException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String selectProductProdNo="SELECT * FROM customer WHERE user_no=?";
		try {
			con=MyConnection.getConnection();
			pstmt=con.prepareStatement(selectProductProdNo);
			pstmt.setString(1,prodNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int userNo=rs.getInt("userNo");
				String userId=rs.getString("userId");
				String userPwd=rs.getString("userPwd");
				String userName=rs.getString("userName");
				String userEmail=rs.getString("userEmail");
				Customer p=new Customer(userNo,userId,userPwd,userName,userEmail);
				return p;
			}else {
				throw new FindException("상품이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs,pstmt,con);
		}
	}

}
