package Marriage.Yadav;

import Marriage.pdfExcelImg.Parameters;

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

    public void insert(LinkedHashMap<Integer, List<CustomerData>> map, int i, Parameters par) {

        List<CustomerData> customerData = map.get(i);
        data = customerData.get(0);

        name = data.getName().trim();
        String age = data.getAge().trim();

        String education;
        if (data.getEducation().contains("'") || data.getEducation().contains("\"")) {
            education = data.getEducation().replaceAll("['|\"]","").trim();
        }else {
            education = data.getEducation().trim();
        }

        String location = data.getLocation().trim();
        String activity = data.getActivity().trim();
        String profileNumber = data.getProfileNumber().trim();
        String cast = data.getFinalCast().trim();
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

            if(par.getTestngXml().equalsIgnoreCase("yadav.xml")){
                yadavDataBaseInsert(age, education, location, activity, profileNumber, cast, date, statement);

            } else if (par.getTestngXml().equalsIgnoreCase("mixed.xml")) {
                mixedDataBaseInsert(age, education, location, activity, profileNumber, cast, date, statement);
            }else {
                throw new RuntimeException("Unsupported testng xml"+par.getTestngXml());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void yadavDataBaseInsert(String age, String education, String location, String activity, String profileNumber, String cast, String date, Statement statement) throws SQLException {
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
    }

    private void mixedDataBaseInsert(String age, String education, String location, String activity, String profileNumber, String cast, String date, Statement statement) throws SQLException {
        try {
            String insert = "INSERT INTO" + " `jrvdb`.`mixedprofiles`" +
                    "(`name`, `age`, `education`, `location`, `activity`, `profilenumber`, `cast`,`date`)" +
                    " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                    "'" + education + "', " + "'" + location + "', " +
                    "'" + activity + "', " + "'" + profileNumber + "', " + "'" + cast + "', " + "'" + date + "'); ";

            statement.executeUpdate(insert);

        } catch (Exception e) {
            name = name + date;
            String insert2 = "INSERT INTO" + " `jrvdb`.`mixedprofiles`" +
                    "(`name`, `age`, `education`, `location`, `activity`, `profilenumber`, `cast`,`date`)" +
                    " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                    "'" + education + "', " + "'" + location + "', " +
                    "'" + activity + "', " + "'" + profileNumber + "', " + "'" + cast + "', " + "'" + date + "'); ";

            statement.executeUpdate(insert2);
        }
        System.out.println("Successfully Inserted " + record + " records");
        record++;
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
