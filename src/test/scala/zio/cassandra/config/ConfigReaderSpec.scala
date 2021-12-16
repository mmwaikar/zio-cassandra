package zio.cassandra.config

import zio.test.Assertion._
import zio.test._

object ConfigReaderSpec extends DefaultRunnableSpec {

  val actual: ApplicationConfig =
    ApplicationConfig(DBCredentials("localhost", 9042, "syndeia_cloud_store", "syndeia_admin", "syndeia4all"))

  def spec = suite("ConfigurationSpec")(
    testM("Reading a configuration file through the Configuration service succeeds with the required data") {
      assertM(Reader.readConfig())(equalTo(actual))
    }
  ).provideCustomLayer(ConfigReaderLive.layer)
}
