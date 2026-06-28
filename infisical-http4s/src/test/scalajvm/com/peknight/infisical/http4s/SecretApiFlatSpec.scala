package com.peknight.infisical.http4s

import cats.data.EitherT
import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import cats.syntax.option.*
import com.peknight.api.syntax.result.asET as resultAsET
import com.peknight.codec.Decoder
import com.peknight.error.syntax.applicativeError.asET
import com.peknight.infisical.SecretKey
import com.peknight.infisical.api.secret.GetSecretRequest
import com.peknight.logging.syntax.eitherT.log
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
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

  "Infisical SecretApi getSecret" should "successfully read a secret from /test path" in {
    val testSecretName = SecretKey("TEST_TOKEN")
    val request = GetSecretRequest(testSecretName, secretContext.projectId, secretContext.environment,
      secretContext.secretPath)
    EmberClientBuilder.default[IO].build
      .use { client =>
        given Client[IO] = client
        val eitherT =
          for
            logger <- Slf4jLogger.fromClass[IO](classOf[SecretApiFlatSpec]).asET
            given Logger[IO] = logger
            secretApi = SecretApi[IO](secretContext.infisicalUri, secretContext.serviceToken)
            secret <- secretApi.getSecret(request).resultAsET.log("SecretApiFlatSpec#getSecret", request.some)
          yield
            secret
        eitherT.value
      }
      .asserting(either => assert(either.isRight))
  }

  "Infisical SecretApi loadTestToken" should "successfully load by key" in {
    EmberClientBuilder.default[IO].build
      .use { client =>
        given Client[IO] = client
        val eitherT =
          for
            logger <- Slf4jLogger.fromClass[IO](classOf[SecretApiFlatSpec]).asET
            given Logger[IO] = logger
            testToken <- EitherT(Decoder.load[IO, TestToken]()).log[Unit]("SecretApiFlatSpec#loadTestToken")
          yield
            testToken
        eitherT.value
      }
      .asserting(either => assert(either.isRight))
  }
end SecretApiFlatSpec
