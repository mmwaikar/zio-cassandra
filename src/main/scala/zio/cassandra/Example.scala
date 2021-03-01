package zio.cassandra

import zio._
import zio.console._
import zio.cassandra.config.Reader

object Example extends zio.App {

  val dbCredentialsWrapper = Reader.readConfig()

  val program = dbCredentialsWrapper.fold(
    e => putStrLn(s"error reading config file: $e"),
    v => putStrLn(s"dbCredentials: $v")
  )

  def run(args: List[String]) = {
    program.exitCode
  }
}
