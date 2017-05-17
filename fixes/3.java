
Connection con = null;
Statement stmt = null;
ResultSet rs = null;

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

try{
								con = DBConnection.getConnection();
								stmt = con.createStatement();
								String query = "select name, country, password from Users where email = ? and password= ?";
								System.out.println(query);

								prepStmt.setString(1, id);
								prepStmt.setString(2, pwd);
								ResultSet rs = prepStmt.executeQuery();

								while(rs.next()) {
																System.out.println("Name="+rs.getString("name")+",country="+rs.getString("country")+",password="+rs.getString("password"));
								}
}finally{
								if(rs != null) rs.close();
								stmt.close();
								con.close();
}
