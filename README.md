# SwaCLI : A simple Star Wars CLI tool 

Created using :

* [Kotlin](https://kotlinlang.org/)
* [PicoCLI](https://picocli.info/)
* [SWAPI](https://swapi.dev/documentation)

## Running

```
$  ./gradlew customFatJar
$ cd build/libs
$ java -cp all-in-one-jar-1.0-SNAPSHOT.jar nl.lengrand.swacli.SwaCLI
# to keep color
$ java -Dpicocli.ansi=true -cp build/libs/all-in-one-jar-1.0-SNAPSHOT.jar nl.lengrand.swacli.SwaCLIOptions -p | less -R 
```

## Author 

* [@jlengrand](https://github.com/jlengrand)
