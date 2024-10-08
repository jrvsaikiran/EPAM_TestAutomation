package Marriage.Bharath;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Bharath_dataBase {

    private static Connection con;
    private static int i=1;
    private String age;
    private String insert;

    public  void insert(List<ProfileData> list) {
        ProfileData profileData = list.get(0);
         String name = profileData.getName().trim();

         if(profileData.getAge().contains("'")){
              age = profileData.getAge().replaceAll("[']","ft ").trim();
         }
        if(age.contains("\"")){
            age = age.replaceAll("\"","in");
        }

        String cast = profileData.getCast().trim();
        String education = profileData.getEducation().replaceAll("[\\t\\n\\r]+","").trim();
        String location = profileData.getLocation().trim();
        String activity = profileData.getActivity().trim();
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

            try {
                 insert = "INSERT INTO" + " `jrvdb`.`bharathprofiles`" +
                        "(`name`, `age`, `cast`, `education`, `location`, `activity`, `date`)" +
                        " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                        "'" +cast + "', " + "'" + education+ "', " +
                        "'" + location + "', " + "'" + activity + "', " + "'" + date + "'); ";

                 statement.executeUpdate(insert);

            } catch (Exception e) {
                throw new Exception("unable to insert db query ---->>>>"+insert+"---->>>"+e.getLocalizedMessage());
            }
                System.out.println("Successfully Inserted " + i + " records");
                i++;
        } catch (Exception e) {
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
