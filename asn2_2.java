 import java.util.Scanner;
 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.util.StringTokenizer;
 import java.io.*;
 import java.text.*;
 import java.util.*;
 
 public class asn2_2
 {
   public static void main(String [] args)
   {
      String FileLocation = args[0];
      String LogLocation = args[1];
      System.out.println("\nYour CSV File Location is :"+FileLocation);
      System.out.println("\nYour Log File Location is :"+LogLocation);
      Scanner in = new Scanner(System.in);
      String UserName,PassWord;
      System.out.println("\n\n!! .. Welcome to the SignUp Program .. !!");
      System.out.print("\n\nEnter the UserName : ");
      UserName = in.next();
      System.out.println("");
      System.out.print("\n\nEnter your Password : ");
      PassWord = in.next();
      System.out.println("");
      int flag = MatchUserName(UserName,FileLocation);
     
      
      while(flag == 1)
      {
         System.out.print("\n\nEnter another UserName : ");
         UserName = in.next();
         System.out.println("");
         flag = MatchUserName(UserName,FileLocation);
      }
      
      if(flag == 2)
      {
         
         WriteIntoCSV(UserName,PassWord,FileLocation);

         System.out.println("\n\nThank You For Using Our Sign Up Program.\n\n We have updated your UserName and PassWord in Our database.\n\n");
         String defaultLogFile = LogLocation;
         try{
         GenerateLogFile(defaultLogFile,LogLocation);
         }
         catch(IOException e)
	      {
	          e.printStackTrace();
	      }
         System.out.println("\n\nLog file is generated at "+LogLocation+ " ");
         //System.out.println("\n\nEnd Of the SignUp Story \n\n");
         //write(defaultLogFile, s);
      }
      
      
                
      
   }
    static int  MatchUserName(String UserName,String FileLocation)
      {
         try
         {
            int flag = 0;
            FileReader fr = new FileReader("asn2_2.java");
            String csvFile = FileLocation;
            //System.out.println(csvFile);        
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            StringTokenizer st = null;
            int lineNumber = 0; 
            int tokenNumber = 0;
            while ((line = br.readLine()) != null && flag == 0)
            {
               lineNumber++;
               st = new StringTokenizer(line, ",");

               while (st.hasMoreTokens())
               {
                  tokenNumber++;        
                  if(UserName.equals(st.nextToken()))
                  {
                     System.out.println("User Name Already Exists. Please Choose another user name.");
                     fr.close();
                     flag = 1;
                     return 1;
                  }
                  st.nextToken();
                 
          
               }
            //tokenNumber = 0;
           
             
          }
        
         fr.close();
         return 2;
         
         

     }
      catch (Exception e) 
      {
         System.err.println("CSV file cannot be read : " + e);
      }
      
      return 4;   
  }
 
 
 
 
 
static int WriteIntoCSV(String UserName,String PassWord,String FileLocation)
 {
  
 try
	{
	    String csvFile = FileLocation;
       FileWriter writer = new FileWriter(csvFile,true);
       StringTokenizer st = null;
       String line = "";
       st = new StringTokenizer(line, ",");
       int tokenNumber = 0;
       int lineNumber = 0;
               while (st.hasMoreTokens())
               {
                  tokenNumber++;        
                  st.nextToken();
          
               }
       for(int j=0;j<tokenNumber;j++)
       {
          writer.append('\n');
       }
      

 
	    writer.append(UserName);
	    writer.append(',');
	    writer.append(PassWord);
	    writer.append('\n'); 
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
   return 0;
 }


     public static void GenerateLogFile(String s,String LogLoc) throws IOException {
      String defaultLogFile =LogLoc;

         write(defaultLogFile, s);
     }
    
         public static void write(String f, String s) throws IOException {
         TimeZone tz = TimeZone.getTimeZone("IST");
         Date now = new Date();
         DateFormat df = new SimpleDateFormat ("yyyy.mm.dd hh:mm:ss ");
         df.setTimeZone(tz);
         String currentTime = df.format(now);
        
         FileWriter aWriter = new FileWriter(f, true);
         aWriter.write(currentTime + " " + s + "\n");
         aWriter.flush();
         aWriter.close();
     }



}
 
