package com.tutorialspoint;


import java.util.List; 
import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;  
@Path("/UserService") 

public class UserService {  
   UserDao userDao = new UserDao();
   
   
   @GET 
   @Path("/users") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<User> getUsers(){ 
      return userDao.getAllUsers();
      
   }    
   
   
   @GET 
   @Path("/users/{timestamp}") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<User> getUser(@PathParam("timestamp") String timestamp){ 
	   
	   System.out.println(" "+ timestamp);
      return userDao.getTweets(timestamp); 
      
   }
   

   
   @GET 
   @Path("/user/{userid}") 
   @Produces(MediaType.APPLICATION_XML) 
   public User getUser(@PathParam("userid") long userid ){ 
	   
	   
	   return userDao.getOutput(""+userid);
	    
	 
   }  
   
   
   
   @GET 
   @Path("/user/{userid1}/{userid2}") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<User> getUser(@PathParam("userid1") String userid1,@PathParam("userid2") String userid2 ){ 
	   
	  return  userDao.getOutput(userid1, userid2);
	  
   }  
   
   
   
   
   
  
   
   
   
}