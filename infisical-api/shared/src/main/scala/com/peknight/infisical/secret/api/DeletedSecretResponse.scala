package com.peknight.infisical.secret.api

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given
import com.peknight.infisical.DeletedSecret

case class DeletedSecretResponse(secret: DeletedSecret)
object DeletedSecretResponse:
  given codecDeletedSecretResponse[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], DeletedSecretResponse] = Codec.derived[F, S, DeletedSecretResponse]
end DeletedSecretResponse
