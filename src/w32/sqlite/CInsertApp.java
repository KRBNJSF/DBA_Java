package w32.sqlite;

import java.sql.*;

public class CInsertApp {

    /**
     * Insert a new row into the warehouse table
     */
    public static void insert(String id, String datum, int vek, String mf, String kraj, String okres, Boolean vZahranici, String stat, Boolean reportovanoKhs) {
        String sql = "INSERT INTO nakazeni(id, datum, vek, mf, " +
                "kraj, okres, vZahranici, stat, reportovanoKhs) VALUES(?, ?, ?, ?, ?, ? , ? ,?, ?)";


        try (Connection conn = AMainDBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, datum);
            pstmt.setInt(3, vek);
            pstmt.setString(4, mf);
            pstmt.setString(5, kraj);
            pstmt.setString(6, okres);
            pstmt.setBoolean(7, vZahranici);
            pstmt.setString(8, stat);
            pstmt.setBoolean(8, reportovanoKhs);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert a new row into the warehouse table, it return new id creted during insert.
     *
     * @param id
     * @param datum
     * @param vek
     * @param mf
     * @param kraj
     * @param okres
     * @param vZahranici
     * @param stat
     * @param reportovanoKhs
     * @returns id
     */
    public long insertReturningId(String id, String datum, int vek, String mf, String kraj, String okres, Boolean vZahranici, String stat, Boolean reportovanoKhs) {
        String sql = "INSERT INTO nakazeni(id, datum, vek, mf, " +
                "kraj, okres, vZahranici, stat, reportovanoKhs) VALUES(?, ?, ?, ?, ?, ? , ? ,?, ?)";
        long returnValue = -1;
        try (Connection conn = AMainDBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, id);
            pstmt.setString(2, datum);
            pstmt.setInt(3, vek);
            pstmt.setString(4, mf);
            pstmt.setString(5, kraj);
            pstmt.setString(6, okres);
            pstmt.setBoolean(7, vZahranici);
            pstmt.setString(8, stat);
            pstmt.setBoolean(8, reportovanoKhs);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    returnValue = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CInsertApp app = new CInsertApp();
        // insert three new rows
        app.insert("1", "2018-01-01", 1, "mf1", "kraj1", "okres1", true, "stat1", true);
        System.out.println("odpad ma id " + app.insertReturningId("1", "12.12.2012", 12, "mf", "kraj", "okres", false, "stat", false));
    }

}
