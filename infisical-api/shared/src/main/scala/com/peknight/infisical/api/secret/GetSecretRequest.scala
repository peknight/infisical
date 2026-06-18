package com.peknight.infisical.api.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.{NullType, ObjectType, StringType}
import com.peknight.infisical.{EnvironmentSlug, ProjectId, SecretName, SecretPath}

case class GetSecretRequest(secretName: SecretName, projectId: ProjectId, environment: EnvironmentSlug,
                            secretPath: Option[SecretPath] = None):
  def query: SecretQuery = SecretQuery(projectId, environment, secretPath)
end GetSecretRequest
object GetSecretRequest:
  given codecGetSecretRequest[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], GetSecretRequest] = Codec.derived[F, S, GetSecretRequest]

  given showGetSecretRequest: Show[GetSecretRequest] = Show.fromToString[GetSecretRequest]
end GetSecretRequest
