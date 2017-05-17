
String query = "SELECT * FROM users WHERE userid ='"+ userid + "'" + " AND password='" + password + "'";
Statement stmt = connection.createStatement();
ResultSet rs = stmt.executeQuery(query);
