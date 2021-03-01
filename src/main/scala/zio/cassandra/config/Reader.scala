package zio.cassandra.config

import zio.config.typesafe._, TypesafeConfigSource._
import zio.config.magnolia.DeriveConfigDescriptor.{Descriptor, descriptor}
import zio.config._

import java.io.File

object Reader {

  def readConfig(filename: String = "application.conf"): Either[ReadError[String], DBCredentials] = {
    val confFilePath = s"src/main/resources/$filename"
    val file = new File(confFilePath)
    if (file == null) println(s"file is null") else println(s"file is not null: $confFilePath")

    val automaticDescription = descriptor[DBCredentials].mapKey(toKebabCase)
    val configSource = TypesafeConfigSource.fromHoconFile(file)

    configSource.flatMap(source => read(automaticDescription from source))
  }
}
