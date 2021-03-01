package zio.cassandra

// import zio._
// import zio.console._
import zio.cassandra.config.Reader

object Example extends App {

  val dbCredentialsWrapper = Reader.readConfig()

  val xx = dbCredentialsWrapper.fold(
    e => println(s"error reading config file: $e"),
    v => println(s"dbCredentials (fold): $v")
  )

  // def run(args: List[String]) = {
  //   xx.exitCode
  //   // dbCredentials.map(s => println(s"dbCredentials config: $s"))
  // }
}
