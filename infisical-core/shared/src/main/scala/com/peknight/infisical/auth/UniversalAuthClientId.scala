package com.peknight.infisical.auth

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class UniversalAuthClientId(value: String) extends AnyVal
object UniversalAuthClientId:
  given stringCodecUniversalAuthClientId[F[_]: Applicative]: Codec[F, String, String, UniversalAuthClientId] =
    Codec.map[F, String, String, UniversalAuthClientId](_.value)(UniversalAuthClientId.apply)
  given codecUniversalAuthClientIdS[F[_]: Applicative, S: {StringType, Show}]
  : Codec[F, S, Cursor[S], UniversalAuthClientId] = Codec.codecS[F, S, UniversalAuthClientId]
end UniversalAuthClientId
