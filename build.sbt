name := """twitter-voice"""

version := "0.1"

scalaVersion := "2.11.4"

resolvers += "spray repo" at "http://repo.spray.io"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-actor"               % "2.3.7",
  "com.typesafe.akka"      %% "akka-stream-experimental" % "1.0-M2",
  "com.typesafe.akka"      %% "akka-slf4j"               % "2.3.7",
  "com.typesafe.akka"      %% "akka-testkit"             % "2.3.7"        % "test",
  "io.spray"               %% "spray-can"                % "1.3.1",
  "io.spray"               %% "spray-client"             % "1.3.1",
  "io.spray"               %% "spray-routing"            % "1.3.1",
  "io.spray"               %% "spray-json"               % "1.3.1",
  "io.spray"               %% "spray-testkit"            % "1.3.1"        % "test",
//  "org.eigengo.monitor"     % "agent-akka"               % "0.2-SNAPSHOT",
//  "org.eigengo.monitor"     % "output-statsd"            % "0.2-SNAPSHOT",
  "org.specs2"             %% "specs2-core"              % "2.4.15"       % "test"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

parallelExecution in Test := true

//javaOptions in run += "-javaagent:" + System.getProperty("user.home") + "/.ivy2/cache/org.aspectj/aspectjweaver/jars/aspectjweaver-1.7.3.jar"

fork in run := false

connectInput in run := true

outputStrategy in run := Some(StdoutOutput)
