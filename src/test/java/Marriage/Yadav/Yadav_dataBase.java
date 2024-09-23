package Marriage.Yadav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;

public class Yadav_dataBase {
    private static Connection con;
    private static CustomerData data;
    private static int record = 1;
    private String name;

    public void insert(LinkedHashMap<Integer, List<CustomerData>> map, int i) {

        List<CustomerData> customerData = map.get(i);
        data = customerData.get(0);

        name = data.getName().trim();
        String age = data.getAge().replaceAll("[^a-zA-Z0-9]+", "").trim();
        String education = data.getEducation().replaceAll("[^a-zA-Z0-9]+", "").trim();
        String location = data.getLocation().replaceAll("[^a-zA-Z0-9]+", "").trim();
        String activity = data.getActivity().replaceAll("[^a-zA-Z0-9]+", "").trim();
        String profileNumber = data.getProfileNumber().replaceAll("[^a-zA-Z0-9]+", "").trim();
        String cast = data.getFinalCast().replaceAll("[^a-zA-Z0-9]+", "").trim();
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
                String insert = "INSERT INTO" + " `jrvdb`.`yadavprofiles`" +
                        "(`name`, `age`, `education`, `location`, `activity`, `profilenumber`, `cast`,`date`)" +
                        " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                        "'" + education + "', " + "'" + location + "', " +
                        "'" + activity + "', " + "'" + profileNumber + "', " + "'" + cast + "', " + "'" + date + "'); ";

                statement.executeUpdate(insert);

            } catch (Exception e) {
                name = name + date;
                String insert2 = "INSERT INTO" + " `jrvdb`.`yadavprofiles`" +
                        "(`name`, `age`, `education`, `location`, `activity`, `profilenumber`, `cast`,`date`)" +
                        " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                        "'" + education + "', " + "'" + location + "', " +
                        "'" + activity + "', " + "'" + profileNumber + "', " + "'" + cast + "', " + "'" + date + "'); ";

                statement.executeUpdate(insert2);
            }
            System.out.println("Successfully Inserted " + record + " records");
            record++;


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
        return s1 + " " + s2;
    }
}
