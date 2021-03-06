name := "test"

//#enablePlugin
enablePlugins(AsciidoctorPlugin)
//#enablePlugin

//#siteSubdirName
// Puts output in `target/site/asciimd`
siteSubdirName in Asciidoctor := "asciimd"
//#siteSubdirName

TaskKey[Unit]("checkContent") := {
  val dest = (target in makeSite).value / (siteSubdirName in Asciidoctor).value
  val index = dest / "index.html"
  assert(index.exists, s"${index.getAbsolutePath} did not exist")
  val content = IO.readLines(index)
  assert(content.exists(_.contains("sbt")), s"Did not find expected content in:\n${content.mkString("\n")}")
}
