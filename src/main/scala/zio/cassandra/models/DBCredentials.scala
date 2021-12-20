package zio.cassandra.models

case class DBCredentials(host: String, port: Int, keyspace: String, username: String, password: String)

object DBCredentials {
  val NULL_OBJECT: DBCredentials = DBCredentials("", 0, "", "", "")
}

case class ApplicationConfig(db: DBCredentials)

object ApplicationConfig {
  val NULL_OBJECT: ApplicationConfig = ApplicationConfig(DBCredentials.NULL_OBJECT)
}
