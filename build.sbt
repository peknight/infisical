import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val infisical = (project in file("."))
  .settings(name := "infisical")
  .aggregate(infisicalCore.projectRefs *)
  .aggregate(infisicalApi.projectRefs *)
  .aggregate(infisicalHttp4s.projectRefs *)

lazy val infisicalCore = (projectMatrix in file("infisical-core"))
  .settings(name := "infisical-core")
  .settings(libraryDependencies ++= dependencies(
    peknight.codec.effect,
    peknight.codec.http4s,
    peknight.api,
    peknight.app,
    peknight.auth,
  ))
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))

lazy val infisicalApi = (projectMatrix in file("infisical-api"))
  .dependsOn(infisicalCore)
  .settings(name := "infisical-api")
  .jvmPlatform(scalaVersions = Seq(scala.scala3.version))
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))

lazy val infisicalHttp4s = (projectMatrix in file("infisical-http4s"))
  .dependsOn(infisicalApi)
  .settings(name := "infisical-http4s")
  .settings(libraryDependencies ++= dependencies(
    http4s.client,
    peknight.auth.http4s,
    peknight.codec.http4s.circe,
    peknight.query.http4s,
  ))
  .settings(libraryDependencies ++= testDependencies(
    http4s.ember.client,
    peknight.logging,
    peknight.logging.logback.config,
    scalaTest.flatSpec,
    typelevel.catsEffect.testingScalaTest,
  ))
  .jvmPlatform(
    scalaVersions = Seq(scala.scala3.version),
    settings = Seq(
      libraryDependencies ++= testDependencies(typelevel.log4Cats.slf4j),
      libraryDependencies ++= jvmTestDependencies(logback.classic),
    )
  )
  .jsPlatform(scalaVersions = Seq(scala.scala3.version))
