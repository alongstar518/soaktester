package soaktester;

public class AndroidCommand extends Syscommand{
	 
	public boolean adbConnect(String ip)
	{
		return issueSysCommand(String.format("adb connect %s", ip),Sysconfig.defaultProcTimeOut,Error_adbConnect,false);
	}
	
	public boolean adbStopApp(String ip, String pkgName)
	{
		return issueSysCommand(String.format("adb -s %s:5555 shell am force-stop %s", ip,pkgName), Sysconfig.defaultProcTimeOut,Error_adbStopApp,false);
	}
	
	public boolean adbStartApp(String ip, String pkgName)
	{
		return issueSysCommand(String.format("adb -s %s:5555 shell am start %s", ip,pkgName), Sysconfig.defaultProcTimeOut,Error_adbLaunch,false);
	}
	
	public boolean adbTap(String ip, String x, String y)
	{
		return issueSysCommand(String.format("adb -s %s:5555 shell input tap %s %s", ip,x,y),Sysconfig.defaultProcTimeOut,Error_adbtap,false);
	}
	
	public Process adblogcat(String ip, String logfile)
	{
		if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
				return issueSysCommand(String.format("cmd /c \"adb -s %s:5555 shell logcat -v time > %s\"", ip, logfile));
		return null;
	}
	private String Error_adbConnect = "unable to connect to";
	private String Error_adbStopApp = "fail";
	private String Error_adbLaunch = "fail";
	private String Error_adbtap = "fail";
}
