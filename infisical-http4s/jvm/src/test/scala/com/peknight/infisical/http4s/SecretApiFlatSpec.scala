package com.peknight.infisical.http4s

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import cats.syntax.option.*
import com.peknight.auth.Token
import com.peknight.error.syntax.applicativeError.asET
import com.peknight.infisical.api.secret.GetSecretRequest
import com.peknight.infisical.{EnvironmentSlug, ProjectId, SecretName, SecretPath}
import com.peknight.logging.syntax.eitherT.log
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.implicits.uri
import org.scalatest.flatspec.AsyncFlatSpec
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

/**
 * Infisical Secret API 连通性测试
 *
 * 前置条件：
 * 1. Token test_read@dev 已在 https://infisical.peknight.com 创建
 * 2. Token 有权限读取 Project Test (projectId: e1bd9423-2ba2-456f-96a8-e78893991237)
 * 3. 在 /test 路径下已创建名为 TEST_SECRET 的 secret
 *
 * 注意：此 token 仅用于测试连通性，Project Test 仅用于测试，不存储真实敏感信息
 */
class SecretApiFlatSpec extends AsyncFlatSpec with AsyncIOSpec:

  val infisicalUri = uri"https://infisical.peknight.com"
  val serviceToken = Token.Bearer("st.ac2c3ca4-8d34-4cc5-938c-9d07138d2acd.c6b15a5f5cf92ead736cfa6ab33b8601.76a1fd2d52a5e0ad6c75b880a40fd89e")
  val projectId = ProjectId("e1bd9423-2ba2-456f-96a8-e78893991237")
  val environment = EnvironmentSlug("dev")
  val secretPath = SecretPath("/test")

  "Infisical SecretApi getSecret" should "successfully read a secret from /test path" in {
    val testSecretName = SecretName("TEST_TOKEN")
    val request = GetSecretRequest(testSecretName, projectId, environment, secretPath.some)
    EmberClientBuilder.default[IO].build
      .use { client =>
        given Client[IO] = client
        val eitherT =
          for
            logger <- Slf4jLogger.fromClass[IO](classOf[SecretApiFlatSpec]).asET
            given Logger[IO] = logger
            secretApi = SecretApi[IO](infisicalUri, serviceToken)
            secret <- secretApi.getSecret(request).asET.log("SecretApiFlatSpec#getSecret", request.some)
          yield
            secret
        eitherT.value
      }
      .asserting(either => assert(either.isRight))
  }

end SecretApiFlatSpec
