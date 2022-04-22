package Study2;

import java.util.*;
import java.sql.*;
//import java.security.PublicKey;
//import java.sql.*;
class Login{
	String first,last,mail,pass;
	Connection con=null;
	void registered() throws SQLException{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Pass@123");
        PreparedStatement ps = con.prepareStatement("insert into login values(?,?,?,?)");
        Scanner s=new Scanner(System.in);
        
        System.out.println("Enter the First Name:");
        first = s.next();
        System.out.println("Enter the Last Name:");
        last= s.next();
        System.out.println("Enter Mail ID:");
        mail = s.next();
        System.out.println("Enter the Password:");
        pass = s.next();
        ps.setString(1, first);
        ps.setString(2, last);
        ps.setString(3,mail);
        ps.setString(4, pass);
        ps.execute();
        System.out.println("Hi "+first+" you have registered successfully");
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	boolean logged()throws SQLException
	{
		boolean bool = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Pass@123");
	        Scanner sc1 = new Scanner(System.in);
	        System.out.println("Enter E-Mail ID");
	        String name = sc1.next();
	        System.out.println("Enter the password");
	        String password = sc1.next();
	        PreparedStatement ps = con.prepareStatement("select * from login");
	        ResultSet rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	String a = rs.getString(3);
	        	String x = rs.getString(4);
	        	if(a.equals(name) && x.equals(password))
	        	{
	        		bool = true;
	        		return bool;
	        		
	        	}		
	        	
	        }
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			con.close();
		}
		return bool;
		
	}
}
class Cust{
	long cif;
	String name;
	long acc;
	long phone;
	long ifsc;
	long balance;
	Connection con=null;
	
	
	void register(){
		try {
	Scanner s=new Scanner(System.in);
	System.out.println("Enter CIF");
	cif=s.nextLong();
	
	System.out.println("Enter name");
	name=s.next();
	
	System.out.println("Enter Account");
	acc=s.nextLong();
	
	System.out.println("Enter Phone");
	phone=s.nextLong();
	
	System.out.println("Enter IFSC");
	ifsc=s.nextLong();
	
	System.out.println("Enter Bank Balance");
	balance=s.nextLong();
	
	
		 Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Pass@123");
         PreparedStatement ps = con.prepareStatement("insert into customer values(?,?,?,?,?,?)");
         
         ps.setFloat(1, cif);
         ps.setString(2, name);
         ps.setFloat(3, acc);
         ps.setFloat(4, phone);
         ps.setFloat(5, ifsc);
         ps.setFloat(6, balance);
         
         ps.execute();
         System.out.println("Data inserted.....");
         
	}
	catch(Exception e) {
		System.out.println(e);
	}
		
	
	}
	void balance() throws SQLException {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Pass@123");
	         PreparedStatement pstmt=con.prepareStatement("select * from customer where account=? or phone=?");
	         Scanner sc=new Scanner(System.in);
	        
	         System.out.println("Enter the account or phone no");
	         long c = sc.nextInt();
	         pstmt.setLong(1,c);
	         pstmt.setLong(2,c);
	         ResultSet rs = pstmt.executeQuery();
	         
	       
//	         Statement stmt=con.createStatement();
//	         ResultSet rSet=stmt.executeQuery("select * from customer where account=? or phone=?");
	         
	         while(rs.next()) {
	        	 System.out.println(rs.getFloat(1)+"    "+rs.getString(2)+"       "+rs.getFloat(6));
	         }
	         System.out.println("Balance shown");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	void transfer() throws SQLException{
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Pass@123");
	         Scanner s=new Scanner(System.in);
	         System.out.println("Choose tranfer using 1 - Account, 2-Phone");
	         int x=s.nextInt();
	         if(x==1) {
	        	 PreparedStatement pstmt=con.prepareStatement("update customer set balance = balance + ? where account=?");
	        	 Scanner s1=new Scanner(System.in);
	        	 System.out.println("Enter the receiver account number");
	        	 acc=s1.nextLong();
	        	 System.out.println("Enter amount");
	        	 balance=s1.nextLong();
	        	 
	        	 pstmt.setLong(1,balance);
	        	 pstmt.setLong(2,acc);
	        	 
	        	 pstmt.execute();
	        	 
	        	 PreparedStatement pstmt2 = con.prepareStatement("update customer set balance = balance - ? where cif=1");
	        	 pstmt2.setLong(1, balance);
	        	// System.out.println("balance after transfer - "+);
	        	 pstmt2.execute();
	        	 //this.balance();
	         }
		} 
		catch(Exception e) {
			System.out.println(e);
		}
	}

}


public class Bank {
	public static void main(String[] args) throws SQLException {
		Cust cust =new Cust();
		Login l=new Login();
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("---------YOU'RE WELCOME TO LENA BANK-----------");
			System.out.println(" 1. Register  ");
			System.out.println(" 2. Login  ");
			System.out.println(" 3. Exit ");
			System.out.println("Enter the Choice");
			int ch = sc.nextInt();
			if (ch==1)
			{
				l.registered();
			}
			else if(ch==2) {
				if(l.logged()==true) {
					do {
						System.out.println("1.Register");
						System.out.println("2.Balance");
						System.out.println("3.Transfer");
						System.out.println("4.Exit");
						//System.out.println("Enter the choice");
						int ch1 = sc.nextInt();
						if(ch1==1)
						{
							cust.register();
						}
						else if(ch1==2)
						{
							cust.balance();
						}
						else if(ch1==3)
						{
							cust.transfer();
						}
						else
						{
							System.exit(0);
						}

					}while(true);
				}
				else
				{
					System.out.println("You're not registered, please REGISTER to proceed");
					System.out.println();
				}

			}else
			{
				System.exit(0);
			}
			



		}while(true);
	}
}


