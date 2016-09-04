# Client-Server-Model-Android-App
Purpose: demonstrate Json-RPC

The project includes a movie information collection service and a pair of Android clients of the service.
Communication between the client and service is done using JSON-RPC.
The purpose of the example is to demonstrate JSON and JSON-RPC. Reference
the following sources for background on these technologies:

JSON (JavaScript Object Notation):
 http://en.wikipedia.org/wiki/JSON
 The JSON web site: http://json.org/

JSON-RPC (JSON Remote Procedure Call):
 http://www.jsonrpc.org
 http://en.wikipedia.org/wiki/JSON-RPC

This example depends on the following frameworks:
1. Ant
   see: http://ant.apache.org/
2. Json for the jdk as implemented by Doug Crockford.
   See: https://github.com/stleary/JSON-java

The movie collection service is deployed as a stand alone Java app.

To build and run the example, you will need to have Ant installed on
your system.

If you don't already have Ant, see:
http://ant.apache.org/

This example assumes that you will use Ant from the command line to build
the client and service. The Ant build file includes targets to compile the
service.

ant build.java.server

run the server from the command line with the statement:

java -cp classes:lib/json.jar ser423.movie.server.MovieServer 8080
