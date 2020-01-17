@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *  重要：修复重大BUG
echo *  author Rukawalee
echo *  快速运行Java文件
echo *  源码中可以声明package
echo *  已附加mysql驱动包
echo *  新增classes管理class
echo *  独立properties文件
echo *  UTF-8文件中文乱码
echo *  runJava_V_1.3.1
echo * 	
echo ------------------------------------
:again
echo 请输入Java源文件(可拖入)：
set /p Jsrc=^>
if not exist "%Jsrc%" (
    echo 不存在："%Jsrc%"
	goto again
)
::使得路径唯一性
set Jsrc=%Jsrc:/=\%
echo+
::注意：for里面如果只有一条命令不能用()括起来
for /f "tokens=1,2 delims=; " %%i in ('type %Jsrc%') do if "%%i" == "package" (
	set pac=%%j
) else (
	goto fin
)
:fin
::获取正确包名
if "%pac%" NEQ "" (
	set t_f=%pac:.=\%
	set pac=%pac%.
)
::获取文件名
for /f %%i in ("%Jsrc%") do set f_n=%%~ni
::获取源码路径
call set d=%%Jsrc:\%f_n%.java=%%
::切换目录到jar包文件下
set grame=%d%
:loop
set grame=%grame:~0,-1%
if "%grame:~-1,1%" NEQ "\" goto loop
set grame=%grame:~0,-1%
if exist "%grame%\classes\%t_f%\%f_n%.class" (
	::切换到字节码目录
	cd /d "%grame%\classes"
	if "%t_f%" == "" (
		del /f %f_n%.class 
	) else ( 
		for /f "delims=\" %%i in ("%t_f%") do rd /q /s %%i
	)
	echo 成功删除历史编译
)
::获取驱动jar包
set l=%grame%\lib\mysql-connector-java-8.0.18.jar
::切换到源码目录
cd /d "%d%"
javac -d "%grame%\classes" "%Jsrc%"
if exist "%grame%\classes\%t_f%\%f_n%.class" (
echo 编译*.java文件成功
echo ------------------------------------
echo 运行结果：
::切换到字节码目录
cd /d "%grame%\classes"
::拷贝配置文件
if exist "%grame%\properties\jdbc.properties" (
	copy "%grame%\properties\jdbc.properties" "%grame%\classes\jdbc.properties" >NUL
)
java -cp ".;%path%;%l%;" "%pac%%f_n%"
)
echo+
goto again
pause