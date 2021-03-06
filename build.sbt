import Dependencies._

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.codionics"
ThisBuild / organizationName := "Codionics"

lazy val root = (project in file("."))
  .settings(
    name := "zio-cassandra",
    libraryDependencies ++= Seq(
      zio,
      zioConfig,
      zioConfigTypesafe,
      zioConfigMagnolia,
      cassandraDriverCore,
      scalaTest % Test
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
