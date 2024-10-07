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
    private String insert;

    public void insert(LinkedHashMap<Integer, List<CustomerData>> map, int i, Parameters par) {

        List<CustomerData> customerData = map.get(i);
        data = customerData.get(0);

        name = getTrim(data.getName().trim());
        String age = getTrim(data.getAge().trim());

        String education = getTrim(data.getEducation().trim());
//        String education;
//        if (data.getEducation().contains("'") || data.getEducation().contains("\"")) {
//            education = data.getEducation().replaceAll("['|\"]","").trim();
//        }else {
//            education = data.getEducation().trim();
//        }

        String location = getTrim(data.getLocation().trim());
        String activity = getTrim(data.getActivity().trim());
        String profileNumber = getTrim(data.getProfileNumber().trim());
        String cast = getTrim(data.getFinalCast().trim());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void yadavDataBaseInsert(String age, String education, String location, String activity, String profileNumber, String cast, String date, Statement statement) throws Exception {
        try {
            String insert = "INSERT INTO" + " `jrvdb`.`yadavprofiles`" +
                    "(`name`, `age`, `education`, `location`, `activity`, `profilenumber`, `cast`,`date`)" +
                    " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                    "'" + education + "', " + "'" + location + "', " +
                    "'" + activity + "', " + "'" + profileNumber + "', " + "'" + cast + "', " + "'" + date + "'); ";

            statement.executeUpdate(insert);

        } catch (Exception e) {
            throw new Exception("unable to insert db query ---->>>>"+insert+"---->>>"+e.getLocalizedMessage());
        }
        System.out.println("Successfully Inserted " + record + " records");
        record++;
    }

    private void mixedDataBaseInsert(String age, String education, String location, String activity, String profileNumber, String cast, String date, Statement statement) throws Exception {
        try {
             insert = "INSERT INTO" + " `jrvdb`.`mixedprofiles`" +
                    "(`name`, `age`, `education`, `location`, `activity`, `profilenumber`, `cast`,`date`)" +
                    " VALUES " + "(" + "'" + name + "', " + "'" + age + "', " +
                    "'" + education + "', " + "'" + location + "', " +
                    "'" + activity + "', " + "'" + profileNumber + "', " + "'" + cast + "', " + "'" + date + "'); ";

            statement.executeUpdate(insert);

        } catch (Exception e) {
            throw new Exception("unable to insert db query ---->>>>"+insert+"---->>>"+e.getLocalizedMessage());
        }
        System.out.println("Successfully Inserted " + record + " records");
        record++;
    }


    private  String getTrim(String str) {

        try {
            if(str.contains("'")) {
                return str.replaceAll("'","").trim();
            }else {
                return str.trim();
            }
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
        return s1 + " " + s2;
    }
}
