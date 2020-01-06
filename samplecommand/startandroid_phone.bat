rd /s /q D:\MFTools\androidphonesoak
taskkill /F /IM ffmpeg.exe
java -jar soaktester.jar -platform android -testduration 02:00:00 -workingdir D:\MFTools\androidphonesoak -cameraname "@device_pnp_\\?\usb#vid_045e&pid_0772&mi_00#7&31090c76&1&0000#{65e8773d-8f56-11d0-a3b9-00a0c9223196}\global" -clickcoordinates 620,1500,500/537,1296,10000 -ip 10.100.204.133