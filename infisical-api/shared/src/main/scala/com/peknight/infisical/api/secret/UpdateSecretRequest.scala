package com.peknight.infisical.api.secret

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.*

case class UpdateSecretRequest(
                                secretName: SecretName,
                                projectId: ProjectId,
                                environment: EnvironmentSlug,
                                secretPath: Option[SecretPath] = None,
                                secretValue: Option[SecretValue] = None,
                                secretComment: Option[String] = None,
                                tagIds: Option[List[TagId]] = None
                              )
object UpdateSecretRequest:
  given codecUpdateSecretRequest[F[_]: Monad, S: {ObjectType, NullType, ArrayType, StringType, Show}]
  : Codec[F, S, Cursor[S], UpdateSecretRequest] = Codec.derived[F, S, UpdateSecretRequest]
end UpdateSecretRequest
