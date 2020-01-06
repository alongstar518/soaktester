package soaktester;

import java.io.IOException;
import java.util.Map;

public class DesktopDeviceTest extends DeviceTest{

	DesktopCommand cmd = new DesktopCommand();
	
	@Override
	public void PlayProgram() {
		
		try {
			cmd.MouseClick(ClickCoordinates);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void KillApp() {
		
		String processToKill;
		String processKillCmd;
		String os = System.getProperty("os.name");
		
		if(os.startsWith("Windows"))
			processKillCmd = "taskkill /F /IM %s";
		else
			processKillCmd = "killall %s";
		switch(Platform)
		{
			case "ie":
				processToKill = "iexplore.exe";
				break;
			case "edge":
				processToKill = "MicrosoftEdge.exe";
				break;
			case "chrome":
				processToKill = "chrome.exe";
				break;
			case "safari":
				processToKill = "Safari";
				break;
			default:
				processToKill = "";
			
		}
		
	    try {
			Runtime.getRuntime().exec(String.format(processKillCmd,processToKill));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public DesktopDeviceTest(Map<String,String> deviceInfo) {
		Ip = deviceInfo.get("ip");
		Platform = deviceInfo.get("platform");
		ProcessName = deviceInfo.get("processname");
		TestDuration = deviceInfo.get("testduration");
		Camera = new Camera(deviceInfo.get("cameraname"),deviceInfo.get("testfolder"),TestDuration);
		ClickCoordinates = deviceInfo.get("coordinates");
	}
}
