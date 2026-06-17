package com.peknight.infisical.auth.api

import cats.{Monad, Show}
import com.peknight.auth.Token
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given

case class UniversalAuthLoginResponse(accessToken: Token.Bearer, expiresIn: Int, accessTokenMaxTTL: Int,
                                      tokenType: String)
object UniversalAuthLoginResponse:
  given codecUniversalAuthLoginResponse[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], UniversalAuthLoginResponse] = Codec.derived[F, S, UniversalAuthLoginResponse]
end UniversalAuthLoginResponse
