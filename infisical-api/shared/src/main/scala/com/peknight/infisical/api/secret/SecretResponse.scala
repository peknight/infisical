package com.peknight.infisical.api.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.Secret

case class SecretResponse(secret: Secret)
object SecretResponse:
  given codecSecretResponse[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], SecretResponse] = Codec.derived[F, S, SecretResponse]
end SecretResponse
