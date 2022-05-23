package w32;

import javafx.beans.property.*;

import javax.print.DocFlavor;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {

    private final ReadOnlyIntegerWrapper id =
            new ReadOnlyIntegerWrapper(this, "id", 0); //idSequence.incrementAndGet());
    private final StringProperty datum =
            new SimpleStringProperty(this, "datum", null);
    private final IntegerProperty vek =
            new SimpleIntegerProperty(this, "vek", 0);
    private final StringProperty mf =
            new SimpleStringProperty(this, "mf", null);
    private final StringProperty kraj =
            new SimpleStringProperty(this, "kraj", null);
    private final StringProperty okres =
            new SimpleStringProperty(this, "okres", null);
    private final IntegerProperty vZahranici =
            new SimpleIntegerProperty(this, "vZahranice", 0);
    private final StringProperty stat =
            new SimpleStringProperty(this, "stat", null);
    private final IntegerProperty reportovanoKhs =
            new SimpleIntegerProperty(this, "reportovanoKhs", 0);

    // Keeps track of last generated person id
    //private static AtomicInteger idSequence = new AtomicInteger(0);


    public Warehouse(int id, String datum, int vek, String mf, String kraj, String okres, int vZahranici, String stat, int reportovanoKhs) {
        this.id.set(id);
        this.datum.set(datum);
        this.vek.set(vek);
        this.mf.set(mf);
        this.kraj.set(kraj);
        this.okres.set(okres);
        this.vZahranici.set(vZahranici);
        this.stat.set(stat);
        this.reportovanoKhs.set(reportovanoKhs);
    }

    /* Id Property */

    public int getId() {
        return id.get();
    }

    public ReadOnlyIntegerWrapper idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDatum() {
        return datum.get();
    }

    public StringProperty datumProperty() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    public int getVek() {
        return vek.get();
    }

    public IntegerProperty vekProperty() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek.set(vek);
    }

    public String getMf() {
        return mf.get();
    }

    public StringProperty mfProperty() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf.set(mf);
    }

    public String getKraj() {
        return kraj.get();
    }

    public StringProperty krajProperty() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj.set(kraj);
    }

    public String getOkres() {
        return okres.get();
    }

    public StringProperty okresProperty() {
        return okres;
    }

    public void setOkres(String okres) {
        this.okres.set(okres);
    }

    public int getvZahranici() {
        return vZahranici.get();
    }

    public IntegerProperty vZahraniciProperty() {
        return vZahranici;
    }

    public void setvZahranici(int vZahranici) {
        this.vZahranici.set(vZahranici);
    }

    public int getReportovanoKhs() {
        return reportovanoKhs.get();
    }

    public IntegerProperty reportovanoKhsProperty() {
        return reportovanoKhs;
    }

    public void setReportovanoKhs(int reportovanoKhs) {
        this.reportovanoKhs.set(reportovanoKhs);
    }

    public String getStat() {
        return stat.get();
    }

    public StringProperty statProperty() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat.set(stat);
    }


    /* Domain specific business rules */
    public boolean isValidWarehouse(List<String> errorList) {
        return isValidWarehouse(this, errorList);
    }

    /* Domain specific business rules */
    public boolean isValidWarehouse(Warehouse w, List<String> errorList) {
        boolean isValid = true;
        String fn = w.id.toString();
        if (fn == null || fn.trim().length() == 0) {
            errorList.add("First name must contain minimum one character.");
            isValid = false;
        }
        return isValid;
    }

    /* Domain specific business rules */
    public boolean save(List<String> errorList) {
        boolean isSaved = false;
        if (isValidWarehouse(errorList)) {
            System.out.println("Saved " + this.toString());
            isSaved = true;
        }
        return isSaved;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", datum=" + datum +
                ", vek=" + vek +
                ", mf=" + mf +
                ", kraj=" + kraj +
                ", okres=" + okres +
                ", vZahranici=" + vZahranici +
                ", stat=" + stat +
                ", reportovanoKhs=" + reportovanoKhs +
                '}';
    }

}
