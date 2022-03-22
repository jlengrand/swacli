#!/bin/sh

~/Downloads/graalvm-ce-java11-22.0.0.2/Contents/Home/bin/native-image -jar build/libs/all-in-one-jar-1.0-SNAPSHOT.jar sw --enable-http --enable-https
