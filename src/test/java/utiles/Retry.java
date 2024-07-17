package utiles;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int i=0,j=3;

    @Override
    public boolean retry(ITestResult result) {
        if (i<j) {
            i++;
            return true;
        }
        return false;
    }
}
