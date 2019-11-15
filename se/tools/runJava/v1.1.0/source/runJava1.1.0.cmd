@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *
echo *  author Rukawalee
echo *  快速运行Java文件
echo *  源码中可以声明package
echo *  不能附加jar包
echo *  UTF-8文件中文乱码
echo *  runJava_V_1.1.0
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
for /f "tokens=1,2" %%i in ('type %Jsrc%') do (
::获取package名
	echo "%%i" EQ "package" 1>NUL 2>NUL && set pac=%%j && goto finish
)
:finish
::获取正确包名
set pac=%pac:;=.%
::去除空格
set pac=%pac: =%
::获取目录
set t_f=%pac:.=\%
for /f %%i in ("%Jsrc%") do set f_n=%%~ni
::替换路径
call set d=%%Jsrc:\%f_n%.java=%%
::切换目录到文件下
cd /d "%d%"
if exist "%t_f%" (
	::del /f /q "%t_f%*.*"
	for /f "delims=\" %%i in ("%t_f%") do rd /q /s %%i
	echo 成功删除旧编译
)
javac -d . "%Jsrc%"
if exist "%t_f%%f_n%.class" (
echo 编译*.java文件成功
echo -----------------------------
echo 运行结果：
java "%pac%%f_n%"
echo+
)
goto again
pause