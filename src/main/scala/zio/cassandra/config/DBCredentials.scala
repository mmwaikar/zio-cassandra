package zio.cassandra.config

case class DBCredentials(host: String, port: Int, username: String, password: String)

object DBCredentials {
  val NULL_OBJECT = DBCredentials("", 0, "", "")
}

case class DBCredentialsWrapper(db: DBCredentials)

object DBCredentialsWrapper {
  val NULL_OBJECT = DBCredentialsWrapper(DBCredentials.NULL_OBJECT)
}
