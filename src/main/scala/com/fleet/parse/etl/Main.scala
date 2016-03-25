package com.fleet.parse.etl

import java.io.File

import com.github.tototoshi.csv.CSVWriter
import org.json4s._
import org.json4s.native.JsonMethods._


object Main {

  def main (args: Array[String]) {


    val field = args.length match {
      case l if l == 2 => "email"
      case _ =>
        println("Usage: java -jar ETL.jar {parse input file} {output file}")
        sys.exit(1)
    }

    val input = args(0)
    val output = args(1)

    println(s"Input: $input, Output: $output, Field to extract: $field")

    val source = scala.io.Source.fromFile(input)
    val lines = try source.mkString finally source.close()

    val parsedLines = parse(lines)

    val stuff: List[JsonAST.JValue] = parsedLines.children

    val theFields: List[String] = for {
      JObject(results) <- parsedLines
      JField("email", JString(someField)) <- results
    } yield someField

    println(s"Found ${theFields.length} matches to field: ${field}.")

    val f = new File(output)
    val writer = CSVWriter.open(f)
    theFields.foreach(value => writer.writeRow(List(value)))
    writer.close()

    println("Done.")

  }

}

object Etl {

  def main (args: Array[String]) {

    val source = scala.io.Source.fromFile("/home/octavian/github/parse-json-etl/src/main/scala/com/fleet/parse/etl/_User.json")
    val lines = try source.mkString finally source.close()

    val parsedLines = parse(lines)

    println(parsedLines)

    val stuff: List[JsonAST.JValue] = parsedLines.children

    val theEmails: List[String] = for {
      JObject(results) <- parsedLines
      JField("email", JString(email)) <- results
    } yield email

    println(theEmails)


    val f = new File("/home/octavian/github/parse-json-etl/emails.csv")

    val writer = CSVWriter.open(f)

    theEmails.foreach(email => writer.writeRow(List(email)))

    writer.close()




    //    println(parsedLines.values)

  }

}