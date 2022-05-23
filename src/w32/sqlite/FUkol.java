package w32.sqlite;
// Převeďte data z https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19
// (COVID-19: Přehled osob s prokázanou nákazou dle hlášení krajských
// hygienických stanic (v2)).
// K nalezení zde: X:\stemberk\verejne_zaci\osoby.csv
// do databáze (můžete si vybrat mezi SQLite, či MySQL).
// Totéž proveďte pro soubor X:\stemberk\verejne_zaci\staty.csv a zobrazte
// v TableView, případně v nějakém grafu JavaFX.

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Struktura tabulky:
 * id,datum,vek,pohlavi,kraj_nuts_kod,okres_lau_kod,nakaza_v_zahranici,nakaza_zeme_csu_kod,reportovano_khs
 * 1ea976a2-896a-40b2-b617-b780a713323d,2020-03-01,43,M,CZ042,CZ0421,1,IT,1
 */
public class FUkol {

    public static void insert(String id, String datum, int vek, String mf, String kraj, String okres, int vZahranici, String stat, int reportovanoKhs) {
        String sql = "INSERT INTO nakazeni5(id, datum, vek, mf, " +
                "kraj, okres, vZahranici, stat, reportovanoKhs) VALUES(?, ?, ?, ?, ?, ? , ? ,?, ?)";


        try (Connection conn = AMainDBConn.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, datum);
            pstmt.setInt(3, vek);
            pstmt.setString(4, mf);
            pstmt.setString(5, kraj);
            pstmt.setString(6, okres);
            pstmt.setInt(7, vZahranici);
            pstmt.setString(8, stat);
            pstmt.setInt(9, reportovanoKhs);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        String radka;
        fr = new FileReader("X:\\stemberk\\verejne_zaci\\osoby.csv");
        BufferedReader br = new BufferedReader(fr);
        br.readLine(); // prvni radku zahodime;

        int cnt = 0;
        while ((radka = br.readLine()) != null) {
            cnt++;
            String id = "";
            String datum = "";
            int vek = 0;
            String mf = "";
            String kraj = "";
            String okres = "";
            int vZahranici = 0;
            String stat = "";
            int reportovanoKhs = 0;
            if (cnt > 0) {
                String[] hodnoty = radka.split(",");
                if (hodnoty.length > 0) id = hodnoty[0];
                if (hodnoty.length > 1) datum = hodnoty[1];
                if (hodnoty.length > 2) vek = Integer.parseInt(hodnoty[2]);
                if (hodnoty.length > 3) mf = hodnoty[3];
                if (hodnoty.length > 4) kraj = hodnoty[4];
                if (hodnoty.length > 5) okres = hodnoty[5];
                if (hodnoty.length > 6) vZahranici = bolda(hodnoty[6]);
                if (hodnoty.length > 7) stat = hodnoty[7];
                if (hodnoty.length > 8) reportovanoKhs = bolda(hodnoty[8]);
                System.out.format("%s, %s, %d, %s, %s, %s, %s, %s, %s%n",
                        id, datum, vek, mf, kraj, okres, vZahranici, stat, reportovanoKhs);
                insert(id, datum, vek, mf, kraj, okres, vZahranici, stat, reportovanoKhs);
            }
            if (cnt++ > 100) break;
        }
    }

    public static int bolda(String bol) {
        if (Objects.equals(bol, "true")) return 1;
        else return 0;
    }
}
