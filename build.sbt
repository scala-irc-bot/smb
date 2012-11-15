// -*- scala -*-
name := "SmbBot"

organization := "net.mtgto"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers += "mtgto repos" at "http://scala-irc-bot.github.com/scala-irc-bot/maven/"

libraryDependencies := Seq(
  "net.mtgto" %% "scala-irc-bot" % "0.1.0-SNAPSHOT" % "provided",
  "org.specs2" %% "specs2" % "1.12.3" % "test",
  "org.mockito" % "mockito-all" % "1.9.0" % "test"
)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-encoding", "UTF8")
