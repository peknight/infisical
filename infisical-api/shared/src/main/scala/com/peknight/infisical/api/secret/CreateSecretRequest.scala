package com.peknight.infisical.api.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.*

case class CreateSecretRequest(
                                secretName: SecretKey,
                                secretValue: SecretValue,
                                projectId: ProjectId,
                                environment: EnvironmentSlug,
                                `type`: SecretType = SecretType.shared,
                                secretPath: Option[SecretPath] = None,
                                tagIds: Option[List[TagId]] = None,
                                secretComment: Option[String] = None
                              )
object CreateSecretRequest:
  given codecCreateSecretRequest[F[_]: Monad, S: {ObjectType, NullType, ArrayType, StringType, Show}]
  : Codec[F, S, Cursor[S], CreateSecretRequest] = Codec.derived[F, S, CreateSecretRequest]
end CreateSecretRequest
