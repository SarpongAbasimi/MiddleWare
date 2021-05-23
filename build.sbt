ThisBuild / organization := "middleware"
ThisBuild / scalaVersion := "2.12.13"

val http4sVersion        = "0.21.22"
val log4catsVersion      = "1.0.1"
val log4catsSlf4jVersion = "1.0.1"

lazy val root = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "org.http4s"        %% "http4s-dsl"          % http4sVersion,
    "org.http4s"        %% "http4s-blaze-server" % http4sVersion,
    "org.http4s"        %% "http4s-blaze-client" % http4sVersion,
    "io.chrisdavenport" %% "log4cats-core"       % log4catsVersion,
    "io.chrisdavenport" %% "log4cats-slf4j"      % log4catsSlf4jVersion,
    "ch.qos.logback"     % "logback-classic"     % "1.2.3"
  )
)
