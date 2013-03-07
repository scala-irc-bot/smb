// -*- scala -*-
name := "smb"

organization := "net.mtgto"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.0"

licenses := Seq("BSD license" -> url("https://github.com/scala-irc-bot/smb/blob/master/LICENSE.txt"))

resolvers += "mtgto repos" at "http://scala-irc-bot.github.com/scala-irc-bot/maven/"

libraryDependencies := Seq(
  "net.mtgto" %% "scala-irc-bot" % "0.2.0" % "provided",
  "junit" % "junit" % "4.10" % "test",
  "org.specs2" %% "specs2" % "1.14" % "test",
  "org.mockito" % "mockito-all" % "1.9.0" % "test"
)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-encoding", "UTF8")

ScctPlugin.instrumentSettings

testOptions in ScctTest += Tests.Argument(TestFrameworks.Specs2, "console", "junitxml")

org.scalastyle.sbt.ScalastylePlugin.Settings
