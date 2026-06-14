package com.peknight.infisical.auth

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class UniversalAuthClientSecret(value: String) extends AnyVal:
  override def toString: String = "<UniversalAuthClientSecret>"
end UniversalAuthClientSecret
object UniversalAuthClientSecret:
  given stringCodecUniversalAuthClientSecret[F[_]: Applicative]: Codec[F, String, String, UniversalAuthClientSecret] =
    Codec.map[F, String, String, UniversalAuthClientSecret](_.value)(UniversalAuthClientSecret.apply)
  given codecUniversalAuthClientSecretS[F[_]: Applicative, S: {StringType, Show}]
  : Codec[F, S, Cursor[S], UniversalAuthClientSecret] = Codec.codecS[F, S, UniversalAuthClientSecret]
end UniversalAuthClientSecret
