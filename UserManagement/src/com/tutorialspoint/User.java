package com.tutorialspoint;
  

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;
import org.glassfish.jersey.server.JSONP; 

@XmlRootElement(name = "user") 

public class User implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private  String id; 
   private String name; 
   private String profession;
   private String tweets;
   private int tweetcount;
   private int retweetcount;
   
 
public User(){} 
    
   public User(String id, String name, String profession){  
      this.id = id; 
      this.name = name; 
      this.profession = profession; 
   }  
   public String  getId() { 
      return id; 
   }  
   
   @XmlElement 
   public void setId(String id) { 
      this.id = id; 
   } 
   public String getName() { 
      return name; 
   } 
   @XmlElement
   public void setName(String name) { 
      this.name = name; 
   } 
   public String getProfession() { 
      return profession; 
   } 
   
   @XmlElement 
   public void setProfession(String profession) { 
      this.profession = profession; 
   }   
   

   public String getTweets() {
	return tweets;
}

  @XmlElement
public void setTweets(String tweets) {
	this.tweets = tweets;
}

public int getTweetcount() {
	return tweetcount;
}

@XmlElement
public void setTweetcount(int tweetcount) {
	this.tweetcount = tweetcount;
}

public int getRetweetcount() {
	return retweetcount;
}

@XmlElement
public void setRetweetcount(int retweetcount) {
	this.retweetcount = retweetcount;
}

} 
