@echo off
color 2e
title made by Rukawalee
echo ------------------------------------
echo *
echo *  author Rukawalee
echo *  ��������Java�ļ�
echo *  Դ������������package
echo *  runJava_V_1.0.0
echo * 	
echo ------------------------------------
:again
echo ������JavaԴ�ļ�(������)��
set /p Jsrc=^>
::ʹ��·��Ψһ��
set Jsrc=%Jsrc:/=\%
echo+
javac "%Jsrc%"
set f=%Jsrc:.java=%
for /f %%i in ("%f%") do set f_n=%%~ni
::�滻·��
call set d=%%f:\%f_n%=%%
if exist "%f%.class" (
echo ����*.java�ļ��ɹ�
echo -----------------------------
echo ���н����
::�л�Ŀ¼���ļ���
cd /d "%d%"
java "%f_n%"
echo+
)
goto again
pause