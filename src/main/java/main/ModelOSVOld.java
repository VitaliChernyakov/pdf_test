package main;

import java.sql.*;
import java.util.ArrayList;

public class ModelOSVOld {


    OSVDataOld osvData = new OSVDataOld();
    public ArrayList<Integer> codeAdmList = new ArrayList<Integer>();
    String url = "jdbc:informix-sqli://10.247.12.31:1525/ratsg:INFORMIXSERVER=ids_delta_1";
    Connection connection;

    public ModelOSVOld() throws ClassNotFoundException, SQLException {
        Class.forName("com.informix.jdbc.IfxDriver");
        this.connection = DriverManager.getConnection(url, "prog686", "111111");
        this.codeAdmList.add(10);
    }

    public ArrayList<Integer> getCodeAdmList() {
        return codeAdmList;
    }

    public void setCodeAdmList() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select code_adm_list from ratsg:a15 where oper_user = 'cont528'");



        statement.executeUpdate("select  distinct serv_class from ratsg:oper_stor where " +
                "flag_usl=0 and cancel_flag = 0 and vat_rate=25 " +
                "group by 1 " +
                "into temp t_class_25; " +
                "select  distinct serv_class from ratsg:oper_stor where " +
                "flag_usl=0 and cancel_flag = 0 and vat_rate=20 " +
                "group by 1 " +
                "into temp t_class_20; " +
                "select serv_code, time_usl from sprav:c1_03 " +
                "where serv_code=503 and time_usl in " +
                "(select koda from ratsg:ims where pro=4) " +
                "into temp t_ims_psis_20; ");

        statement.executeUpdate("select distinct serv_class from ratsg:oper_stor where " +
                "flag_usl in (1,3,4) and cancel_flag = 0  and serv_class not in ( 688,689) " +
                "group by 1 " +
                "into temp t_class_storon;");


//        графа 3
        resultSet = statement.executeQuery("select sum(saldo_beg) as saldoBegC10 from sprav:c10r_03 " +
                "where saldo_beg < 0 and serv_class not in ( 9, 216) and substr(cust_code,0, 2)= 10");

        if (resultSet.next()) {
            osvData.saldoBegC10 = resultSet.getDouble("saldoBegC10");
            System.out.println(osvData.saldoBegC10);
        }
        //        графа 4
        resultSet = statement.executeQuery("select sum(saldo_beg) as saldoBegC10r from sprav:c10_03 " +
                "where saldo_beg > 0 and substr(cust_code,0, 2)= 10");

        if (resultSet.next()) {
            osvData.saldoBegC10r = resultSet.getDouble("saldoBegC10r");
            System.out.println(osvData.saldoBegC10r);
        }

                //        графа 9
        resultSet = statement.executeQuery("select sum(summa) as vozmOborud from  sprav:c1_03 " +
                "where serv_code in ( select distinct serv_id_code from ratsg:a10 where serv_class in ( 688, 689) and  distr_flag  not in (1,2)) " +
                "and substr(cust_code,0, 2)= 10");

        if (resultSet.next()) {
            osvData.vozmOborud = resultSet.getDouble("vozmOborud");
            System.out.println(osvData.vozmOborud);
        }
        //        графа 10
        resultSet = statement.executeQuery("select sum(summa) as storUsl from  sprav:c1_03 c " +
                "where serv_code in ( select serv_id_code from ratsg:a10 where serv_class in ( select serv_class from t_class_storon) and  distr_flag not in (1,2,4,1024,64,2048,32768,65536) " +
                "and serv_class not in (9,216) and serv_code not in ( 885,995,410,880,518,524,11014) and substr(c.cust_code,0, 2)= 10  )");

        if (resultSet.next()) {
            osvData.storUsl = resultSet.getDouble("storUsl");
            System.out.println(osvData.storUsl);
        }

        //        графа 11
        resultSet = statement.executeQuery("select sum(summa) as vozmOborud from  sprav:c1_03 " +
                "where serv_code in ( select distinct serv_id_code from ratsg:a10 where serv_class in ( 688, 689) and  distr_flag  not in (1,2)) " +
                "and substr(cust_code,0, 2)= 10");

        if (resultSet.next()) {
            osvData.vozmOborud = resultSet.getDouble("vozmOborud");
            System.out.println(osvData.vozmOborud);
        }

        //        графа 15
        resultSet = statement.executeQuery("select sum(summa) as realizProcheeVozm " +
                "from  sprav:c1_03 " +
                "where substr(cust_code,0, 2)= 10 and " +
                "serv_code in ( select serv_id_code from ratsg:a10 where serv_class in ( 688, 689) and  distr_flag in (1,2));");
        if (resultSet.next()) {
            osvData.realizProcheeVozm = resultSet.getDouble("realizProcheeVozm");
            System.out.println(osvData.realizProcheeVozm);
        }
        //        графа 16
        resultSet = statement.executeQuery("select sum(summa) as realizProcheeStor " +
                "from  sprav:c1_03 c " +
                "where serv_code in ( select serv_id_code from ratsg:a10 where serv_class in ( select serv_class from t_class_storon) and  distr_flag in (1,2)\n" +
                "and serv_class not in (9,216) and serv_code not in ( 885,995,410,880,518,524,11014) and substr(c.cust_code,0, 2)= 10  );");
        if (resultSet.next()) {
            osvData.realizProcheeStor = resultSet.getDouble("realizProcheeStor");
            System.out.println(osvData.realizProcheeStor);
        }

        //        графа 17
        resultSet = statement.executeQuery("select sum(pay_r) as platAll " +
                "from sprav:c9 where substr(cust_code,0, 2)= 10 " +
                "and period_date = '2020-03-01' and pay_flag in (1,2,8,9,10);");
        if (resultSet.next()) {
            osvData.platAll = resultSet.getDouble("platAll");
            System.out.println(osvData.platAll);
        }

        //        графа 18
        resultSet = statement.executeQuery("select sum(sprav:c9r.pay_r) as platStor " +
                "from sprav:c9r " +
                "left join sprav:c9 " +
                "on sprav:c9.serial_num=sprav:c9r.serial_num    " +
                "and substr(sprav:c9r.cust_code,0, 2)= 10 " +
                "and sprav:c9.period_date = '2020-03-01' and sprav:c9r.period_date = '2020-03-01' and sprav:c9.pay_flag  in (1,2,8,9,10 ) " +
                "and sprav:c9r.serv_class in ( select serv_class from t_class_storon);");
        if (resultSet.next()) {
            osvData.platStor = resultSet.getDouble("platStor");
            System.out.println(osvData.platStor);
        }

        //        графа 21
        resultSet = statement.executeQuery("select sum(summa) as storUslKorr " +
                "from sprav:c1_03 c " +
                "join ratsg:a10 a " +
                "on c.serv_code=serv_id_code and distr_flag in (32768,65536) " +
                "and  substr(c.cust_code,0, 2)= 10 " +
                "and a.date_mod=( select max(w.date_mod) from ratsg:a10 w where w.serv_id_code=a.serv_id_code ) " +
                "and serv_class in (select serv_class from t_class_storon) ");
        if (resultSet.next()) {
            osvData.storUslKorr = resultSet.getDouble("storUslKorr");
            System.out.println(osvData.storUslKorr);
        }

        //        графа 22
        resultSet = statement.executeQuery("select sum(saldo_end) as saldoEndC10r " +
                "from  sprav:c10_03 " +
                "where saldo_beg > 0 and substr(cust_code,0, 2)= 10 ;");
        if (resultSet.next()) {
            osvData.saldoEndC10r = resultSet.getDouble("saldoEndC10r");
            System.out.println(osvData.saldoEndC10r);
        }


        //        графа 23
        resultSet = statement.executeQuery("select sum(saldo_end) as saldoEndC10 " +
                "from  sprav:c10_03 " +
                "where saldo_beg > 0 and substr(cust_code,0, 2)= 10 ;");

        if (resultSet.next()) {
            osvData.saldoEndC10 = resultSet.getDouble("saldoEndC10");
            System.out.println(osvData.saldoEndC10);
        }





    }
}
