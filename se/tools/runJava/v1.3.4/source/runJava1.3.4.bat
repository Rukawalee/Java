::[Bat To Exe Converter]
::
::YAwzoRdxOk+EWAjk
::fBw5plQjdCuDJGuR/0MnKRdRSTiIPX27OqYZ1Pz04OaUnl0UV94NbYGV07eBQA==
::YAwzuBVtJxjWCl3EqQJgSA==
::ZR4luwNxJguZRRnk
::Yhs/ulQjdF+5
::cxAkpRVqdFKZSjk=
::cBs/ulQjdF+5
::ZR41oxFsdFKZSTk=
::eBoioBt6dFKZSDk=
::cRo6pxp7LAbNWATEpSI=
::egkzugNsPRvcWATEpSI=
::dAsiuh18IRvcCxnZtBJQ
::cRYluBh/LU+EWAnk
::YxY4rhs+aU+IeA==
::cxY6rQJ7JhzQF1fEqQJhZkgaGGQ=
::ZQ05rAF9IBncCkqN+0xwdVsFAlfMaAs=
::ZQ05rAF9IAHYFVzEqQIiPRV+TRKDXA==
::eg0/rx1wNQPfEVWB+kM9LVsJDBaXMkG7ELR8
::fBEirQZwNQPfEVWB+kM9LVsJDBaXMkG7ELR8
::cRolqwZ3JBvQF1fEqQK199DdrPsK45syx1k26f76aTB3JYX20TjQGA==
::dhA7uBVwLU+EWDk=
::YQ03rBFzNR3SWATElA==
::dhAmsQZ3MwfNWATExlc7KQxVQAGHXA==
::ZQ0/vhVqMQ3MEVWAtB9wGg5fTRODMG6/Zg==
::Zg8zqx1/OA3MEVWAtB9wGg5fTRODMG6/Zg==
::dhA7pRFwIByZRRnk
::Zh4grVQjdCuDJGuR/0MnKRdRSTiIPX27OqYZ1Pz04OaUnl8IWsg+bo7nzOXAc65H1lfheZMsxX9mit4CJhpOf1yudgpU
::YB416Ek+ZG8=
::
::
::978f952a14a936cc963da21a135fa983
@echo off
color 2e
title made by Rukawalee
:again
echo ------------------------------------
echo *  author Rukawalee
echo *  快速运行Java文件
echo *  源码中可以声明package
echo *  可附加多个驱动包
echo *  新增classes管理class
echo *  可附加多个properties文件
echo *  可附加多个XML配置文件
echo *  修复UTF-8文件中文乱码
echo *  runJava_V_1.3.4
echo * 	
echo ------------------------------------
echo 请输入Java源文件(可拖入)：
set /p Jsrc=^>
if not exist "%Jsrc%" (
    echo 不存在："%Jsrc%"
	goto again
)
::清屏
cls
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
set l=%grame%\lib\*
::切换到源码目录
cd /d "%d%"
javac -encoding UTF-8 -cp ".;%path%;%l%;" -d "%grame%\classes" "%Jsrc%"
if exist "%grame%\classes\%t_f%\%f_n%.class" (
echo 编译*.java文件成功
echo ------------------------------------
echo 运行结果：
::切换到字节码目录
cd /d "%grame%\classes"
::拷贝配置文件
if exist "%grame%\properties\*.properties" (
	copy "%grame%\properties\*.properties" "%grame%\classes\*.properties" >NUL
)
if exist "%grame%\properties\*.xml" (
	copy "%grame%\properties\*.xml" "%grame%\classes\*.xml" >NUL
)
java -cp ".;%path%;%l%;" "%pac%%f_n%"
)
echo+
pause
goto again
pause