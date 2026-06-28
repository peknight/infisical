package com.peknight.infisical.api.auth

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.auth.{UniversalAuthClientId, UniversalAuthClientSecret}

case class UniversalAuthLoginRequest(clientId: UniversalAuthClientId, clientSecret: UniversalAuthClientSecret)
object UniversalAuthLoginRequest:
  given codecUniversalAuthLoginRequest[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], UniversalAuthLoginRequest] = Codec.derived[F, S, UniversalAuthLoginRequest]
end UniversalAuthLoginRequest
