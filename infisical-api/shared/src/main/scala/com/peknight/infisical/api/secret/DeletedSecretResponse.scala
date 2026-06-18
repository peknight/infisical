package com.peknight.infisical.api.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.Secret

case class DeletedSecretResponse(secret: Secret)
object DeletedSecretResponse:
  given codecDeletedSecretResponse[F[_]: Monad, S: {ObjectType, NullType, ArrayType, BooleanType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], DeletedSecretResponse] = Codec.derived[F, S, DeletedSecretResponse]
end DeletedSecretResponse
