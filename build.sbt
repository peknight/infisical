import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val infisical = (project in file("."))
  .settings(name := "infisical")
  .aggregate(
    infisicalCore.jvm,
    infisicalCore.js,
    infisicalApi.jvm,
    infisicalApi.js,
    infisicalHttp4s.jvm,
    infisicalHttp4s.js,
  )

lazy val infisicalCore = (crossProject(JVMPlatform, JSPlatform) in file("infisical-core"))
  .settings(name := "infisical-core")
  .settings(crossDependencies(
    peknight.auth,
    peknight.codec.effect,
    peknight.codec.http4s,
    peknight.api,
  ))

lazy val infisicalApi = (crossProject(JVMPlatform, JSPlatform) in file("infisical-api"))
  .dependsOn(infisicalCore)
  .settings(name := "infisical-api")

lazy val infisicalHttp4s = (crossProject(JVMPlatform, JSPlatform) in file("infisical-http4s"))
  .dependsOn(infisicalApi)
  .settings(name := "infisical-http4s")
  .settings(crossDependencies(
    http4s.client,
    peknight.auth.http4s,
    peknight.codec.http4s.circe,
    peknight.query.http4s,
  ))
  .settings(crossTestDependencies(
      http4s.ember.client,
      peknight.logging,
      peknight.logging.logback.config,
      scalaTest.flatSpec,
      typelevel.catsEffect.testingScalaTest,
  ))
  .jvmSettings(libraryDependencies ++= Seq(
      testDependency(typelevel.log4Cats.slf4j),
      jvmTestDependency(logback.classic),
  ))
