package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given

case class Secret(
                   id: Option[SecretId] = None,
                   version: Option[Int] = None,
                   workspace: Option[ProjectId] = None,
                   project: Option[ProjectId] = None,
                   environment: Option[EnvironmentSlug] = None,
                   secretPath: Option[SecretPath] = None,
                   secretName: SecretName,
                   secretValue: Option[SecretValue] = None,
                   secretComment: Option[String] = None,
                   `type`: Option[SecretType] = None
                 )
object Secret:
  given codecSecret[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], Secret] = Codec.derived[F, S, Secret]
end Secret
