
String query = "SELECT * FROM users WHERE userid ='"+ userid + "'" + " AND password='" + password + "'";

String userid = request.getParameter("userid");
String password = request.getParameter("password");

PreparedStatement stmt = conn.prepareStatement(query);
prepStmt.setString(1, userid);
prepStmt.setString(2, password);
ResultSet rs = prepStmt.executeQuery();
