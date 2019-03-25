***

<div align="center">
    <b><em>jSupla</em></b><br>
    IoT with Java and <why href="https://supla.org">Supla</why>
</div>

<div align="center">

[![Build Status](https://travis-ci.org/magx2/jSupla.svg?branch=master)](https://travis-ci.org/magx2/jSupla)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/magx2/jSupla.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/magx2/jSupla/alerts/)
[![Download](https://api.bintray.com/packages/big-boy/bigboy/jSupla/images/download.svg) ](https://bintray.com/big-boy/bigboy/jSupla/_latestVersion)

</div>

***

# Modules

There are few modules in jSupla project. Two most important are ```server``` and ```nettyServer```. 
First is an abstraction on server-client communication. Second is implementation done in Netty framework.

| Artifact ID  | Depends On   | Comment |
| ------------ | ------------ | ------- |
| nettyServer  | server       | This is module required for creating server that will handle connecting Supla devices and clients. |
| server       | protocolJava | Abstraction for creating Supla server. Use this interfaces when dealing with Supla server |
| protocolJava | protocol     | Module that contains all entity classes that are send in protocol. Entities are Java friendly |
| protocol     | common       | Very similar to ```protocolJava```, but entities are more familiar to C/C++ programmers. They do not obey ```POJO``` rules. Use this module if you need to get really good performance. |
| common       | N/A          | Common classes used by all modules. Do not use them by yourself. |
 

# Installing

## Repository

Add ```Big Boy``` maven repository to your ```build.gradle```:

### Gradle

```groovy
repositories {
    maven {
        url "https://dl.bintray.com/big-boy/bigboy"
    }
}
``` 

### Maven

```xml
<repositories>
    <repository>
      <id>big-boy</id>
      <name>Big Boy</name>
      <url>https://dl.bintray.com/big-boy/bigboy</url>
    </repository>
  </repositories>
```

## Dependency

Depending which module you want to use add it as dependency: 

### Gradle

```groovy
compile 'pl.grzeslowski.jSupla:$module:0.9.0'
```

### Maven

```xml
<dependency>
  <groupId>pl.grzeslowski.jSupla</groupId>
  <artifactId>$module</artifactId>
  <version>0.9.0</version>
  <type>pom</type>
</dependency>
```

# Usage

Full tutorial need to be written but at this time you can check [Netty Test project](https://github.com/magx2/jSupla/blob/master/nettyTest/src/main/java/pl/grzeslowski/jsupla/nettytest/Server.java) 
or jSupla binding in OpenHAB.
