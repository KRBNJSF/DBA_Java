package w32.sqlite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import w32.Warehouse;

import java.sql.*;
import java.time.LocalDate;

public class EWarehouseTableUtil {
    /* Returns an observable list of persons */
    public static ObservableList<Warehouse> getWarehouseList() {
        ObservableList<Warehouse> ret = FXCollections.<Warehouse>observableArrayList();
        Connection c = AMainDBConn.connect();
        String sql = "SELECT * FROM nakazeni5;";
        try (Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            int cnt = 0;
            while (rs.next()) {
                System.out.println(cnt++);
                //System.out.format("id:%d, name: %s, c:%d%n", rs.getInt("id"), rs.getString("name"), rs.getInt("capacity"));
                //System.out.println(new Warehouse(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity")).toString());
                ret.add(new Warehouse(rs.getInt("id"), rs.getString("datum"), rs.getInt("vek"), rs.getString("mf"), rs.getString("kraj"), rs.getString("okres"), rs.getInt("vZahranici"), rs.getString("stat"), rs.getInt("reportovanoKhs")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Warehouse p1 = new Warehouse(0, "Sharan", 4000 );
        return ret; //FXCollections.<Warehouse>observableArrayList(p1);
    }

    /* Returns Id TableColumn */
    public static TableColumn<Warehouse, Integer> getIdColumn() {
        TableColumn<Warehouse, Integer> personIdCol = new TableColumn<>("id");
        personIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return personIdCol;
    }

    /* Returns Date TableColumn */
    public static TableColumn<Warehouse, String> getDatumColumn() {
        TableColumn<Warehouse, String> fNameCol = new TableColumn<>("datum");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        return fNameCol;
    }

    /* Returns Last Age TableColumn */
    public static TableColumn<Warehouse, Integer> getVekColumn() {
        TableColumn<Warehouse, Integer> lastNameCol = new TableColumn<>("vek");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("vek"));
        return lastNameCol;
    }

    public static TableColumn<Warehouse, String> getmfColumn() {
        TableColumn<Warehouse, String> fNameCol = new TableColumn<>("mf");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("mf"));
        return fNameCol;
    }

    public static TableColumn<Warehouse, String> getKrajColumn() {
        TableColumn<Warehouse, String> fNameCol = new TableColumn<>("kraj");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("kraj"));
        return fNameCol;
    }

    public static TableColumn<Warehouse, String> getOkresColumn() {
        TableColumn<Warehouse, String> fNameCol = new TableColumn<>("okres");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("okres"));
        return fNameCol;
    }

    public static TableColumn<Warehouse, Integer> getVZahraniciColumn() {
        TableColumn<Warehouse, Integer> fNameCol = new TableColumn<>("vZahranici");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("vZahranici"));
        return fNameCol;
    }

    public static TableColumn<Warehouse, String> getStatColumn() {
        TableColumn<Warehouse, String> fNameCol = new TableColumn<>("stat");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("stat"));
        return fNameCol;
    }

    public static TableColumn<Warehouse, Integer> getReportovanoKshColumn() {
        TableColumn<Warehouse, Integer> fNameCol = new TableColumn<>("reportovanoKhs");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("reportovanoKhs"));
        return fNameCol;
    }

}
