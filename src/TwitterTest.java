import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;
import java.util.List;

public class TwitterTest {
 public static void main(String[] args) {
 try {
 // Authorise the library
 
 
         ConfigurationBuilder cb = new ConfigurationBuilder();
         
         cb.setOAuthConsumerKey("Get Your own");
         cb.setOAuthConsumerSecret("Get Your own");
         cb.setOAuthAccessToken("Get Your own");
         cb.setOAuthAccessTokenSecret("Get Your own");
         
         Twitter twitter = new TwitterFactory(cb.build()).getInstance();
         User user = twitter.verifyCredentials(); // Get main user
         
 // Print user profile
         System.out.println("User Profile");
         System.out.println("UserName: @" + user.getScreenName());
         
         //System.out.println(user.getId());
         System.out.println("Name: "+user.getName());
         //System.out.println(user.getProfileImageURL());
         System.out.println(user.getFriendsCount() + " Friends");
         System.out.println(user.getFollowersCount() + " Folowers");
         System.out.println(user.getDescription());
         System.out.println("-----------------------------------------------------------------------------------------");
         System.out.println(); 
         
 // Print Home Timeline
 
         List<Status> statuses = twitter.getUserTimeline();
         System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
         for (Status status : statuses) 
         {
                System.out.println(status.getCreatedAt()+" - " + status.getText()); 
         }
         System.out.println(); 
            
//User Followers    //Get's only first 20 results
            System.out.println(user.getName()+" is "+ " Following:--------------------------------------------------------"); 
            PagableResponseList<User> s2 = twitter.getFriendsList(" your name here", -1);
            for (User fID : s2) {

                System.out.println(fID.getName()); 
            } 

//Folowers 2        //Get's all the result
long cursor = -1;
    IDs ids;
    System.out.println("User Followers:----------------------------------------------------------------------------------------");
    do 
    {
        ids = twitter.getFollowersIDs(" your name here", cursor);
        for (long id : ids.getIDs()) {
            //System.out.println(id);
            User user2 = twitter.showUser(id);
            System.out.println(user2.getName());
        }
    } 
    while ((cursor = ids.getNextCursor()) != 0);
    
          
//User Friends  //Get's only first 20 results

        System.out.println(user.getName()+"'s"+ " Followers:------------------------------------------------------------");
         PagableResponseList<User> s1 = twitter.getFollowersList("your name here", -1);
            for (User fID : s1) {

                System.out.println(fID.getName()); 
            }
        System.out.println();     
    
    
 //Friends 2    //Get's all the result
    cursor = -1;
    IDs ids1;
    System.out.println("User Friends---------------------------------------------------------------------------------------");
    do 
    {
        ids = twitter.getFriendsIDs(" your name here", cursor);
        for (long id : ids.getIDs()) {
            //System.out.println(id);
            User user2 = twitter.showUser(id);
            System.out.println(user2.getName());
        }
    } 
    while ((cursor = ids.getNextCursor()) != 0);
    
 } 
 catch (TwitterException te) 
     {
     te.printStackTrace();
     System.out.println("Failed to get timeline: " + te.getMessage());
     System.exit(-1);
     }
 }
}
