package Marriage.pdfExcelImg;

import Marriage.Bharath.ProfileData;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BaseMysql {

    private static Connection con;
    private static int i=1;
    private String name;

//    public static void main(String[] arg) {
//        mysqlDb();
//        insert();
//    }

    public  void insert(List<ProfileData> list) {
        ProfileData profileData = list.get(0);
         name = profileData.getName().trim().replaceAll("[^a-zA-Z0-9]+", "").trim();
        String age = profileData.getAge().trim().replaceAll("[^a-zA-Z0-9]+","").trim();
        String cast = profileData.getCast().trim().replaceAll("[^a-zA-Z0-9]+","").trim();
        String education = profileData.getEducation().trim().replaceAll("[^a-zA-Z0-9]+","").trim();
        String location = profileData.getLocation().trim().replaceAll("[^a-zA-Z0-9]+","").trim();
        String activity = profileData.getActivity().trim().replaceAll("[^a-zA-Z0-9]+","").trim();
        String date = timeMethod();

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
                    "(`name`, `age`, `cast`, `education`, `location`, `activity`, `date`)" +
                    " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                    "'" +cast + "', " + "'" + education+ "', " +
                    "'" + location + "', " + "'" + activity + "', " + "'" + date + "'); ";
            String insert1 = "INSERT INTO" + " `jrvdb`.`bharathprofiles`" +
                    "(`name`)" +
                    " VALUES " + "(" + "'" + profileData.getName().trim() +"'"+");";

            try {
                int record = statement.executeUpdate(insert);
                System.out.println("Successfully Inserted " + record + " records");
            } catch (Exception e) {

                name = name + i;
                String insert2 = "INSERT INTO" + " `jrvdb`.`bharathprofiles`" +
                        "(`name`, `age`, `cast`, `education`, `location`, `activity`)" +
                        " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                        "'" +cast + "', " + "'" + education+ "', " +
                        "'" + location + "', " + "'" + activity + "', " + "'" + date + "'); ";
                int record = statement.executeUpdate(insert2);
                System.out.println("Successfully Inserted " + record + " records");
                i++;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String timeMethod() {
        LocalDate date = LocalDate.now();
        String s1 = String.valueOf(date);

        LocalTime time = LocalTime.now();
        String s = String.valueOf(time);
        String[] split = s.split("\\.");
        String s2 = split[0].replace(":", "-");
        return s1 +" "+ s2;
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
