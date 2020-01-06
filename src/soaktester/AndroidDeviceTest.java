package soaktester;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

public class AndroidDeviceTest extends DeviceTest{
	
	AndroidCommand cmd = new AndroidCommand();
	

	@Override
	public void PlayProgram()
	{
		Logger.log("Start Playing program at " + ClickCoordinates);
		
		String[]coordinates = ClickCoordinates.split("/");
		
		try {
			
			for(int i = 0 ; i < coordinates.length;i++)
			{
				String[] c = coordinates[i].split(",");
				
				cmd.adbTap(Ip, c[0], c[1]);
				
				Thread.sleep(Integer.parseInt(c[2]));
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void KillApp() {
		Logger.log("Stopping App:" + ProcessName.split("/")[0]);
		if(logCatProcess != null && logCatProcess.isAlive())
			logCatProcess.destroy();
		cmd.adbStopApp(Ip, ProcessName.split("/")[0]);
	}

	@Override
	public void StartApp() {
		Logger.log("Starting App:" + ProcessName);
		
		try {
			cmd.adbConnect(Ip);
			Thread.sleep(1000);
			StartLogcat();
			cmd.adbStartApp(Ip, ProcessName);
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void StartLogcat()
	{
		Logger.log("Starting logcat for " + Ip);
		LocalDateTime dt = LocalDateTime.now();
		String logcatPath = Paths.get(Sysconfig.workingFolder, String.format("%s_%s_%s_%s_%s_%s_%s.log", Ip,dt.getHour(),dt.getMinute(),dt.getSecond(),dt.getMonth(),dt.getDayOfMonth(),dt.getYear())).toString();
		logCatProcess = cmd.adblogcat(Ip, logcatPath);
	}
	
	private Process logCatProcess;
	
	public AndroidDeviceTest(Map<String,String> deviceInfo) {
		Ip = deviceInfo.get("ip");
		Platform = deviceInfo.get("platform");
		ProcessName = deviceInfo.get("processname");
		TestDuration = deviceInfo.get("testduration");
		Camera = new Camera(deviceInfo.get("cameraname"),deviceInfo.get("testfolder"),TestDuration);
		ClickCoordinates = deviceInfo.get("coordinates");
	}
}
