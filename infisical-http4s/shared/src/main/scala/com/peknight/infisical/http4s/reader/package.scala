package com.peknight.infisical.http4s

import cats.data.Kleisli
import cats.effect.Concurrent
import cats.syntax.functor.*
import com.peknight.codec.reader.{Key, Reader}
import com.peknight.error.syntax.applicativeError.asError
import com.peknight.infisical.api.secret.GetSecretRequest
import com.peknight.infisical.{SecretContext, SecretKey, api}
import org.http4s.client.Client

package object reader:
  def apply[F[_]](f: Key => String)(using context: SecretContext)(using Client[F], Concurrent[F]): Reader[F, String] =
    val secretApi: api.SecretApi[F] = SecretApi[F](context.infisicalUri, context.serviceToken)
    Kleisli(key => secretApi.getSecret(GetSecretRequest(SecretKey(f(key)), context.projectId, context.environment,
      context.secretPath)).map(_.data.flatMap(_.secret.secretValue).map(_.value)).asError)
end reader