
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
try{
								con = DBConnection.getConnection();
								stmt = con.createStatement();
								String query = "select name, country, password from Users where email = '"+id+"' and password='"+pwd+"'";
								System.out.println(query);
								rs = stmt.executeQuery(query);

								while(rs.next()) {
																System.out.println("Name="+rs.getString("name")+",country="+rs.getString("country")+",password="+rs.getString("password"));
								}
}finally{
								if(rs != null) rs.close();
								stmt.close();
								con.close();
}
