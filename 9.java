public void doPrivilegedAction(
        String username, char[] password
        ) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
                // Handle error
        }
        try {
                String pwd = hashPassword(password);
                String sqlString = "select * from db_user where username=" +
                                   username + " and password =" + pwd;
                PreparedStatement stmt = connection.prepareStatement(sqlString);

                ResultSet rs = stmt.executeQuery();
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
