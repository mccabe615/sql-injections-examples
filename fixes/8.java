public void doPrivilegedAction(String username, char[] password)
throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
                // Handle error
        }
        try {
                String pwd = hashPassword(password);

                String sqlString = "SELECT * FROM db_user WHERE username =  AND password = ?";

                PreparedStatement prepStmt = conn.prepareStatement(sqlString);
                prepStmt.setString(1, username);
                prepStmt.setString(2, pwd);
                ResultSet rs = prepStmt.executeQuery();

                if (!rs.next()) {
                        throw new SecurityException(
                                      "User name or password incorrect"
                                      );
                }

                // Authenticated; proceed
        } finally {
                try {
                        connection.close();
                } catch (SQLException x) {
                        // Forward to handler
                }
        }
}
