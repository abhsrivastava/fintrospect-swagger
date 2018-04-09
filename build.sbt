val fintrospectVersion = "14.21.2"
val prj = (project in file(".")).
settings(
  name := "fintrospect-swagger",
  organization := "com.abhi",
  version := "1.0.0",
  scalaVersion := "2.12.5",  
  libraryDependencies ++= Seq(
    "io.fintrospect" %% "fintrospect-core" % fintrospectVersion,
    "io.fintrospect" %% "fintrospect-circe" % fintrospectVersion
  )
)