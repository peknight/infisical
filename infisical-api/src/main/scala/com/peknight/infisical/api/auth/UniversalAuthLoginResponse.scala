package com.peknight.infisical.api.auth

import cats.{Monad, Show}
import com.peknight.auth.Token
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.instances.time.finiteDuration.ofSeconds
import com.peknight.codec.number.Number
import com.peknight.codec.sum.*
import com.peknight.codec.{Codec, Decoder, Encoder}
import com.peknight.infisical.auth.TokenType

import scala.concurrent.duration.{Duration, FiniteDuration}

case class UniversalAuthLoginResponse(accessToken: Token.Bearer, expiresIn: Duration, accessTokenMaxTTL: Duration,
                                      tokenType: TokenType)
object UniversalAuthLoginResponse:
  given codecUniversalAuthLoginResponse[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], UniversalAuthLoginResponse] =
    given Encoder[F, S, Duration] = Encoder.encodeN[F, S, Duration](using Encoder.map[F, Number, Duration] {
      case infinite: Duration.Infinite => Number.fromInt(0)
      case duration: FiniteDuration => Number.fromLong(duration.toSeconds)
    })
    given Decoder[F, Cursor[S], Duration] = Decoder.decodeNS[F, S, Duration](using Decoder
      .numberDecodeNumberOption[F, Duration](_.toBigDecimal.map(seconds =>
        if seconds <= 0 then Duration.Inf else ofSeconds(seconds)
      )))
    Codec.derived[F, S, UniversalAuthLoginResponse]
end UniversalAuthLoginResponse
