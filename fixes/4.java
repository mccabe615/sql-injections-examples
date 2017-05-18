protected AttackResult injectableQuery(String accountName)
{
        try
        {
                Connection connection = DatabaseUtilities.getConnection(getWebSession());
                String query = "SELECT * FROM user_data WHERE last_name =?";

                try
                {
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, accountName);
                        ResultSet results = statement.executeQuery(query);

                        if ((results != null) && (results.first() == true))
                        {
                                ResultSetMetaData resultsMetaData = results.getMetaData();
                                StringBuffer output = new StringBuffer();

                                output.append(writeTable(results, resultsMetaData));
                                results.last();

                                // If they get back more than one user they succeeded
                                if (results.getRow() >= 6)
                                {
                                        return trackProgress(success().feedback("sql-injection.5a.success").feedbackArgs(output.toString()).build());
                                } else {
                                        return trackProgress(failed().output(output.toString()).build());
                                }
                        }
                        else
                        {
                                return trackProgress(failed().feedback("sql-injection.5a.no.results").build());

                        }
                } catch (SQLException sqle)
                {

                        return trackProgress(failed().output(sqle.getMessage()).build());
                }
        } catch (Exception e)
        {
                e.printStackTrace();
                return trackProgress(failed().output(this.getClass().getName() + " : " + e.getMessage()).build());
        }
}
