package jdbc;

import main.DataBean;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class TestJDBC {

    String url = "jdbc:informix-sqli://10.247.12.31:1525/forms:INFORMIXSERVER=ids_delta_1";
    Connection connection;
    String firmName = "не задано";
    long nomDog;
    ArrayList<DataBean> dataBeanList;

    public TestJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.informix.jdbc.IfxDriver");
        this.connection = DriverManager.getConnection(url, "prog686", "111111");
    }

    public ArrayList getArrayCustCode() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select  distinct cust_code from ratsg:a_rash where priznak in (1,2,3)");
        ArrayList<Integer> arrayCustCode = new ArrayList<Integer>();
        while (resultSet.next()) {
            arrayCustCode.add(resultSet.getInt("cust_code"));
        }
        return arrayCustCode;
    }

    public void setFirmName(Integer custCode) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT a5.firm_name,a2.nom_dog_ob " +
                "  FROM ratsg:a2 a2 " +
                "  LEFT JOIN ratsg:a5f a5 ON a5.name_code=a2.name_code " +
                "  WHERE a2.ab_code = ? ");
        statement.setInt(1, custCode);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            firmName = resultSet.getString("firm_name");
            nomDog = resultSet.getLong(2);
        }
    }

    public ArrayList<DataBean> getDataBeanList(Integer custCode) throws SQLException, ParseException {
        PreparedStatement statement = connection.prepareStatement("SELECT f.phone, f.serv_code,  " +
                "CASE " +
                "WHEN l.name  is NULL THEN 'МЕСТНЫЕ РАЗГОВОРЫ' " +
                "ELSE l.name END AS name, " +
                "f.datar,LPAD(f.vremya,6,'0') AS vremya,f.tel_b,f.prod,f.summa " +
                "FROM forms:f16 f " +
                "LEFT JOIN rater:locations l ON f.zone_code =l.location_id " +
                "WHERE cust_code = ? AND priz_r = 'e'");
        statement.setInt(1, custCode);
        ResultSet resultSet = statement.executeQuery();
        dataBeanList = new ArrayList<DataBean>();
        Float summa;
        String vremya, time;
        while (resultSet.next()) {
            vremya = resultSet.getString("vremya");
            time = vremya.substring(0, 2) + ":" + vremya.substring(2, 4);
            summa = resultSet.getFloat("summa") / 10000;
            dataBeanList.add(new DataBean(resultSet.getString("phone"),
                    resultSet.getString("name"),
                    (resultSet.getInt("serv_code") != 412 ? resultSet.getString("datar") : " "),
                    (resultSet.getInt("serv_code") != 412 ? time : " "),
                    (resultSet.getInt("serv_code") != 412 ? resultSet.getString("tel_b") : " "),
                    resultSet.getInt("prod"),
                    summa));
        }
        return dataBeanList;
    }

    public String getFirmName() {
        return firmName;
    }

    public long getNomDog() {
        return nomDog;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
