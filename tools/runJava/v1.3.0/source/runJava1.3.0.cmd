@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *  ��Ҫ���޸��ش�BUG
echo *  author Rukawalee
echo *  ��������Java�ļ�
echo *  Դ���п�������package
echo *  �Ѹ���mysql������
echo *  ����classes����class
echo *  UTF-8�ļ���������
echo *  runJava_V_1.3.0
echo * 	
echo ------------------------------------
:again
echo ������JavaԴ�ļ�(������)��
set /p Jsrc=^>
if not exist "%Jsrc%" (
    echo �����ڣ�"%Jsrc%"
	goto again
)
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
set l=%grame%\lib\mysql-connector-java-8.0.18.jar
::�л���Դ��Ŀ¼
cd /d "%d%"
javac -d "%grame%\classes" "%Jsrc%"
if exist "%grame%\classes\%t_f%\%f_n%.class" (
echo ����*.java�ļ��ɹ�
echo ------------------------------------
echo ���н����
::�л����ֽ���Ŀ¼
cd /d "%grame%\classes"
::���������ļ�
if exist "%d%\jdbc.properties" (
	copy "%d%\jdbc.properties" "%grame%\classes\jdbc.properties" >NUL
)
java -cp ".;%path%;%l%;" "%pac%%f_n%"
)
echo+
goto again
pause