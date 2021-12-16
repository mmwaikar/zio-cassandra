package zio.cassandra.config

import zio._
import zio.config._
import zio.config.magnolia.DeriveConfigDescriptor.descriptor
import zio.config.typesafe._

import java.io.File

trait ConfigReader {

  def readConfig(filename: String): IO[ReadError[String], ApplicationConfig]
}

case class ConfigReaderLive() extends ConfigReader {

  override def readConfig(filename: String = "application.conf"): IO[ReadError[String], ApplicationConfig] = {
    val confFilePath = s"conf/$filename"
    val file         = new File(confFilePath)

    val automaticDescription = descriptor[ApplicationConfig]
    val configSource         = TypesafeConfigSource.fromHoconFile(file)

    val eitherConfig = configSource.flatMap(source => read(automaticDescription from source))
    ZIO.fromEither(eitherConfig)
  }
}

object ConfigReaderLive {

  val layer: ULayer[Has[ConfigReader]] = ZLayer.succeed(ConfigReaderLive())
}
