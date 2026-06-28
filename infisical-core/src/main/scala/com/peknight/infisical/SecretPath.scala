package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class SecretPath(value: String) extends AnyVal
object SecretPath:
  given stringCodecSecretPath[F[_]: Applicative]: Codec[F, String, String, SecretPath] =
    Codec.map[F, String, String, SecretPath](_.value)(SecretPath.apply)
  given codecSecretPathS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretPath] =
    Codec.codecS[F, S, SecretPath]
  given showSecretPath: Show[SecretPath] = Show.fromToString[SecretPath]
end SecretPath
