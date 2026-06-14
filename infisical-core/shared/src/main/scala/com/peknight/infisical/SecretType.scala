package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class SecretType(value: String) extends AnyVal
object SecretType:
  val shared: SecretType = SecretType("shared")
  val personal: SecretType = SecretType("personal")
  given stringCodecSecretType[F[_]: Applicative]: Codec[F, String, String, SecretType] =
    Codec.map[F, String, String, SecretType](_.value)(SecretType.apply)
  given codecSecretTypeS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretType] =
    Codec.codecS[F, S, SecretType]
  given showSecretType: Show[SecretType] = Show.fromToString[SecretType]
end SecretType
