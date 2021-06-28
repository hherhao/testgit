package banking;

import java.sql.*;

class SQLITEDB {
    static void initalizeDB(String DBfilename){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+DBfilename);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE card " +
                    "(id INT," +
                    " number TEXT," +
                    " pin TEXT," +
                    " balance INT DEFAULT 0)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    static void insert(Integer id, String number, String pin, Integer balance, String filename) {
        String sql = "INSERT INTO card(id, number, pin, balance) VALUES(?,?,?,?)";
        String url = "jdbc:sqlite:" + filename;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, number);
            pstmt.setString(3, pin);
            pstmt.setInt(4, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static String selectpin(String number, String filename) {
        String sql = "select pin from card where number = ?";
        String url = "jdbc:sqlite:" + filename;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            ResultSet rs = pstmt.executeQuery();
            return rs.getString("pin");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "o";
        }
    }

    static int selectbalance(String number, String filename) {
        String sql = "select balance from card where number = ?";
        String url = "jdbc:sqlite:" + filename;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }


    static void dropDB(String DBfilename){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+DBfilename);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DROP TABLE card ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table card deletes successfully");
    }

    static void Add_income(String number, int income,String filename) {
        String sql = "update card set balance = balance + ? where number = ? ";
        String url = "jdbc:sqlite:" + filename;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, income);
            pstmt.setString(2, number);
            pstmt.executeUpdate();
            System.out.println("Income was added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void Close_account(String number,String filename) {
        String sql = "DELETE from card where number = ? ";
        String url = "jdbc:sqlite:" + filename;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            pstmt.executeUpdate();
            System.out.println("The account has been closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }







}