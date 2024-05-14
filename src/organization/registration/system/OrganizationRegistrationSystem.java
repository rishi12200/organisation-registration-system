/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package organization.registration.system;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Rishi
 */
public class OrganizationRegistrationSystem {


	/**
	 * @param args the command line arguments
	 */
    public static void main(String[] args) {
        int x;
        Connection cn = null;
        ResultSet rs = null;
        Statement stmt = null;
        String query;
        try {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "root");
                stmt = cn.createStatement();
                System.out.println("Connected With Database");
                    } catch (ClassNotFoundException cnfe) {
                System.out.println(cnfe);
                    } catch (SQLException sqle) {
                System.out.println(sqle);
                    }
            int inp, str,choi;
            Scanner in = new Scanner(System.in);
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("Press 1.User 2.Regional Director 3.Exit");
                inp = in.nextInt();
//1 USER 2 Regional Director
                if (inp == 1) {

                do {
                    System.out.println("Press 1.To Register Details 2.View Status 3.Exit");
                    str = in.nextInt();
                    switch (str) {
                        
//INSERTING DETAILS
                        case 1: {
                                System.out.println("Enter UserID: ");
                                int user_Id = sc.nextInt();
                                System.out.println("Enter User name: ");
                                String user_name = sc.next();
                                System.out.println("Enter User Phone Number: ");
                                String user_phno = sc.next();
                                System.out.println("Enter the age :");
                                int user_age = sc.nextInt();
                                System.out.println("Enter the address: ");
                                String address = sc.next();
                                try {
//insert into table(ids) values(" ");
                                    query = "insert into userdetails(userId,userName,userAge,userPhno,address) values(" + user_Id + ",'" + user_name + "'," + user_phno + "," + user_age + ",'" + address + "');";
                                    System.out.println(query);
/*for inserting executeUpdate*/     stmt.executeUpdate(query);
                                    System.out.println("User entry entered");
                                    } catch (Exception e) {
                                    System.out.println(e);
                                    e.printStackTrace();
                                    }
                                    System.out.println("Enter CompanyID: ");
                                    int comp_Id = sc.nextInt();
                                    System.out.println("Enter Company Name: ");
                                    String comp_name = sc.next();
                                    System.out.println("Enter Company Type : ");
                                    String comp_type = sc.next();
                                    System.out.println("Enter No.Of Employee :");
                                    int noofemp = sc.nextInt();
                                    System.out.println("Enter the address : ");
                                    String compaddress = sc.next();
                                    try {
                                        query = "insert into companydetails(compId,compName,compType,noofemp,address) values(" + comp_Id + ",'" + comp_name + "','" + comp_type + "'," + noofemp + ",'" + compaddress + "');";
                                        System.out.println(query);
                                        stmt.executeUpdate(query);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        e.printStackTrace();
                                    }
                        }break;

                        case 2: {
                                    System.out.println("Enter Company Name :");
                                    String k = sc.next();
                                    try {
                                        String quer = "select * from companydetails where compName =\'" + k + "\';";
                                        System.out.println(quer);
                                        rs = stmt.executeQuery(quer);
                                        while (rs.next()) {
                                            System.out.println("Company Id :" + rs.getInt("compId") + "\n");
                                            System.out.println("Company Name: " + rs.getString("compName") + "\n");
                                            int res = rs.getInt("Staus");
                                            if (res == 1)
                                            {
                                                System.out.println("Company Status: Application Accepted");                                                               
                                                System.out.println("DIN number: " + rs.getInt("compdin"));
                                            }       
                                            else
                                            {
                                                System.out.println("Application Rejected");
                                            }
                                        }
                                    } catch (Exception e) {
                                    System.out.println(e);
                                    }
                        }break;
                        
                        case 3: {
                                System.out.println("Exit");
                                break;
                                }
                    }
                } while (str != 3);
                } else if (inp == 2) {
                do
                {
                    System.out.println("Press 1.Display 2.Update Status 3. Issue DIN 4. Exit");
                    choi = sc.nextInt();
                    switch (choi)
                    {
                        case 1: {
                        System.out.println("=======Displaying company details=======");
                        query = "select * from  companydetails";
                        try {
                            rs = stmt.executeQuery(query);
                            while(rs.next())
                            {
                                System.out.println("-----------------------------------------------------");
                                System.out.println("COMPANY ID    : " + rs.getInt("compid"));
                                System.out.println("COMPANY NAME  : " + rs.getString("compname"));
                                System.out.println("COMPANY TYPE  : " + rs.getString("comptype"));
                                System.out.println("NO_OF_EMPLOYEE: " + rs.getInt("noofemp"));
                                System.out.println("ADDRESS : " + rs.getString("address"));
                                System.out.println("STATUS  : " + rs.getInt("staus"));
                                System.out.println("DIN NUMBER :"+ rs.getInt("compdin"));
                                System.out.println("-----------------------------------------------------");
                            }
                        } catch (Exception e) {
                        System.out.println(e);
                            }
                        }
                        break;
                        
                        case 2 : {
                        //update  status
                                    try {
                                        int res=1;  
                                        query = "update companydetails set staus="+res+" where address IS NOT NULL;";
                                        stmt.executeUpdate(query);
                                        System.out.println("Updated Successfully");
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        }
                                        
                        }
                        break;

                        case 3:
                        {
                        //issue din
                            System.out.println("Enter Company name : ");
                            String comp_name = sc.next();
                            System.out.println("Enter DIN number :");
                            int din = sc.nextInt();
                            try {
                                query = "update companydetails set compdin = "+din+" where compName ='"+ comp_name+"'";
                                System.out.println(query);
                                stmt.executeUpdate(query);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }break;
                        case 4: System.out.println("Exitted");break;
                    }
                }while(choi!=4);
                }   
            }while(inp != 3 );
    }
}



