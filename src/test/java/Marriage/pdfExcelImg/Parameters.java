package Marriage.pdfExcelImg;

import lombok.Data;

@Data
public class Parameters {
    public String jenkins;
    public String prime;
    public String tab;
    public String testngXml;

//    public Parameters(String jenkins,String prime,String tab, String testngXml){
//        this.jenkins=jenkins;
//        this.prime=prime;
//        this.tab=tab;
//        this.testngXml=testngXml;
//    }


    public void getParameters() {
        setJenkins(System.getProperty("jenkins"));
        setPrime(System.getProperty("prime"));
        setTab(System.getProperty("tab"));
        setTestngXml(System.getProperty("testng.xml"));


        System.out.println("Tab -------->" + getTab());
        System.out.println("Prime ------>" + getPrime());
        System.out.println("Jenkins ---->" + getJenkins());
        System.out.println("Testng.xml ->" + getTestngXml());
    }


}
