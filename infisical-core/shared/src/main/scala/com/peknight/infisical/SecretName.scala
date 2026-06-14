package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class SecretName(value: String) extends AnyVal
object SecretName:
  given stringCodecSecretName[F[_]: Applicative]: Codec[F, String, String, SecretName] =
    Codec.map[F, String, String, SecretName](_.value)(SecretName.apply)
  given codecSecretNameS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretName] =
    Codec.codecS[F, S, SecretName]
  given showSecretName: Show[SecretName] = Show.fromToString[SecretName]
end SecretName
