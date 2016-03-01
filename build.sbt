import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

enablePlugins(JavaAppPackaging)

name := "whereat-server"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-Xmax-classfile-name", "128")

assemblyMergeStrategy in assembly := {
  // In the case of HikariCP, a java-6 version is loaded onto the classpath first (why?)
  // We want to use the current version, which is loaded last.
  case PathList("com", "zaxxer", xs @ _*) => MergeStrategy.last
  // In any other case we just want to use the old strategy
  case x =>
    val old = (assemblyMergeStrategy in assembly).value
    old(x)
}

libraryDependencies ++= {
  val akkaStreamVersion = "2.0.3"
  val akkaVersion = "2.3.14"
  val h2Version = "1.4.187"
  val hikariCpVersion = "2.3.12"
  val postgresqlVersion = "9.4-1201-jdbc41"
  val scalaMockVersion = "3.2"
  val scalaTestVersion = "2.2.5"
  val slickVersion = "3.1.0"
  val slf4jVersion = "1.6.4"
  val sprayJsonVersion = "1.3.2"

  Seq(
    "com.h2database"      % "h2"                                  % h2Version,
    "com.typesafe.akka"   %% "akka-actor"                         % akkaVersion,
    "com.typesafe.akka"   %% "akka-stream-experimental"           % akkaStreamVersion,
    "com.typesafe.akka"   %% "akka-http-experimental"             % akkaStreamVersion,
    "com.typesafe.akka"   %% "akka-http-core-experimental"        % akkaStreamVersion,
    "com.typesafe.akka"   %% "akka-http-testkit-experimental"     % akkaStreamVersion,
    "com.typesafe.akka"   %% "akka-http-spray-json-experimental"  % akkaStreamVersion,
    "com.typesafe.slick"  %% "slick"                              % slickVersion,
    "com.typesafe.slick"  %% "slick-hikaricp"                     % slickVersion,

    "com.zaxxer"          % "HikariCP"                            % hikariCpVersion,
    "io.spray"            %% "spray-json"                         % sprayJsonVersion,
    "org.slf4j"           %  "slf4j-nop"                          % slf4jVersion,
    "org.postgresql"      %  "postgresql"                         % postgresqlVersion,

    "org.scalamock"       %% "scalamock-scalatest-support"        % scalaMockVersion % "test",
    "org.scalatest"       %% "scalatest"                          % scalaTestVersion % "test",
    "com.typesafe.akka"   %% "akka-testkit"                       % akkaVersion % "test",
    "org.glassfish.tyrus.bundles" % "tyrus-standalone-client" % "1.12" % "test"
  )
}
