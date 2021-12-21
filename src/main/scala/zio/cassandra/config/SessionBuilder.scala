package zio.cassandra.config

import com.datastax.oss.driver.api.core.CqlSession
import zio._
import zio.cassandra.models.ApplicationConfig

import java.net.InetSocketAddress

trait SessionBuilder {

  def getSession(filename: String): IO[String, CqlSession]
}

case class SessionBuilderLive(reader: ConfigReader) extends SessionBuilder {

  override def getSession(filename: String = "application.conf"): IO[String, CqlSession] = {
    for {
      config <- reader.readConfig(filename).mapError(readErrorString => readErrorString.toString())
    } yield getCqlSession(config)
  }

  private def getCqlSession(config: ApplicationConfig): CqlSession = {
    CqlSession
      .builder()
      .withLocalDatacenter("datacenter1")
      .addContactPoint(new InetSocketAddress(config.db.host, config.db.port))
      .withKeyspace(config.db.keyspace)
      .withAuthCredentials(config.db.username, config.db.password)
      .build()
  }
}

object SessionBuilderLive {

  val layer: URLayer[Has[ConfigReader], Has[SessionBuilder]] = (SessionBuilderLive(_)).toLayer
}

object SessionBuilder {

  def getSession(filename: String): ZIO[Has[SessionBuilder], String, CqlSession] =
    ZIO.serviceWith[SessionBuilder](_.getSession(filename))
}
