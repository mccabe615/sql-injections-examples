public void doPrivilegedAction(
        String username, char[] password
        ) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
                // Handle error
        }
        try {
                String pwd = hashPassword(password);
                String sqlString = "select * from db_user where username=? and password =?";
                PreparedStatement prepStmt = connection.prepareStatement(sqlString);
                prepStmt.setString(1, username);
                prepStmt.setString(2, pwd);
                ResultSet rs = prepStmt.executeQuery();

                if (!rs.next()) {
                        throw new SecurityException("User name or password incorrect");
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
