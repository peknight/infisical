package com.peknight.infisical.secret.api

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given
import com.peknight.infisical.{EnvironmentSlug, ProjectId, SecretName, SecretPath, SecretType, SecretValue}

case class CreateSecretRequest(
                                secretName: SecretName,
                                projectId: ProjectId,
                                environment: EnvironmentSlug,
                                secretPath: Option[SecretPath] = None,
                                secretValue: SecretValue,
                                `type`: SecretType = SecretType.shared,
                                tagIds: Option[List[String]] = None,
                                secretComment: Option[String] = None
                              )
object CreateSecretRequest:
  given codecCreateSecretRequest[F[_]: Monad, S: {ObjectType, NullType, ArrayType, StringType, Show}]
  : Codec[F, S, Cursor[S], CreateSecretRequest] = Codec.derived[F, S, CreateSecretRequest]
end CreateSecretRequest
