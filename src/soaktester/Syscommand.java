package soaktester;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

public class Syscommand {
	public boolean issueSysCommand(String cmd,int timeOutKill,String errorMsg,boolean block)
	{
		boolean ret = true;
		Logger.log("Issuing Command " + cmd+ " timeout:" + timeOutKill);
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = rt.exec(cmd);
			
			if(block)
			{
				proc.waitFor();
			}
			else
			{
				proc.waitFor(timeOutKill, TimeUnit.MILLISECONDS);
			}
			if(proc.isAlive())
			{
				proc.destroy();
				if(timeOutKill == Sysconfig.defaultProcTimeOut)
				{
					Logger.log("Operation TimeOut");
					ret = false;
				}
			}
			
		} catch (Exception e) {
			Logger.log(e.getMessage());
		}

		return ret;
		
	}
	
	public Process issueSysCommand(String cmd)
	{
		Logger.log("Issuing Command " + cmd+ " without timeout");
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = rt.exec(cmd);
			
		} catch (Exception e) {
			Logger.log(e.getMessage());
		}

		return proc;
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkCmdSuccess(InputStream input, String errorMsg)
	{
		if(errorMsg == null)
		{
			return true;
		}
		String toChk = null;
		try {
			toChk = IOUtils.toString(input).toLowerCase();
		} catch (IOException e) {
			Logger.log("Error When CheckingErrorMessage");
			Logger.log(e.getMessage());
			return false;
		}
		if(toChk.contains(errorMsg))
			return false;
		return true;
	}
	
}
