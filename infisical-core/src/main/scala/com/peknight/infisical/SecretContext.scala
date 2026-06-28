package com.peknight.infisical

import cats.effect.std.Env
import cats.{Monad, MonadError, Show}
import com.peknight.auth.Token
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.effect.instances.envReader.given
import com.peknight.codec.http4s.instances.uri.given
import com.peknight.codec.reader.Key
import com.peknight.codec.sum.*
import com.peknight.codec.{Codec, Decoder}
import org.http4s.Uri

case class SecretContext(serviceToken: Token.Bearer, infisicalUri: Uri, projectId: ProjectId,
                         environment: EnvironmentSlug, secretPath: Option[SecretPath] = None)
object SecretContext:
  given codecSecretContext[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], SecretContext] = Codec.derived[F, S, SecretContext]

  given keyDecodeSecretContext[F[_]](using MonadError[F, Throwable], Env[F]): Decoder[F, Key, SecretContext] =
    Decoder.derivedByKey[F, SecretContext]
end SecretContext
