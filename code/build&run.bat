call ant release
call timeout /t 3 
rename bin\OppoLauncher-release.apk OppoLauncher.apk
adb remount
adb push bin\OppoLauncher.apk /system/app/
timeout /t 4
::adb shell am start -n com.oppo.launcher/com.oppo.launcher.Launcher
 