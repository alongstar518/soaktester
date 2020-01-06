package soaktester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class soaktester {

	public static void main(String[] args) throws Exception {
		
		
		int i = 0;
		while(i < args.length)
		{
			switch(args[i].toLowerCase())
			{
				case "-workingdir":
					Sysconfig.workingFolder = args[++i]; // where video and log stored
					break;

				case "-defaulttimeout":
					Sysconfig.defaultProcTimeOut = Integer.parseInt(args[++i]); // in ms
					break;

				case "-loopdelayseconds":
					Sysconfig.loopDelaySeconds = Integer.parseInt(args[++i]); // in seconds
					break;
				case "-testloops":
					Sysconfig.TestInteration = Integer.parseInt(args[++i]);
				case "-ips":
					Sysconfig.ips = args[++i];
					break;
			}
			i++;
		}
		
		
		Getffmpeglocation();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				Logger.log("Killing ffmpeg");
				String cmd = "killall ffmpeg.exe"; 

				if(System.getProperty("os.name").startsWith("Windows"))
				{
					cmd = "taskkill /F /IM ffmpeg.exe";
				}
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
		
		Logger.log("**Starting Test**");

		CheckWorkingDir();
		// create Device(s) 
		// Device(s).RunTest()
		DeviceFactory();
		RunTest();
	}
	
	private static List<ITest> testDevices=new ArrayList<>();
	public static List<ITest> DeviceFactory() 
	{
		String[] _devices = Sysconfig.ips.split(";;");
		for(String d : _devices)
		{
			Map<String,String> devInfo = ParseDeviceProfile(d);
		
			DeviceTest device;
			if(devInfo.get("platform").equals("android")) {
				device = new AndroidDeviceTest(devInfo);
			}
			else {
				device = new DesktopDeviceTest(devInfo);
			}
			testDevices.add(device);
		}
		return testDevices;
	}
	public static List<Thread> runnignTestThreads= new ArrayList<>();
	public static void RunTest() throws InterruptedException
	{
		for(ITest test:testDevices) {
			
			Thread t = new Thread(new TestThread(test));
			runnignTestThreads.add(t);
			t.start();
		}
		
		for(Thread thread : runnignTestThreads)
		{
			thread.join();
		}
	}
	
	
	
	public static void CheckWorkingDir() throws Exception
	{
		File wdir = new File(Sysconfig.workingFolder);
		if(wdir.exists())
		{
			return;
		}
		wdir.mkdirs();
		
	}
	
	public static void Getffmpeglocation() throws Exception
	{
		if(Sysconfig.ffmpegLoaction == null)
		{
	        Map<String, String> env = System.getenv();
	        for (String envName : env.keySet()) {
	            if(envName.equals("ffmpeg"))
	            {
	            	Sysconfig.ffmpegLoaction = env.get(envName);
	            	return;
	            }
	        }
	        throw new Exception("!!ffmpeg environment variable is not set!!");
		}
	}
	
	private static Map<String,String> ParseDeviceProfile(String deviceProfile)
	{
		Map<String,String> map = new HashMap<>();
		
		String[] item = deviceProfile.split(";");
		for(String _item : item)
		{
			String[] i = _item.split("=");
			map.put(i[0],i[1]);
		}
		
		return map;
	}
	
}
