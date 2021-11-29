package zio.cassandra.db

trait Repository {

  def getAll: Seq[Map[String, Any]]

  def getByPK(id: String): Option[Map[String, Any]]

  def insert(data: Map[String, Any]): Option[Map[String, Any]]

  def update(id: String, data: Map[String, Any]): Option[Map[String, Any]]

  def delete(id: String): Unit
}
