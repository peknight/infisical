package com.peknight.infisical.http4s

import cats.Show
import cats.effect.Concurrent
import com.peknight.codec.Decoder
import com.peknight.codec.config.given
import com.peknight.codec.reader.Key
import com.peknight.infisical.SecretValue
import com.peknight.infisical.http4s.instances.reader.given
import org.http4s.client.Client

case class TestToken(testToken: SecretValue)
object TestToken:
  given keyDecodeTestToken[F[_]](using Client[F], Concurrent[F]): Decoder[F, Key, TestToken] =
    Decoder.derivedByKey[F, TestToken]

  given showTestToken: Show[TestToken] = Show.fromToString[TestToken]
end TestToken
