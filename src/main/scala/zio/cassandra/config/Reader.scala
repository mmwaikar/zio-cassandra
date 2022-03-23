package zio.cassandra.config

import zio.config.typesafe._
import TypesafeConfigSource._
import zio.config.magnolia.DeriveConfigDescriptor.{Descriptor, descriptor}
import zio.config._

import java.io.File
import zio.ZIO
import zio.IO
import zio.cassandra.models.ApplicationConfig

object Reader {

  def readConfig(filename: String = "application.conf"): IO[ReadError[String], ApplicationConfig] = {
    val confFilePath = s"conf/$filename"
    val file         = new File(confFilePath)

    val automaticDescription = descriptor[ApplicationConfig]
    val configSource         = TypesafeConfigSource.fromHoconFile(file)

    val eitherConfig = read(automaticDescription from configSource)
    eitherConfig
  }
}
