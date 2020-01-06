taskkill /F /IM ffmpeg.exe
rd /s /q D:\Test\Tests\chromesoak
java -jar soaktester.jar -platform chrome -testduration 02:00:00 -workingdir D:\Test\Tests\chromesoak -clickcoordinates 11,1048,1000/853,295,30000/913,673,1000/913,673,10000