package zio.cassandra.config

case class DBCredentials(host: String, port: Int, keyspace: String, username: String, password: String)

object DBCredentials {
  val NULL_OBJECT = DBCredentials("", 0, "", "", "")
}

case class Configuration(db: DBCredentials)

object Configuration {
  val NULL_OBJECT = Configuration(DBCredentials.NULL_OBJECT)
}
