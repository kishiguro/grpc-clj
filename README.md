# gRPC from Clojure

This is example of calling gRPC API from Clojure.

### gRPC Java

To use gRPC from Clojure, we need to set
up [gRPC Java](https://github.com/grpc/grpc-java) environment first. We can use
Maven or Gradle for project set up. In this example, we use Gradle.

### Install Gradle

When gradle is not installed on your system, need to install it first.

* Linux

``` bash
$ sudo apt install gradle
```

* Mac OS

``` bash
$ brew install gradle
```

### Prepare build.gradle and proto file.

We use stripped version of gRPC Java
Example [build.gradle](https://github.com/kishiguro/grpc-clj/blob/master/build.gradle).
Original bulid.gradle is located
at [here](https://github.com/grpc/grpc-java/blob/master/examples/build.gradle).

Proto file should be located in `src/main/proto`. In this example, `route_guide.proto` is
used.

Generate project with gradle.

``` bash
$ gradle generateProto
Starting a Gradle Daemon (subsequent builds will be faster)
:extractIncludeProto
:extractProto
:generateProto NO-SOURCE

BUILD SUCCESSFUL

Total time: 11.012 secs
```

### Leiningen for Java and Clojure mixed-code

### Example Server

### Example Client
