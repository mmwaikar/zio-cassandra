package zio.cassandra.config

import zio.cassandra.config.SessionBuilder.getSession
import zio.test.Assertion._
import zio.test._

object SessionBuilderSpec extends DefaultRunnableSpec {

  val confFileName: String = "application.conf"

  def spec = suite("SessionBuilderSpec")(
    testM("Getting a cql session through the SessionBuilder service succeeds") {

      val app = getSession(confFileName).map { session =>
        val rs    = session.execute("select count(*) from relations;")
        val row   = rs.one()
        val count = row.getLong(0)

        session.close()
        count
      }

//      assertM(getSession(confFileName))(not(equalTo(null)))
      assertM(app)(isGreaterThan(0L))
    }
  ).provideLayer(ConfigReaderLive.layer >>> SessionBuilderLive.layer)
}
