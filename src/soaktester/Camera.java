package soaktester;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Camera {
	public String DeviceName;
	public String RecordingLocation;
	public String RecordingDuration;
	
	public Camera(String deviceName, String recordingLocation, String recordingDuration) {
		DeviceName = deviceName;
		RecordingLocation = recordingLocation;
		RecordingDuration = recordingDuration;
	}
	
	public void StartRecording()
	{
		Syscommand cmd = new Syscommand();
		LocalDateTime dt = LocalDateTime.now();
		String RecordingFileName= String.format("%s_%s_%s_%s_%s_%s", dt.getHour(),dt.getMinute(),dt.getSecond(),dt.getDayOfMonth(),dt.getMonth(),dt.getYear());
		String ffmpegCmd;
		CheckAndCreateRecordingFolder();
		String recordingLocation = Paths.get(RecordingLocation,RecordingFileName).toString();
		String ffmpeglocation = Paths.get(Sysconfig.ffmpegLoaction,"ffmpeg.exe").toString();
		if(DeviceName != null)
		{
			ffmpegCmd =	String.format("%s -y -f dshow -video_device_number 0 -t %s -i video=\"%s\" %s.mp4 -nostats -loglevel 0",ffmpeglocation,RecordingDuration,DeviceName,recordingLocation);
		}else
		{
			ffmpegCmd = ffmpeglocation + " -y -f gdigrab -i desktop -t " + RecordingDuration + " " + recordingLocation  +".mp4 -nostats -loglevel 0";
		}
		cmd.issueSysCommand(ffmpegCmd, Sysconfig.defaultProcTimeOut, "Error when do ffmpeg recording",true);
	}
	
	public void StopRecording()
	{

	}
	
	private void CheckAndCreateRecordingFolder()
	{
		File wdir = new File(RecordingLocation);
		if(wdir.exists())
		{
			return;
		}
		wdir.mkdirs();
	}
}
