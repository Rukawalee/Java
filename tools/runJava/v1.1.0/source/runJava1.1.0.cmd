@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *
echo *  author Rukawalee
echo *  ��������Java�ļ�
echo *  Դ���п�������package
echo *  ���ܸ���jar��
echo *  UTF-8�ļ���������
echo *  runJava_V_1.1.0
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
	echo "%%i" EQ "package" 1>NUL 2>NUL && set pac=%%j && goto finish
)
:finish
::��ȡ��ȷ����
set pac=%pac:;=.%
::ȥ���ո�
set pac=%pac: =%
::��ȡĿ¼
set t_f=%pac:.=\%
for /f %%i in ("%Jsrc%") do set f_n=%%~ni
::�滻·��
call set d=%%Jsrc:\%f_n%.java=%%
::�л�Ŀ¼���ļ���
cd /d "%d%"
if exist "%t_f%" (
	::del /f /q "%t_f%*.*"
	for /f "delims=\" %%i in ("%t_f%") do rd /q /s %%i
	echo �ɹ�ɾ���ɱ���
)
javac -d . "%Jsrc%"
if exist "%t_f%%f_n%.class" (
echo ����*.java�ļ��ɹ�
echo -----------------------------
echo ���н����
java "%pac%%f_n%"
echo+
)
goto again
pause