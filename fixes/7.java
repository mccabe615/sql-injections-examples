protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

                String product_name = request.getParameter("product_name");

                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp", "root", "passw0rd");

                        Statement st = conn.createStatement();
                        String sql = "SELECT product_name, product_price FROM products WHERE product_name=?";
                        System.out.println(sql);

                        PreparedStatement prepStmt = conn.prepareStatement(query);
                        prepStmt.setString(1, prod_name);
                        ResultSet rs = prepStmt.executeQuery();

                        while (rs.next()) {
                                String prod_name = rs.getString("product_name");
                                String prod_price = rs.getString("product_price");
                                out.println(prod_name);
                                out.println(prod_price);
                                out.println("<br/>");
                        }
                        conn.close();

                } catch (Exception e) {
                        e.printStackTrace();
                }
        } finally {
                out.close();
        }
}
