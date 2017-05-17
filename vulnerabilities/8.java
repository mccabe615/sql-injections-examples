public void doPrivilegedAction(String username, char[] password)
throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
                // Handle error
        }
        try {
                String pwd = hashPassword(password);

                String sqlString = "SELECT * FROM db_user WHERE username = '"
                                   + username +
                                   "' AND password = '" + pwd + "'";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sqlString);

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
