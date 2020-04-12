package data;

import utilities.Xls_Reader;

public class DataFile {

	Xls_Reader d = new Xls_Reader("C:\\testing\\NikulTest.xlsx"); // call this constructor from xls_reader.java file and
																	// put it to utilities and add 5 jar files

	// login test
	public String validEmail = d.getCellData("Data1", "UserName", 2);
	public String invalidEmail = d.getCellData("Data1", "UserName", 3);
	public String wrongPassword = d.getCellData("Data1", "Password", 2);
	public String emailError = d.getCellData("Data1", "Email Error", 2);
	public String passwordError = d.getCellData("Data1", "Password Error", 2);

}
