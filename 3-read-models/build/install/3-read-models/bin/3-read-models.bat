@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  3-read-models startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and 3_READ_MODELS_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\3-read-models.jar;%APP_HOME%\lib\commons-lang3-3.0.jar;%APP_HOME%\lib\axon-core-2.4.4.jar;%APP_HOME%\lib\spring-shell-1.1.0.RELEASE.jar;%APP_HOME%\lib\disruptor-3.2.0.jar;%APP_HOME%\lib\cglib-nodep-2.2.2.jar;%APP_HOME%\lib\slf4j-api-1.7.5.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\xstream-1.4.6.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar;%APP_HOME%\lib\jline-2.11.jar;%APP_HOME%\lib\guava-15.0.jar;%APP_HOME%\lib\cglib-2.2.2.jar;%APP_HOME%\lib\spring-context-support-4.0.3.RELEASE.jar;%APP_HOME%\lib\spring-core-4.0.3.RELEASE.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\asm-3.3.1.jar;%APP_HOME%\lib\spring-beans-4.0.3.RELEASE.jar;%APP_HOME%\lib\spring-context-4.0.3.RELEASE.jar;%APP_HOME%\lib\commons-logging-1.1.3.jar;%APP_HOME%\lib\spring-aop-4.0.3.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.0.3.RELEASE.jar;%APP_HOME%\lib\aopalliance-1.0.jar

@rem Execute 3-read-models
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %3_READ_MODELS_OPTS%  -classpath "%CLASSPATH%" axon.ui.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable 3_READ_MODELS_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%3_READ_MODELS_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
