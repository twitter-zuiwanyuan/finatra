name := "java-server"
organization := "com.twitter"
version := "2.4.0"
scalaVersion := "2.11.8"
parallelExecution in ThisBuild := false
publishMavenStyle := true
crossPaths := false
autoScalaLibrary := false

mainClass in (Compile, packageBin) := Some("com.twitter.hello.server.HelloWorldServerMain")

lazy val versions = new {
  val finatra = "2.4.0"
  val guice = "4.0"
  val logback = "1.1.7"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case "META-INF/io.netty.versions.properties" => MergeStrategy.last
  case other => MergeStrategy.defaultMergeStrategy(other)
}

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-slf4j" % versions.finatra,
  "com.twitter" %% "inject-server" % versions.finatra,
  "com.twitter" %% "inject-core" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback,

  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.3" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % Test)
