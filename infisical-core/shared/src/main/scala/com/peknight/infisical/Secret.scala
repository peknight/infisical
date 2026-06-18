package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*

import java.time.Instant

case class Secret(
                   secretKey: SecretKey,
                   id: Option[SecretId] = None,
                   workspace: Option[ProjectId] = None,
                   environment: Option[EnvironmentSlug] = None,
                   version: Option[Int] = None,
                   `type`: Option[SecretType] = None,
                   secretValue: Option[SecretValue] = None,
                   secretComment: Option[String] = None,
                   skipMultilineEncoding: Option[Boolean] = None,
                   createdAt: Option[Instant] = None,
                   updatedAt: Option[Instant] = None,
                   secretValueHidden: Option[Boolean] = None,
                   secretPath: Option[SecretPath] = None,
                   tags: Option[List[Tag]] = None
                 )
object Secret:
  given codecSecret[F[_]: Monad, S: {ObjectType, NullType, ArrayType, BooleanType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], Secret] = Codec.derived[F, S, Secret]
end Secret
