package zio.cassandra

import zio.Task

trait Dao[TPk, T] {

  def getAll: Task[T]

  def getByPK(pk: TPk): Task[Option[T]]

  def getCount(): Task[Long]

  def getByQuery(query: String): Task[Seq[T]]

  def insert(t: T): Task[Option[T]]

  def update(t: T): Task[Option[T]]

  def delete(t: T): Task[Option[T]]
}
