name := """onlineexam"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
lazy val myProject = (project in file("."))
 .enablePlugins(PlayJava, PlayEbean)
libraryDependencies ++=Seq( "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
							"org.apache.poi" % "poi" % "3.13",
							"javax.mail" % "mail" % "1.4",
							"org.json" % "json" % "20090211",
							"com.googlecode.json-simple" % "json-simple" % "1.1",
							"com.itextpdf" % "itextpdf" % "5.0.6",
							"com.itextpdf.tool" % "xmlworker" % "5.5.6",
							"org.apache.poi" % "poi-ooxml" % "3.9"
							
							)
