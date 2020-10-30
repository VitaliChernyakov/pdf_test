package main;

import java.sql.*;
import java.util.ArrayList;

public class ModelOSV {

    public ArrayList<Integer> codeAdmList = new ArrayList<Integer>();
    public ArrayList<OSVData> osvDataList = new ArrayList<OSVData>();
    public String url = "jdbc:informix-sqli://10.247.12.31:1525/tmp:INFORMIXSERVER=ids_delta_1";
    public Connection connection;

    public ModelOSV() throws SQLException, ClassNotFoundException {

        Class.forName("com.informix.jdbc.IfxDriver");
        this.connection = DriverManager.getConnection(url, "prog686", "111111");
        this.codeAdmList.add(10);
    }

    public ArrayList<OSVData> getOsvDataList() {
        return osvDataList;
    }

    public ArrayList<Integer> getCodeAdmList() {
        return codeAdmList;
    }

    public void setOsvData(ArrayList<Integer> codeAdmList) throws SQLException {

        OSVData osvData = new OSVData();
        Statement statement = connection.createStatement();
        for (Integer codeAdm : codeAdmList) {
            statement.execute("execute procedure osv(" + codeAdm + ");");

            ResultSet resultSet = statement.executeQuery("select\n" +
                    "russ ," +
                    "sum(debet) debet ,\n" +
                    "sum(kredet) kredet,\n" +
                    "sum(nds_25_n) nds_25_n,\n" +
                    "sum(nds_20_n) nds_20_n,\n" +
                    "sum(nds_0_n) nds_0_n,\n" +
                    "sum( vsego_nachisl)  vsego_nach,\n" +
                    "sum(oborud)  odorud,\n" +
                    "sum(storon_usl)  storon_usl,\n" +
                    "sum(nds_25_rk) nds_25_rk,\n" +
                    "sum(nds_20_rk) nds_20_rk,\n" +
                    "sum(nds_0_rk)  nds_0_rk,\n" +
                    "sum( real_korr) real_korr,\n" +
                    "sum(real_korr_obor)  real_korr_obor,\n" +
                    "sum(storon_usl_rk)  storon_usl_rk,\n" +
                    "sum(vsego_pay)  vsego_pay,\n" +
                    "sum(vsego_pay_stor) vsego_pay_stor,\n" +
                    "sum(debet_nk )  debet_nk,\n" +
                    "sum(kredit_nk)  kredit_nk,\n" +
                    "sum(storon_nk)  storon,\n" +
                    "sum(debet_end) debet_end,\n" +
                    "sum(kredit_end)  kredit_end,\n" +
                    "sum(saldo_end)  s_end from  osv  group by 1");

            if (resultSet.next()) {
                osvData.russ = resultSet.getDouble("russ");
                osvData.debet = resultSet.getDouble("debet");
                osvData.kredet = resultSet.getDouble("kredet");
                osvData.nds_25_n = resultSet.getDouble("nds_25_n");
                osvData.nds_20_n = resultSet.getDouble("nds_20_n");
                osvData.nds_0_n = resultSet.getDouble("nds_0_n");
                osvData.vsego_nach = resultSet.getDouble("vsego_nach");
                osvData.odorud = resultSet.getDouble("odorud");
                osvData.storon_usl = resultSet.getDouble("storon_usl");
                osvData.nds_25_rk = resultSet.getDouble("nds_25_rk");
                osvData.nds_20_rk = resultSet.getDouble("nds_20_rk");
                osvData.nds_0_rk = resultSet.getDouble("nds_0_rk");
                osvData.real_korr = resultSet.getDouble("real_korr");
                osvData.real_korr_obor = resultSet.getDouble("real_korr_obor");
                osvData.storon_usl_rk = resultSet.getDouble("storon_usl_rk");
                osvData.vsego_pay = resultSet.getDouble("vsego_pay");
                osvData.vsego_pay_stor = resultSet.getDouble("vsego_pay_stor");
                osvData.debet_nk = resultSet.getDouble("debet_nk");
                osvData.kredit_nk = resultSet.getDouble("kredit_nk");
                osvData.storon = resultSet.getDouble("storon");
                osvData.debet_end = resultSet.getDouble("debet_end");
                osvData.kredit_end = resultSet.getDouble("kredit_end");
                osvData.s_end = resultSet.getDouble("s_end");

            }

            this.osvDataList.add(osvData);
        }
    }
}
