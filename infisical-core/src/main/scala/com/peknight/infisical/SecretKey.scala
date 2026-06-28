package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class SecretKey(value: String) extends AnyVal
object SecretKey:
  given stringCodecSecretKey[F[_]: Applicative]: Codec[F, String, String, SecretKey] =
    Codec.map[F, String, String, SecretKey](_.value)(SecretKey.apply)
  given codecSecretKeyS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretKey] =
    Codec.codecS[F, S, SecretKey]
  given showSecretKey: Show[SecretKey] = Show.fromToString[SecretKey]
end SecretKey
