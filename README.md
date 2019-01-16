# Integration test for EGA-DATA-API (Rest-Assured Automation)
This is an application of Rest-Assured as a basis for API test framework. 


## Framework

### Structure
This project is your standard Maven Java project with `src` folders and `POM.xml`.

### Properties
`src/main/resources/config.properties` is a simple properties file to store various configurations


## How to Test
The `ega-data-api` application must be running somewhere before running this integration test suite. To run the integration test use below command

```
$ mvn test
```

The default value for properties are written is pom.xml inside `<systemPropertyVariables>` for example the key server host is assumed to be running on `http://localhost`. To override this value at run time use below command

```
$ mvn test "-Dkey.uri=http://localhost2"
```

## Configurations
### Key server
The key server should have following properties updated in the properties file(keyserver.properties). These below sample files `sample.txt`, `pass.txt`, `testing.txt` & `legacy.txt` are provided in `src/test/resources/key` Copy these in your local system and update the path below as per yours.

```
ega.key.path = D:/ebi/config-files/sample.txt
ega.keypass.path = D:/ebi/config-files/pass.txt
ega.sharedpass.path = D:/ebi/config-files/testing.txt
ega.publickey.url =
ega.legacy.path = D:/ebi/config-files/legacy.txt
```
