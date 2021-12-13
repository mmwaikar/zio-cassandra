package zio.cassandra.config

import zio.config.typesafe._, TypesafeConfigSource._
import zio.config.magnolia.DeriveConfigDescriptor.{Descriptor, descriptor}
import zio.config._

import java.io.File
import zio.ZIO
import zio.IO

object Reader {

  def readConfig(filename: String = "application.conf"): IO[ReadError[String], ApplicationConfig] = {
    val confFilePath = s"conf/$filename"
    val file         = new File(confFilePath)

    val automaticDescription = descriptor[ApplicationConfig]
    val configSource         = TypesafeConfigSource.fromHoconFile(file)

    val eitherConfig = configSource.flatMap(source => read(automaticDescription from source))
    ZIO.fromEither(eitherConfig)
  }
}
