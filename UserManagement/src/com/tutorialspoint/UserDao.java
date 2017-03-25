package com.tutorialspoint;

 

import java.io.BufferedReader;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  

class StreamGobbler extends Thread {
    InputStream is;
 
    StreamGobbler(InputStream is) {
        this.is = is;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
                System.out.println(line);    
        } catch (IOException ioe) {
            ioe.printStackTrace();  
        }
    }
}

public class UserDao { 	
   public List<User> getAllUsers(){ 
      
      List<User> userList = null; 
      
      try { 
         File file = new File("/home/shashi/Users.dat"); 
         if (!file.exists()) { 
            User user = new User(""+1, "Mahesh", "Teacher"); 
            userList = new ArrayList<User>(); 
            userList.add(user); 
            saveUserList(userList); 
         } 
         else{ 
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            userList = (List<User>) ois.readObject(); 
            ois.close(); 
         } 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } catch (ClassNotFoundException e) { 
         e.printStackTrace(); 
      }   
      return userList; 
   } 
   
   
   private void saveUserList(List<User> userList){ 
      try { 
         File file = new File("/home/shashi/Users.dat"); 
         FileOutputStream fos;  
         fos = new FileOutputStream(file); 
         ObjectOutputStream oos = new ObjectOutputStream(fos); 
         oos.writeObject(userList); 
         oos.close(); 
      } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
   } 
   
   
  
   
   
public List<User> getTweets(String timestamp) {
	// TODO Auto-generated method stub
	
	
	List<User> al = new ArrayList<User>();
	try
	{
	Runtime rt = Runtime.getRuntime();
	Process proc = rt.exec(" sh /home/shashi/finalscript1.sh   "+timestamp);
	//output both stdout and stderr data from proc to stdout of this process
	StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
	StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());
	errorGobbler.start();
	outputGobbler.start();
	proc.waitFor();  

	}
	catch(Exception e1)
	{
		
		System.out.println(" calling web service1 execption ");
	}
	

		
		BufferedReader  bfr =null;
				
		try
		{
			
	   File F1= new File("/home/shashi/output1");
		System.out.println(F1.exists());
			
	    bfr  = new  BufferedReader(new FileReader(F1));
	    
		String  str="";
		while((str=bfr.readLine())!=null)
		{	
			if(str.trim().length()!=0)
			{
			//System.out.println(str);
				
			  String [] arr =str.split("\\|");
			  System.out.println(arr[0]);
			  User u= new User();
			  u.setId((arr[0]));
			  System.out.println("------------------------");
			  System.out.println(arr[1]);
			  u.setTweets(arr[1]);
			  
             al.add(u);
             
			  
			}
		}
		
		bfr.close();
	}
	catch(Exception e1)
	{
		System.out.println(" calling web service1  3 execption ");
		e1.printStackTrace();
		
	}
	
		finally {
			try
			{
			if(bfr!=null)
				 bfr.close();
			}
			catch(Exception e2){
				
				System.out.println(" calling web service1  4 execption ");
				
				e2.printStackTrace();
				
			}
		}
 
	return al;
}
   


public User getOutput(String userid) {
	

User u =null;
	try
	{
	Runtime rt = Runtime.getRuntime();
	Process proc = rt.exec(" sh /home/shashi/finalscript2.sh   "+userid);
	//output both stdout and stderr data from proc to stdout of this process
	StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
	StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());
	errorGobbler.start();
	outputGobbler.start();
	proc.waitFor();  

	}
	catch(Exception e1)
	{
		
		System.out.println(" calling 2 web service1 execption ");
	}
	

		
		BufferedReader  bfr =null;
				
		try
		{
			
	   File F1= new File("/home/shashi/output2");
		System.out.println(F1.exists());
			
	    bfr  = new  BufferedReader(new FileReader(F1));
	    
		String  str="";
		while((str=bfr.readLine())!=null)
		{	
			if(str.trim().length()!=0)
			{
			//System.out.println(str);
				
			  String [] arr =str.split("\\|");
			  System.out.println(arr[0]);
			   u= new User();
			  u.setId((arr[0]));
			  System.out.println("------------------------");
			  System.out.println(arr[1]);
			  u.setTweetcount(Integer.parseInt(arr[1].trim()));
             
			  
			}
		}
		
		bfr.close();
	}
	catch(Exception e1)
	{
		System.out.println(" calling  2 web service1  3 execption ");
		e1.printStackTrace();
		
	}
	
		finally {
			try
			{
			if(bfr!=null)
				 bfr.close();
			}
			catch(Exception e2){
				
				System.out.println(" calling  2 web service1  4 execption ");
				
				e2.printStackTrace();
				
			}
		}
 
	return u;
	
	}


public List<User> getOutput(String userid1, String userid2) {
	// TODO Auto-generated method stub

	
	List<User> al = new ArrayList<User>();
	System.out.println (userid1+ " " + userid2);
	

	try
	{
	Runtime rt = Runtime.getRuntime();
	Process proc = rt.exec(" sh /home/shashi/finalscript3.sh   "+userid1+ " " + userid2);
	//output both stdout and stderr data from proc to stdout of this process
	StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
	StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());
	errorGobbler.start();
	outputGobbler.start();
	proc.waitFor();  

	}
	catch(Exception e1)
	{
		
		System.out.println(" calling 3 web service1 execption ");
	}

		BufferedReader  bfr =null;	
		try
		{	
	    File F1= new File("/home/shashi/output3");
		System.out.println(F1.exists());
	    bfr  = new  BufferedReader(new FileReader(F1));
		String  str="";
		while((str=bfr.readLine())!=null)
		{	
			if(str.trim().length()!=0)
			{	
			  String [] arr =str.split("\\|");
			  System.out.println(arr[0]);
			  User u= new User();
			  u.setId((arr[0]));
			  System.out.println("------------------------");
			  System.out.println(arr[1]);
			  u.setRetweetcount(Integer.parseInt(arr[1].trim()));
			  
			  al.add(u);
            
			}
		}
		
		bfr.close();
	}
	catch(Exception e1)
	{
		System.out.println(" calling  3 web service1  3 execption ");
		e1.printStackTrace();
		
	}
	
		finally {
			try
			{
			if(bfr!=null)
				 bfr.close();
			}
			catch(Exception e2){
				
				System.out.println(" calling  3 web service1  4 execption ");
				
				e2.printStackTrace();
				
			}
		}
 
	return al;
}

	
}

	

    
   