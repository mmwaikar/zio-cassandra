package zio.cassandra.config

import zio.config.typesafe._, TypesafeConfigSource._
import zio.config.magnolia.DeriveConfigDescriptor.{Descriptor, descriptor}
import zio.config._

import java.io.File
import zio._

object configuration {
  type Configuration = Has[Configuration.Service]

  // Companion object exists to hold service definition and also the live implementation.
  object Configuration {
    
    trait Service {
      def readConfig(filename: String): IO[ReadError[String], ApplicationConfig]
    }

    val live: ULayer[Configuration] = ZLayer.succeed {
      new Service {
        override def readConfig(filename: String = "application.conf"): IO[ReadError[String], ApplicationConfig] = {
          val confFilePath = s"conf/$filename"
          val file         = new File(confFilePath)

          val automaticDescription = descriptor[ApplicationConfig]
          val configSource         = TypesafeConfigSource.fromHoconFile(file)

          val eitherConfig = configSource.flatMap(source => read(automaticDescription from source))
          ZIO.fromEither(eitherConfig)
        }
      }
    }
  }

  // Accessor Methods
  def readConfig(filename: String = "application.conf"): ZIO[Configuration, ReadError[String], ApplicationConfig] =
    ZIO.accessM(_.get.readConfig(filename))
}
