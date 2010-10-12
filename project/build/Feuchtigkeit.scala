import sbt._

class FeuchtigkeitProject( info: ProjectInfo ) extends DefaultProject( info ) {
   val fscapeJobs = "de.sciss" %% "fscapejobs" % "0.10"
}
