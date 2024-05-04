# Tugas Kecil 3 IF2211 Strategi Algoritma

## Project Description
Students were given an assignment to write a small program in Java that solves the "word ladder" game using three algorithms: Uniform Complete Search, Greedy Best First Search, and A\* algorithm.

## Requirements
This program requires only Java to run. For Unix-like OS, do
```
sudo apt update
sudo apt install default-jdk
```
and for windows, either download Java manually or do
```
winget install -e --id Oracle.JavaRuntimeEnvironment
```
after installing, make sure Java can be run from an arbitrary location by doing
```
java --version
```
If the version information does not show up, then either the installation has not been completed correctly, or you simply need to restart the terminal.

## How To Run


Make sure to navigate to `bin` folder, then just run with Java:

```
java src.Main
```
I have also provided the executable (`.jar` file), it is  in `bin` folder as well. If this is preferred, again navigate to `bin` and simply do
```
java -jar app.jar
```
Alternatively, you can also double click the executable and it should automatically prompt to open with java. **If you move the executable file, make sure that it is in the same folder as "words.txt", otherwise it will not find the file.**

## How To Compile (Optional)
If you want, you can recompile the `.java` files. These are of course located in the `src` folder so **navigate there** and you can compile by using `javac` (note that this is seperate to Java and you will need to have installed that before compiling).
```
javac *.java -d ../bin
```
Lastly, if you, for whatever reason, want to rebuild the JAR file, then you can do that by doing (from `bin` folder)
```
jar cvmf MANIFEST.MF app.jar *
```

# How To Use

1. The program will start by trying to load from `words.txt` file. Wait for it (this should last no more than a few sconds). The program will display a message once it has finished loading.

2. Once running, the program will now continuously receive input, solve, and output the solution until you exit the program.
3. To exit the program, type `/quit` (case sensitive).
4. The input format is a single line consisting of three words, `startword`, `endword`, and `algorithm`, where `startword` is the start word, `endword` is the goal word, and `algorithm` is the name of the algorithm you would like to use. This consists of `"UCS"`, `"GBFS"`, and `"ASTAR"`, which correspond to Uniform Complete Search, Greedy Best First Search, and A\* algorithm respectively. All three words are **CASE INSENSITIVE**. In addition, **the program will continue to work even if you add additional words**, it will just ignore them. It will also not be bothered by newlines, it will simply wait until you have put in at least three words. Below are a few examples of accepted inputs.  

`Hello World UCS`

`play  dull    gbfs`

`RUN FLY astar  hello from the other siiiiiiiidddeee!!!`

5. You will be notified if your inputs are incorrect as well as the reason. 

## Extras
By modifying words.txt, you can manipulate what exists or doesn't exist in the dictionary. You can play around with this, for example by adding imaginary words to create faster paths or by using a different wordset entirely. If you do this, **make sure that all words consists of only letters and are lowercase.** And also make sure the file is still named words.txt.

## Author

|NAMA|NIM|
|-|-|
|Renaldy Arief Susanto|13522022|
