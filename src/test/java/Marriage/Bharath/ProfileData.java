package Marriage.Bharath;

import lombok.Data;

@Data
public class ProfileData {

    String name;
    String age;
    String cast;
    String education;
    String location;
    String activity;

    public ProfileData(String name, String age, String cast, String education, String location, String activity) {
        this.name = name;
        this.age = age;
        this.cast = cast;
        this.education = education;
        this.location = location;
        this.activity = activity;
    }
}
