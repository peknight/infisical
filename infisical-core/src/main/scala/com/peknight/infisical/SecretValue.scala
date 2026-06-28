package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class SecretValue(value: String) extends AnyVal:
  override def toString: String = "<SecretValue>"
end SecretValue
object SecretValue:
  given stringCodecSecretValue[F[_]: Applicative]: Codec[F, String, String, SecretValue] =
    Codec.map[F, String, String, SecretValue](_.value)(SecretValue.apply)
  given codecSecretValueS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretValue] =
    Codec.codecS[F, S, SecretValue]
  given showSecretValue: Show[SecretValue] = Show.fromToString[SecretValue]
end SecretValue
