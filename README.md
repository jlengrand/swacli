# SwaCLI : A simple Star Wars CLI tool 

Created using :

* [Kotlin](https://kotlinlang.org/)
* [PicoCLI](https://picocli.info/)
* [SWAPI](https://swapi.dev/documentation)

This repository contains several small CLIs that aim at demonstrating the various capabilities of [picoCLI](https://picocli.info/).

You can find : 

* [SwaCLIBasic](src/main/kotlin/nl/lengrand/swacli/SwaCLIBasic.kt) Not doing anything but showing how picoCLI comes with batteries included
* [SwaCLIOptions](src/main/kotlin/nl/lengrand/swacli/SwaCLIOptions.kt) A small demonstration on how to use (mutually exclusive) options and arguments
* [SwaCLISubCommands](src/main/kotlin/nl/lengrand/swacli/SwaCLISubCommands.kt) A small demonstration on how to use subcommands 
* [SwaCLIPaginate](src/main/kotlin/nl/lengrand/swacli/SwaCLIPaginate.kt) A small demonstration on how to use subcommands and paginate the results. Default command in the sw compilable
* [SwaCLIProgrammatic](src/main/kotlin/nl/lengrand/swacli/SwaCLIProgrammatic.kt) An example that uses the programmatic API instead of the annotation system.
* [SwaCLISubCommandsTest](src/test/kotlin/nl/lengrand/swacli/SwaCLISubCommandsTest.kt) An example on how to test commandline configurations 


All examples are in Kotlin and provide the same rough capabilities (get some facts about characters or planets in Star Wars)

## Compiling / Running 

```
$  ./gradlew customFatJar
$ java -cp build/libs/all-in-one-jar-1.0-SNAPSHOT.jar nl.lengrand.swacli.SwaCLIPaginate
```

or if you're on Mac give a shot to the latest [release](https://github.com/jlengrand/swacli/releases).

## Creating the native image

[You'll need to have GraalVM and the native image extension setup on your system :)](https://www.graalvm.org/reference-manual/native-image/)

```
$ cd build/libs;native-image --static -jar all-in-one-jar-1.0-SNAPSHOT.jar sw --enable-http --enable-https
# remove static if you're on Mac :)
```

##  Why

This is an example CLI that I created as illustration for my [JFall 2020 talk](https://jfall.nl/sessions/an-introduction-to-creating-cli-applications-using-picocli/)

You can check out the slides [here](https://github.com/jlengrand/picocli-jfall-bitesize-2020). 

## Source

* [The repository can be found here](https://github.com/jlengrand/swacli)

## Author 

* [@jlengrand](https://twitter.com/jlengrand)
