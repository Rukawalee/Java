@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *  ��Ҫ���޸�һЩBUG
echo *  author Rukawalee
echo *  ��������Java�ļ�
echo *  Դ���п�������package
echo *  �Ѹ���mysql������
echo *  UTF-8�ļ���������
echo *  runJava_V_1.2.0
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
for /f "tokens=1,2" %%i in ('type %Jsrc%') do (
::��ȡpackage��
	echo "%%i" NEQ "package" 1>NUL 2>NUL && goto finish
	echo "%%i" EQU "package" 1>NUL 2>NUL && set pac=%%j
)
:finish
::��ȡ��ȷ����
if "%pac%" NEQ "" (
	set pac=%pac:;=.%
	::ȥ���ո�
	set pac=%pac: =%
	::��ȡĿ¼
	set t_f=%pac:.=\%
)
for /f %%i in ("%Jsrc%") do set f_n=%%~ni
::�滻·��
call set d=%%Jsrc:\%f_n%.java=%%
::�л�Ŀ¼���ļ���
set l=%d%
:loop
set l=%l:~0,-1%
if "%l:~-1,1%" NEQ "\" goto loop
set l=%l:~0,-1%\lib\mysql-connector-java-8.0.18.jar
cd /d "%d%"
if exist "%t_f%%f_n%.class" (
	::del /f /q "%t_f%*.*"
	for /f "delims=\" %%i in ("%t_f%") do rd /q /s %%i
	echo �ɹ�ɾ���ɱ���
)
javac -d . "%Jsrc%"
if exist "%t_f%%f_n%.class" (
echo ����*.java�ļ��ɹ�
echo ------------------------------------
echo ���н����
java -cp ".;%path%;%l%;" "%pac%%f_n%"
)
echo+
goto again
pause