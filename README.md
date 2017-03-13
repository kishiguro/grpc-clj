# gRPC from Clojure

This is example of calling gRPC API from Clojure.

### gRPC Java

To use gRPC from Clojure, we need to set
up [gRPC Java](https://github.com/grpc/grpc-java) environment first. We can use
Maven or Gradle for project set up. In this example, we use Gradle.

#### Install Gradle

* Linux

	`$ sudo apt install gradle`

* Mac OS

	`$ brew install gradle`

#### Prepare build.gradle

We use stripped version of gRPC Java
Example [build.gradle](https://github.com/kishiguro/grpc-clj/build.grade).
Original bulid.gradle is located
at [here](https://github.com/grpc/grpc-java/blob/master/examples/build.gradle).

``` groovy
// Feel free to delete the comment at the next line. It is just for safely
// updating the version in our release process.
def grpcVersion = '1.1.2' // CURRENT_GRPC_VERSION
```


### Leiningen for Java and Clojure mixed-code

### Example Server

### Example Client
