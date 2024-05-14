package organization.registration.system;
import java.util.*;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

	private int user_Id;
	private String user_name;
	private String user_phno;
	private int user_age;
	private String address;
	Scanner sc = new Scanner(System.in);
	Connection cn;
	ResultSet rs;
	Statement stmt;
	String quer;

	public User() {
		user_Id = 0;
		user_name = "";
		user_phno = "";
		user_age = 0;
		address = "";


		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");
			System.out.println("Connected With Database");
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
	}

	public User(int a, String b, String c, int d, String e) {
		user_Id = a;
		user_name = b;
		user_phno = c;
		user_age = d;
		address = e;
	}
	public void addDetails() {
		System.out.println("Enter UserID: ");
		this.user_Id = sc.nextInt();
		System.out.println("Enter User name: ");
		this.user_name = sc.next();
		System.out.println("Enter User Phone Number: ");
		this.user_phno = sc.next();
		System.out.println("Enter the age :");
		this.user_age = sc.nextInt();
		System.out.println("Enter the address: ");
		this.address = sc.next();
		try {
			String quer = "insert into userdetails values(" + user_Id + ",'" + user_name + "','" + this.user_phno + "'," + this.user_age + " , "+ this.address +" );";
			this.stmt.executeUpdate(quer);
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void viewStatus() {
		System.out.println("Enter Company Name : ");
		String k = sc.next();
		try{
			String quer = "select compId,compName,Status from userdetails where compName ="+ k + ";";
			ResultSet rs = this.stmt.executeQuery(quer);
			while(rs.next()) {
				System.out.println("Company Id :" + rs.getInt("compId") + "\n");
				System.out.println("Company Name: " + rs.getString("compName") + "\n");
				int res=rs.getInt("Status");
				if (res == 1 )
					System.out.println("Company Status: Yes");
				else
					System.out.println("Application Rejected");
			}
		}catch(Exception e){System.out.println(e);}
	}

	public int checkRole() {
		int pkk = 0;
		System.out.println("Enter User Id :");
		int r1 = sc.nextInt();
		try {
			String quer = "select count(*) from userdetails where userId =" + r1;
			ResultSet rs = this.stmt.executeQuery(quer);
			while (rs.next()) {
				pkk = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return 1;
        }
}