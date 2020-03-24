package uem.dam.seg.airmadrid.javaBeans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "Dato_Horario")
public class Estacion {
    /*Dióxido de Azufre SO2
    Dióxido de Nitrógeno NO2
    Partículas < 2.5 µm PM2.5
    Partículas < 10 µm PM10*/

    /*Date fechaAct = new Date();
    String sFecAct = fechaAct.toString();*/

    @Element(name="estacion")
    private String nombreEstacion;
    @Element(name="magnitud")
    private String magnitud;
    @Element(name="H01")
    private String h1;
    @Element(name="H02")
    private String h2;
    @Element(name="H03")
    private String h3;
    @Element(name="H04")
    private String h4;
    @Element(name="H05")
    private String h5;
    @Element(name="H06")
    private String h6;
    @Element(name="H07")
    private String h7;
    @Element(name="H08")
    private String h8;
    @Element(name="H09")
    private String h09;
    @Element(name="H10")
    private String h10;
    @Element(name="H11")
    private String h11;
    @Element(name="H12")
    private String h12;
    @Element(name="H13")
    private String h13;
    @Element(name="H14")
    private String h14;
    @Element(name="H15")
    private String h15;
    @Element(name="H16")
    private String h16;
    @Element(name="H17")
    private String h17;
    @Element(name="H18")
    private String h18;
    @Element(name="H19")
    private String h19;
    @Element(name="H20")
    private String h20;
    @Element(name="H21")
    private String h21;
    @Element(name="H22")
    private String h22;
    @Element(name="H23")
    private String h23;
    @Element(name="H24")
    private String h24;

    public Estacion() {
    }

    public Estacion(String nombreEstacion, String magnitud, String h1, String h2, String h3, String h4, String h5, String h6, String h7, String h8, String h09, String h10, String h11, String h12, String h13, String h14, String h15, String h16, String h17, String h18, String h19, String h20, String h21, String h22, String h23, String h24) {
        this.nombreEstacion = nombreEstacion;
        this.magnitud = magnitud;
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.h5 = h5;
        this.h6 = h6;
        this.h7 = h7;
        this.h8 = h8;
        this.h09 = h09;
        this.h10 = h10;
        this.h11 = h11;
        this.h12 = h12;
        this.h13 = h13;
        this.h14 = h14;
        this.h15 = h15;
        this.h16 = h16;
        this.h17 = h17;
        this.h18 = h18;
        this.h19 = h19;
        this.h20 = h20;
        this.h21 = h21;
        this.h22 = h22;
        this.h23 = h23;
        this.h24 = h24;
    }

    public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return h4;
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getH5() {
        return h5;
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }

    public String getH6() {
        return h6;
    }

    public void setH6(String h6) {
        this.h6 = h6;
    }

    public String getH7() {
        return h7;
    }

    public void setH7(String h7) {
        this.h7 = h7;
    }

    public String getH8() {
        return h8;
    }

    public void setH8(String h8) {
        this.h8 = h8;
    }

    public String getH09() {
        return h09;
    }

    public void setH09(String h09) {
        this.h09 = h09;
    }

    public String getH10() {
        return h10;
    }

    public void setH10(String h10) {
        this.h10 = h10;
    }

    public String getH11() {
        return h11;
    }

    public void setH11(String h11) {
        this.h11 = h11;
    }

    public String getH12() {
        return h12;
    }

    public void setH12(String h12) {
        this.h12 = h12;
    }

    public String getH13() {
        return h13;
    }

    public void setH13(String h13) {
        this.h13 = h13;
    }

    public String getH14() {
        return h14;
    }

    public void setH14(String h14) {
        this.h14 = h14;
    }

    public String getH15() {
        return h15;
    }

    public void setH15(String h15) {
        this.h15 = h15;
    }

    public String getH16() {
        return h16;
    }

    public void setH16(String h16) {
        this.h16 = h16;
    }

    public String getH17() {
        return h17;
    }

    public void setH17(String h17) {
        this.h17 = h17;
    }

    public String getH18() {
        return h18;
    }

    public void setH18(String h18) {
        this.h18 = h18;
    }

    public String getH19() {
        return h19;
    }

    public void setH19(String h19) {
        this.h19 = h19;
    }

    public String getH20() {
        return h20;
    }

    public void setH20(String h20) {
        this.h20 = h20;
    }

    public String getH21() {
        return h21;
    }

    public void setH21(String h21) {
        this.h21 = h21;
    }

    public String getH22() {
        return h22;
    }

    public void setH22(String h22) {
        this.h22 = h22;
    }

    public String getH23() {
        return h23;
    }

    public void setH23(String h23) {
        this.h23 = h23;
    }

    public String getH24() {
        return h24;
    }

    public void setH24(String h24) {
        this.h24 = h24;
    }
}
