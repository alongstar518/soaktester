taskkill /F /IM ffmpeg.exe
rd /s /q D:\Test\Tests\IEsoak
java -jar soaktester.jar -platform ie -testduration 02:00:00 -workingdir D:\Test\Tests\IEsoak -clickcoordinates 30,1048,500/537,299,30000/913,673,1000/913,673,10000