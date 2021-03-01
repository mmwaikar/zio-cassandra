import sbt._

object Dependencies {
  val zioConfigVersion = "1.0.0"

  lazy val zio = "dev.zio" %% "zio" % "1.0.4"
  lazy val zioConfig = "dev.zio" %% "zio-config" % zioConfigVersion
  lazy val zioConfigTypesafe = "dev.zio" %% "zio-config-typesafe" % zioConfigVersion
  lazy val zioConfigMagnolia = "dev.zio" %% "zio-config-magnolia" % zioConfigVersion

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.2"
}
