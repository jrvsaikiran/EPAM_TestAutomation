package Marriage.Yadav;

import lombok.Data;

@Data
public class CustomerData {
    public String name;
    public String age;
    public String education;
    public String location;
    public String activity;
    public String profileNumber;

    public CustomerData(String name, String age, String education, String location, String activity, String profileNumber){
        this.name=name;
        this.age=age;
        this.education=education;
        this.location=location;
        this.activity=activity;
        this.profileNumber=profileNumber;
    }
}
