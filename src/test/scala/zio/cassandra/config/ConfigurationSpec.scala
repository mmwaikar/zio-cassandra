package zio.cassandra.config

import zio.test._
import zio.test.Assertion._
import zio.ZIO
import zio.cassandra.config.configuration._

object ConfigurationSpec extends DefaultRunnableSpec {
  def spec = suite("ConfigurationSpec")(

    testM("Reading a configuration file through the Configuration service succeeds with the required data") {
      val actual = ApplicationConfig(DBCredentials("localhost", 9042, "syndeia_cloud_store", "syndeia_admin", "syndeia4all"))
      assertM(Reader.readConfig())(equalTo(actual))
    }
  ).provideCustomLayer(Configuration.live)
}
