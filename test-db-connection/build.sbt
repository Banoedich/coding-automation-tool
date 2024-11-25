name := """test-db-connection"""
organization := "example-organization"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.15"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "example-organization.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "example-organization.binders._"
//libraryDependencies ++= Seq(
//  "org.postgresql" % "postgresql" % "42.7.3",
//  "com.typesafe.slick" %% "slick" % "3.5.0",
//  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.1"
//)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.3.0",
  "org.postgresql" % "postgresql" % "42.7.3",
  "com.typesafe.slick" %% "slick" % "3.5.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.1"
)