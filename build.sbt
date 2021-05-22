ThisBuild / organization := "middleware"
ThisBuild / scalaVersion := "2.12.13"

val http4sVersion = "0.21.22"

lazy val root = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % http4sVersion
  )
)