package JenkinsAutoRun.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class QualitativeFactor {
	JenkinsAutoRun.util.Xls_Reader reader=new JenkinsAutoRun.util.Xls_Reader("E:\\SeleniumSample\\JenkinsAutoRun\\TestData\\QualitativeFactor.xlsx");
	String testName="QualitativeFactor";
	ExtentReports ex;
	ExtentTest test;

	@Test(dataProvider="getdata")
	public void testing(Hashtable<String,String> data) throws IOException, InterruptedException, AWTException{
		
		ex=JenkinsAutoRun.util.ExtentManager.getInstance();
		test=ex.startTest(testName);
		test.log(LogStatus.INFO, "Starting test"+testName);
		
		if(JenkinsAutoRun.util.DataUtil.isSkip(reader, testName) || data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test because runmode N");
			throw new SkipException("Skipping the test");
		}
		
		JenkinsAutoRun.Keywords app=new JenkinsAutoRun.Keywords();
		app.test1(reader,testName,test,data);
	}	
		
	@AfterTest
	public void runeverytest(){
		if(ex!=null){
		ex.endTest(test);
		ex.flush();
		}
	}
		
	
	@DataProvider
	public Object[][] getdata(){
		return JenkinsAutoRun.util.DataUtil.getdata(reader, testName);
	}
		
	
}
