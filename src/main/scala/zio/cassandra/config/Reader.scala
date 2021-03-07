package zio.cassandra.config

import zio.config.typesafe._, TypesafeConfigSource._
import zio.config.magnolia.DeriveConfigDescriptor.{Descriptor, descriptor}
import zio.config._

import java.io.File

object Reader {

  def readConfig(filename: String = "application.conf"): Either[ReadError[String], ApplicationConfig] = {
    val confFilePath = s"src/main/resources/$filename"
    val file = new File(confFilePath)

    val automaticDescription = descriptor[ApplicationConfig]
    val configSource = TypesafeConfigSource.fromHoconFile(file)

    configSource.flatMap(source => read(automaticDescription from source))
  }
}
