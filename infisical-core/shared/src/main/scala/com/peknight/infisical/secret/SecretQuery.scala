package com.peknight.infisical.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given
import com.peknight.infisical.{EnvironmentSlug, ProjectId, SecretPath}

case class SecretQuery(projectId: ProjectId, environment: EnvironmentSlug, secretPath: Option[SecretPath] = None)
object SecretQuery:
  given codecSecretQuery[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], SecretQuery] = Codec.derived[F, S, SecretQuery]
  given showSecretQuery: Show[SecretQuery] = Show.fromToString[SecretQuery]
end SecretQuery
