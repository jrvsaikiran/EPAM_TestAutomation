package Marriage.pdfExcelImg;

import Marriage.Bharath.ProfileData;

import java.sql.*;
import java.util.List;

public class BaseMysql {

    private static Connection con;

//    public static void main(String[] arg) {
//        mysqlDb();
//        insert();
//    }

    public  void insert(List<ProfileData> list) {
        ProfileData profileData = list.get(0);

        try {
            if (con == null) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jrvdb", "root", "rootpassword");
                System.out.println(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Statement statement = con.createStatement();
            //            String insert = """
//                  INSERT INTO `jrvdb`.`bharathprofiles` (`name`, `age`, `cast`, `education`, `location`, `activity`)
//                VALUES ('raja1', '89', 'yad', 'mth', 'ggj', 'yaa');
//                  \s""";

            String insert = "INSERT INTO" + " `jrvdb`.`bharathprofiles`" +
                    "(`name`, `age`, `cast`, `education`, `location`, `activity`)" +
                    " VALUES " + "(" + "'" + profileData.getName().trim() + "', " + "'" + profileData.getAge().trim() + "', " +
                    "'" + profileData.getCast().trim() + "', " + "'" + profileData.getEducation().trim() + "', " +
                    "'" + profileData.getLocation().trim() + "', " + "'" + profileData.getActivity().trim() + "'); ";
            int i = statement.executeUpdate(insert);
            System.out.println("Successfully Inserted " + i + " records");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void mysqlDb() {
        try {
            if (con == null) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jrvdb", "root", "rootpassword");
                System.out.println(con);
            }
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM jrvdb.bharathprofiles;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
