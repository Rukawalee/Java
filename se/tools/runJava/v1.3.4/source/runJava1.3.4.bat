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
echo *  ��������Java�ļ�
echo *  Դ���п�������package
echo *  �ɸ��Ӷ��������
echo *  ����classes����class
echo *  �ɸ��Ӷ��properties�ļ�
echo *  �ɸ��Ӷ��XML�����ļ�
echo *  �޸�UTF-8�ļ���������
echo *  runJava_V_1.3.4
echo * 	
echo ------------------------------------
echo ������JavaԴ�ļ�(������)��
set /p Jsrc=^>
if not exist "%Jsrc%" (
    echo �����ڣ�"%Jsrc%"
	goto again
)
::����
cls
::ʹ��·��Ψһ��
set Jsrc=%Jsrc:/=\%
echo+
::ע�⣺for�������ֻ��һ���������()������
for /f "tokens=1,2 delims=; " %%i in ('type %Jsrc%') do if "%%i" == "package" (
	set pac=%%j
) else (
	goto fin
)
:fin
::��ȡ��ȷ����
if "%pac%" NEQ "" (
	set t_f=%pac:.=\%
	set pac=%pac%.
)
::��ȡ�ļ���
for /f %%i in ("%Jsrc%") do set f_n=%%~ni
::��ȡԴ��·��
call set d=%%Jsrc:\%f_n%.java=%%
::�л�Ŀ¼��jar���ļ���
set grame=%d%
:loop
set grame=%grame:~0,-1%
if "%grame:~-1,1%" NEQ "\" goto loop
set grame=%grame:~0,-1%
if exist "%grame%\classes\%t_f%\%f_n%.class" (
	::�л����ֽ���Ŀ¼
	cd /d "%grame%\classes"
	if "%t_f%" == "" (
		del /f %f_n%.class 
	) else ( 
		for /f "delims=\" %%i in ("%t_f%") do rd /q /s %%i
	)
	echo �ɹ�ɾ����ʷ����
)
::��ȡ����jar��
set l=%grame%\lib\*
::�л���Դ��Ŀ¼
cd /d "%d%"
javac -encoding UTF-8 -cp ".;%path%;%l%;" -d "%grame%\classes" "%Jsrc%"
if exist "%grame%\classes\%t_f%\%f_n%.class" (
echo ����*.java�ļ��ɹ�
echo ------------------------------------
echo ���н����
::�л����ֽ���Ŀ¼
cd /d "%grame%\classes"
::���������ļ�
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