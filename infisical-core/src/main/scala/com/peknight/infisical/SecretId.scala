package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class SecretId(value: String) extends AnyVal
object SecretId:
  given stringCodecSecretId[F[_]: Applicative]: Codec[F, String, String, SecretId] =
    Codec.map[F, String, String, SecretId](_.value)(SecretId.apply)
  given codecSecretIdS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretId] =
    Codec.codecS[F, S, SecretId]
  given showSecretId: Show[SecretId] = Show.fromToString[SecretId]
end SecretId
