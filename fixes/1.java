String query = "SELECT account_balance FROM user_data WHERE user_name = ?";

String customerName = request.getParameter("customerName");

  try {
    PreparedStatement prepStmt = conn.prepareStatement(query);
    prepStmt.setString(1, customerName);
    ResultSet results = prepStmt.executeQuery();
}
