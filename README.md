***

<div align="center">
	<b><em>jSupla</em></b><br>
	Java ❤️ <a href="https://supla.org">Supla</a>
</div>

<div align="center">

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

</div>

***

**jSupla** is an implementation of the Supla protocol for Java, designed to facilitate the development of IoT (Internet
of Things) applications. The Supla protocol is used for communication between smart devices, enabling them to interact
seamlessly within a connected environment. With **jSupla**, developers can create servers that communicate with
Supla-compatible devices, handling various operations such as data exchange, command execution, and device status
monitoring.

The project provides two main modules: `protocol` and `server`. The `protocol` module includes the necessary data
structures and serialization/deserialization mechanisms for the Supla protocol, while the `server` module offers a
robust server implementation using Reactor and Netty. This combination allows for efficient, asynchronous communication
with Supla devices, making it easier to build scalable and responsive IoT solutions.

# Modules

| Module   | Description                                                                                                                                     |
|----------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| protocol | Contains structures that are sent in the Supla environment and decoders/encoders for them. The code generation is done during the Gradle build. |
| server   | Implements a server for Supla devices and clients. Internally, it uses Reactor and Netty.                                                       |

# Installing

Both modules are deployed to Maven Central.

## `protocol` module

The protocol module can be found
at [Maven Repository - Protocol](https://mvnrepository.com/artifact/pl.grzeslowski.jSupla/protocol):

```xml

<dependency>
	<groupId>pl.grzeslowski.jSupla</groupId>
	<artifactId>protocol</artifactId>
	<version>X.Y.Z</version>
</dependency>
```

```groovy
implementation group: 'pl.grzeslowski.jSupla', name: 'protocol', version: 'X.Y.Z'
```

## `server` module

The server module can be found
at [Maven Repository - Server:](https://mvnrepository.com/artifact/pl.grzeslowski.jSupla/server)

```xml

<dependency>
	<groupId>pl.grzeslowski.jSupla</groupId>
	<artifactId>server</artifactId>
	<version>X.Y.Z</version>
</dependency>
```

```groovy
implementation group: 'pl.grzeslowski.jSupla', name: 'server', version: 'X.Y.Z'
```

# Examples

This section provides examples of how to use the **jSupla** library to set up a server and handle communication with
Supla devices.

## Protocol Module Usage

The proto module provides the necessary structures and codecs for communication in the Supla environment. Below is an
example of how to use these structures.

### Encoding and Decoding Example

Here is a basic example of encoding and decoding a Supla protocol message:

```java
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;

public class ProtoExample {
	public static void main(String[] args) {
		// Create encoder and decoder factories
		EncoderFactoryImpl encoderFactory = new EncoderFactoryImpl();
		DecoderFactoryImpl decoderFactory = new DecoderFactoryImpl();

		// Example structure
		SuplaRegisterDeviceResult result = new SuplaRegisterDeviceResult(
			1, // resultCode
			2, // activityTimeout
			3, // version
			4  // minVersion
		);

		// Encode the structure
		byte[] encodedData = encoderFactory.getEncoder(SuplaRegisterDeviceResult.class).encode(result);

		// Decode the structure
		SuplaRegisterDeviceResult decodedResult = (SuplaRegisterDeviceResult) decoderFactory.getDecoder(SuplaRegisterDeviceResult.class).decode(encodedData);

		// Print the decoded result
		System.out.println("Decoded Result: " + decodedResult);
	}
}
```

## Server Setup Example

To create a server using the `server` module, you can refer to the `Server` class provided in the project. This class
demonstrates how to initialize and run a server that communicates with Supla devices.

### Basic Server Setup

Check example code
in  [
`Server`](https://github.com/magx2/jSupla/blob/master/server/src/test/java/pl/grzeslowski/jsupla/server/Server.java)
class.

# Support ❤️

If you find **jSupla** helpful and would like to support the author of this project, consider buying him a coffee:

[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/S6S8UBWWY) <a href="https://buycoffee.to/magx2" target="_blank"><img src="https://buycoffee.to/btn/buycoffeeto-btn-primary.svg" alt="Postaw mi kawę na buycoffee.to" width="150"></a>
