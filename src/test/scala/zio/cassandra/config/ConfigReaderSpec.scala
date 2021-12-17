package zio.cassandra.config

import zio.cassandra.config.ConfigReader._
import zio.test.Assertion._
import zio.test._

object ConfigReaderSpec extends DefaultRunnableSpec {

  val confFileName: String      = "application.conf"
  val actual: ApplicationConfig =
    ApplicationConfig(DBCredentials("localhost", 9042, "syndeia_cloud_store", "syndeia_admin", "syndeia4all"))

  def spec = suite("ConfigurationSpec")(
    testM("Reading a configuration file through the Configuration service succeeds with the required data") {
      assertM(readConfig())(equalTo(actual))
    }
  ).provideCustomLayer(ConfigReaderLive.layer)
}
