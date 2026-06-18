package com.peknight.infisical.auth

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.derivation.EnumCodecDerivation
import com.peknight.codec.sum.StringType

enum TokenType:
  case Bearer
end TokenType
object TokenType:
  given stringCodecTokenType[F[_]: Applicative]: Codec[F, String, String, TokenType] =
    EnumCodecDerivation.unsafeDerivedStringCodecEnum[F, TokenType]
  given codecTokenTypeS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], TokenType] =
    Codec.codecS[F, S, TokenType]
  given showTokenType: Show[TokenType] = Show.fromToString[TokenType]
end TokenType
