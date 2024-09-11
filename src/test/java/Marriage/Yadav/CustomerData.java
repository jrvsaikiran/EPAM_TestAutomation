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
    public String finalCast;

    public CustomerData(String name, String age, String education, String location, String activity, String profileNumber, String finalCast){
        this.name=name;
        this.age=age;
        this.education=education;
        this.location=location;
        this.activity=activity;
        this.profileNumber=profileNumber;
        this.finalCast=finalCast;
    }
}
