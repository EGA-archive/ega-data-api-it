# Integration test for EGA-DATA-API (Rest-Assured Automation)
This is an application of Rest-Assured as a basis for API test framework. 


## Framework

### Structure
This project is your standard Maven Java project with `src` folders and `POM.xml`.

### Properties
`src/main/resources/config.properties` is a simple properties file to store various configurations


## How to Test
Before running this integration test suite the `ega-data-api` must be running somewhere and its url should be updated in `src/main/resources/`

```
$ mvn test
```


##
### Key server
The key server should have following properties updated in the properties file(keyserver.properties). These below sample files `sample.txt`, `pass.txt`, `testing.txt` & `legacy.txt` are provided in `src/test/resources/key` Copy these in your local system and update the path below as per yours.

```
ega.key.path = D:/ebi/config-files/sample.txt
ega.keypass.path = D:/ebi/config-files/pass.txt
ega.sharedpass.path = D:/ebi/config-files/testing.txt
ega.publickey.url =
ega.legacy.path = D:/ebi/config-files/legacy.txt
```






