name := "IdiomEars"

version := "1.0"

organization := "com.samthomson"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % "0.4.1",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayReleaseOnPublish in ThisBuild := false
