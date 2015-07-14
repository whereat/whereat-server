import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

enablePlugins(JavaAppPackaging)

name := "whereat-server"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaStreamVersion = "1.0-RC4"
  val akkaVersion = "2.3.12"
  val h2Version = "1.4.187"
  val scalaTestVersion = "2.2.5"
  val slickVersion = "3.0.0"
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
    "org.slf4j"           %  "slf4j-nop"                          % slf4jVersion,
    "org.scalatest"       %% "scalatest"                          % scalaTestVersion % "test",
    "com.typesafe.akka"   %% "akka-testkit"                       % akkaVersion % "test",
    "io.spray"            %% "spray-json"                         % sprayJsonVersion
  )
}