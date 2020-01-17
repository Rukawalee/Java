@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *
echo *  author Rukawalee
echo *  快速运行Java文件
echo *  源码中请勿声明package
echo *  runJava_V_1.0.0
echo * 	
echo ------------------------------------
:again
echo 请输入Java源文件(可拖入)：
set /p Jsrc=^>
::使得路径唯一性
set Jsrc=%Jsrc:/=\%
echo+
javac "%Jsrc%"
set f=%Jsrc:.java=%
for /f %%i in ("%f%") do set f_n=%%~ni
::替换路径
call set d=%%f:\%f_n%=%%
if exist "%f%.class" (
echo 编译*.java文件成功
echo -----------------------------
echo 运行结果：
::切换目录到文件下
cd /d "%d%"
java "%f_n%"
echo+
)
goto again
pause