package zio.cassandra.config

import zio.test._
import zio.test.Assertion._
import zio.ZIO

object ReaderSpec extends DefaultRunnableSpec {
  def spec = suite("ReaderSpec")(

    testM("Reading a configuration file succeeds with the required data") {
      val actual = ApplicationConfig(DBCredentials("localhost", 9042, "syndeia_cloud_store", "syndeia_admin", "syndeia4all"))
      assertM(Reader.readConfig())(equalTo(actual))
    }
  )
}
