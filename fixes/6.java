protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp", "root", "passw0rd");

                        Statement st = conn.createStatement();
                        String sql = "SELECT * FROM users WHERE username= ? AND password= ?";
                        System.out.println(sql);

                        PreparedStatement prepStmt = conn.prepareStatement(sql);
                        prepStmt.setString(1, username);
                        prepStmt.setString(2, password);
                        ResultSet rs = prepStmt.executeQuery();

                        if (rs.next()) {
                                HttpSession session = request.getSession();
                                session.setAttribute("username", username);
                                response.sendRedirect("search.jsp");
                        } else {
                                out.println("Invalid username and/or password");
                        }
                        conn.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        } finally {
                out.close();
        }
}
