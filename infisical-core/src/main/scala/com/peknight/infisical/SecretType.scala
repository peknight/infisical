package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.derivation.EnumCodecDerivation
import com.peknight.codec.sum.StringType

enum SecretType:
  case shared, personal
end SecretType
object SecretType:
  given stringCodecSecretType[F[_]: Applicative]: Codec[F, String, String, SecretType] =
    EnumCodecDerivation.unsafeDerivedStringCodecEnum[F, SecretType]
  given codecSecretTypeS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], SecretType] =
    Codec.codecS[F, S, SecretType]
  given showSecretType: Show[SecretType] = Show.fromToString[SecretType]
end SecretType
