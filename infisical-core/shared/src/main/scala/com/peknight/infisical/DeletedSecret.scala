package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given

case class DeletedSecret(id: Option[SecretId] = None, secretName: SecretName)
object DeletedSecret:
  given codecDeletedSecret[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], DeletedSecret] = Codec.derived[F, S, DeletedSecret]
end DeletedSecret
