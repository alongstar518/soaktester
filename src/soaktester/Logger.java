package soaktester;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

public class Logger {
	public static void log(String message)
	{
		 if(message == null) return;
		 File logFile = new File(Sysconfig.workingFolder , "Op.log");
		 try
		 {
			  if(!logFile.exists())
			  {
				  logFile.createNewFile();
			  }
			  Writer fileWriter = new FileWriter(logFile,true);	
			  try
			  {
				  message = LocalDateTime.now().toString() + ":  " + message;
				  System.out.println(message);
				  fileWriter.write(message  + '\n'); 
			  }
			  catch(IOException e)
			  {
				  
			  }
			  fileWriter.close();
		 }
		 catch(IOException e)
		 {
			 
		 }
		  
	}
}
