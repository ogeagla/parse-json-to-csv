import _root_.sbtassembly.Plugin.AssemblyKeys._
import _root_.sbtassembly.Plugin.{PathList, _}

assemblySettings

name := "parse-json-etl"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.3.0"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.2.2"

mainClass in assembly := Some("com.fleet.parse.etl.Main")

jarName in assembly := "ETL.jar"

outputPath in assembly := file("bin/ETL.jar")

test in assembly := {}

mergeStrategy in assembly := {
  case x if Assembly.isConfigFile(x) =>
    MergeStrategy.concat
  case PathList(ps@_*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
    MergeStrategy.rename
  case PathList("META-INF", xs@_*) =>
    MergeStrategy.discard
  case PathList("log4j.properties", xs@_*) =>
    MergeStrategy.concat
  case _ => MergeStrategy.first
}