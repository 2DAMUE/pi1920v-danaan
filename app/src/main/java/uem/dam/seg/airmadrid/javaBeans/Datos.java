package uem.dam.seg.airmadrid.javaBeans;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.net.URL;
import java.util.List;

@Root(name="Datos")
public class Datos {

    @ElementList(name="Dato_Horario", required=false, entry="Dato_Horario", inline=true)
    List<DatoHorario> datoHorario;

    public List<DatoHorario> getDatoHorario() {return this.datoHorario;}
    public void setDatoHorario(List<DatoHorario> value) {this.datoHorario = value;}

    public static class DatoHorario {

        @Element(name="punto_muestreo", required=false)
        String puntoMuestreo;

        @Element(name="H21", required=false)
        String h21;

        @Element(name="H20", required=false)
        String h20;

        @Element(name="H23", required=false)
        String h23;

        @Element(name="H22", required=false)
        String h22;

        @Element(name="H24", required=false)
        String h24;

        @Element(name="V21", required=false)
        String v21;

        @Element(name="V20", required=false)
        String v20;

        @Element(name="V23", required=false)
        String v23;

        @Element(name="V22", required=false)
        String v22;

        @Element(name="V24", required=false)
        String v24;

        @Element(name="municipio", required=false)
        String municipio;

        @Element(name="H01", required=false)
        String h01;

        @Element(name="H03", required=false)
        String h03;

        @Element(name="H02", required=false)
        String h02;

        @Element(name="provincia", required=false)
        String provincia;

        @Element(name="H05", required=false)
        String h05;

        @Element(name="H04", required=false)
        String h04;

        @Element(name="H07", required=false)
        String h07;

        @Element(name="H06", required=false)
        String h06;

        @Element(name="H09", required=false)
        String h09;

        @Element(name="H08", required=false)
        String h08;

        @Element(name="V01", required=false)
        String v01;

        @Element(name="V03", required=false)
        String v03;

        @Element(name="V02", required=false)
        String v02;

        @Element(name="estacion", required=false)
        String estacion;

        @Element(name="mes", required=false)
        String mes;

        @Element(name="V05", required=false)
        String v05;

        @Element(name="V04", required=false)
        String v04;

        @Element(name="V07", required=false)
        String v07;

        @Element(name="V06", required=false)
        String v06;

        @Element(name="V09", required=false)
        String v09;

        @Element(name="V08", required=false)
        String v08;

        @Element(name="ano", required=false)
        String ano;

        @Element(name="H10", required=false)
        String h10;

        @Element(name="H12", required=false)
        String h12;

        @Element(name="H11", required=false)
        String h11;

        @Element(name="magnitud", required=false)
        String magnitud;

        @Element(name="H14", required=false)
        String h14;

        @Element(name="H13", required=false)
        String h13;

        @Element(name="H16", required=false)
        String h16;

        @Element(name="H15", required=false)
        String h15;

        @Element(name="H18", required=false)
        String h18;

        @Element(name="H17", required=false)
        String h17;

        @Element(name="H19", required=false)
        String h19;

        @Element(name="V10", required=false)
        String v10;

        @Element(name="V12", required=false)
        String v12;

        @Element(name="V11", required=false)
        String v11;

        @Element(name="V14", required=false)
        String v14;

        @Element(name="V13", required=false)
        String v13;

        @Element(name="V16", required=false)
        String v16;

        @Element(name="V15", required=false)
        String v15;

        @Element(name="V18", required=false)
        String v18;

        @Element(name="dia", required=false)
        String dia;

        @Element(name="V17", required=false)
        String v17;

        @Element(name="V19", required=false)
        String v19;

        public String getPuntoMuestreo() {return this.puntoMuestreo;}
        public void setPuntoMuestreo(String value) {this.puntoMuestreo = value;}

        public String getH21() {return this.h21;}
        public void setH21(String value) {this.h21 = value;}

        public String getH20() {return this.h20;}
        public void setH20(String value) {this.h20 = value;}

        public String getH23() {return this.h23;}
        public void setH23(String value) {this.h23 = value;}

        public String getH22() {return this.h22;}
        public void setH22(String value) {this.h22 = value;}

        public String getH24() {return this.h24;}
        public void setH24(String value) {this.h24 = value;}

        public String getV21() {return this.v21;}
        public void setV21(String value) {this.v21 = value;}

        public String getV20() {return this.v20;}
        public void setV20(String value) {this.v20 = value;}

        public String getV23() {return this.v23;}
        public void setV23(String value) {this.v23 = value;}

        public String getV22() {return this.v22;}
        public void setV22(String value) {this.v22 = value;}

        public String getV24() {return this.v24;}
        public void setV24(String value) {this.v24 = value;}

        public String getMunicipio() {return this.municipio;}
        public void setMunicipio(String value) {this.municipio = value;}

        public String getH01() {return this.h01;}
        public void setH01(String value) {this.h01 = value;}

        public String getH03() {return this.h03;}
        public void setH03(String value) {this.h03 = value;}

        public String getH02() {return this.h02;}
        public void setH02(String value) {this.h02 = value;}

        public String getProvincia() {return this.provincia;}
        public void setProvincia(String value) {this.provincia = value;}

        public String getH05() {return this.h05;}
        public void setH05(String value) {this.h05 = value;}

        public String getH04() {return this.h04;}
        public void setH04(String value) {this.h04 = value;}

        public String getH07() {return this.h07;}
        public void setH07(String value) {this.h07 = value;}

        public String getH06() {return this.h06;}
        public void setH06(String value) {this.h06 = value;}

        public String getH09() {return this.h09;}
        public void setH09(String value) {this.h09 = value;}

        public String getH08() {return this.h08;}
        public void setH08(String value) {this.h08 = value;}

        public String getV01() {return this.v01;}
        public void setV01(String value) {this.v01 = value;}

        public String getV03() {return this.v03;}
        public void setV03(String value) {this.v03 = value;}

        public String getV02() {return this.v02;}
        public void setV02(String value) {this.v02 = value;}

        public String getEstacion() {return this.estacion;}
        public void setEstacion(String value) {this.estacion = value;}

        public String getMes() {return this.mes;}
        public void setMes(String value) {this.mes = value;}

        public String getV05() {return this.v05;}
        public void setV05(String value) {this.v05 = value;}

        public String getV04() {return this.v04;}
        public void setV04(String value) {this.v04 = value;}

        public String getV07() {return this.v07;}
        public void setV07(String value) {this.v07 = value;}

        public String getV06() {return this.v06;}
        public void setV06(String value) {this.v06 = value;}

        public String getV09() {return this.v09;}
        public void setV09(String value) {this.v09 = value;}

        public String getV08() {return this.v08;}
        public void setV08(String value) {this.v08 = value;}

        public String getAno() {return this.ano;}
        public void setAno(String value) {this.ano = value;}

        public String getH10() {return this.h10;}
        public void setH10(String value) {this.h10 = value;}

        public String getH12() {return this.h12;}
        public void setH12(String value) {this.h12 = value;}

        public String getH11() {return this.h11;}
        public void setH11(String value) {this.h11 = value;}

        public String getMagnitud() {return this.magnitud;}
        public void setMagnitud(String value) {this.magnitud = value;}

        public String getH14() {return this.h14;}
        public void setH14(String value) {this.h14 = value;}

        public String getH13() {return this.h13;}
        public void setH13(String value) {this.h13 = value;}

        public String getH16() {return this.h16;}
        public void setH16(String value) {this.h16 = value;}

        public String getH15() {return this.h15;}
        public void setH15(String value) {this.h15 = value;}

        public String getH18() {return this.h18;}
        public void setH18(String value) {this.h18 = value;}

        public String getH17() {return this.h17;}
        public void setH17(String value) {this.h17 = value;}

        public String getH19() {return this.h19;}
        public void setH19(String value) {this.h19 = value;}

        public String getV10() {return this.v10;}
        public void setV10(String value) {this.v10 = value;}

        public String getV12() {return this.v12;}
        public void setV12(String value) {this.v12 = value;}

        public String getV11() {return this.v11;}
        public void setV11(String value) {this.v11 = value;}

        public String getV14() {return this.v14;}
        public void setV14(String value) {this.v14 = value;}

        public String getV13() {return this.v13;}
        public void setV13(String value) {this.v13 = value;}

        public String getV16() {return this.v16;}
        public void setV16(String value) {this.v16 = value;}

        public String getV15() {return this.v15;}
        public void setV15(String value) {this.v15 = value;}

        public String getV18() {return this.v18;}
        public void setV18(String value) {this.v18 = value;}

        public String getDia() {return this.dia;}
        public void setDia(String value) {this.dia = value;}

        public String getV17() {return this.v17;}
        public void setV17(String value) {this.v17 = value;}

        public String getV19() {return this.v19;}
        public void setV19(String value) {this.v19 = value;}

    }

}