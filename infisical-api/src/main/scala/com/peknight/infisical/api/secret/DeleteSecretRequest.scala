package com.peknight.infisical.api.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.{NullType, ObjectType, StringType}
import com.peknight.infisical.{EnvironmentSlug, ProjectId, SecretKey, SecretPath}

case class DeleteSecretRequest(secretName: SecretKey, projectId: ProjectId, environment: EnvironmentSlug,
                               secretPath: Option[SecretPath] = None):
  def query: SecretQuery = SecretQuery(projectId, environment, secretPath)
end DeleteSecretRequest
object DeleteSecretRequest:
  given codecDeleteSecretRequest[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], DeleteSecretRequest] = Codec.derived[F, S, DeleteSecretRequest]
end DeleteSecretRequest
