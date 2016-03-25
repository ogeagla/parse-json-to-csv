# What Is This
 - This code generates an executable jar which takes as input a Parse data export json (such as `_User.json`) and writes the values (in our case, emails) to csv.

# How To Use

 - Build the project
```
./sbt assembly
```
 - Run the jar
```
java -jar bin/ETL.jar /path/to/input/json /path/to/output/csv
```
