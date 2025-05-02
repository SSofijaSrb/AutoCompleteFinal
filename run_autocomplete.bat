@echo off
echo Compiling Java files...
javac -d out app\src\main\java\autocomplete\*.java

echo Running AutoCompleteApp...
java -cp out autocomplete.AutoCompleteApp

pause
