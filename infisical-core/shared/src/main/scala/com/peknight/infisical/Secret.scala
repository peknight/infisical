package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*

case class Secret(
                   secretName: SecretName,
                   id: Option[SecretId] = None,
                   `type`: Option[SecretType] = None,
                   secretValue: Option[SecretValue] = None,
                   version: Option[Int] = None,
                   workspace: Option[ProjectId] = None,
                   environment: Option[EnvironmentSlug] = None,
                   secretPath: Option[SecretPath] = None,
                   secretComment: Option[String] = None
                 )
object Secret:
  given codecSecret[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], Secret] = Codec.derived[F, S, Secret]
end Secret
