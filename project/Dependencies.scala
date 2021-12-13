import sbt._

object Dependencies {
  val zioVersion       = "1.0.12"
  val zioConfigVersion = "1.0.10"

  lazy val zio        = "dev.zio" %% "zio"          % zioVersion
  lazy val zioTest    = "dev.zio" %% "zio-test"     % zioVersion
  lazy val zioTestSbt = "dev.zio" %% "zio-test-sbt" % zioVersion

  lazy val zioConfig         = "dev.zio" %% "zio-config"          % zioConfigVersion
  lazy val zioConfigTypesafe = "dev.zio" %% "zio-config-typesafe" % zioConfigVersion
  lazy val zioConfigMagnolia = "dev.zio" %% "zio-config-magnolia" % zioConfigVersion

  lazy val cassandraDriverCore = "com.datastax.oss" % "java-driver-core" % "4.13.0"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.2"
}
