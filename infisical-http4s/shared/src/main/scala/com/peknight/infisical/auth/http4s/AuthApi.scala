package com.peknight.infisical.auth.http4s

import cats.effect.Concurrent
import com.peknight.codec.circe.sum.jsonType.given
import com.peknight.codec.http4s.circe.instances.entityDecoder.given
import com.peknight.codec.http4s.circe.instances.entityEncoder.given
import com.peknight.infisical.auth.{UniversalAuthLoginRequest, UniversalAuthLoginResponse, api}
import org.http4s.Method.POST
import org.http4s.Uri
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl

class AuthApi[F[_]: Concurrent](baseUri: Uri)(using client: Client[F]) extends api.AuthApi[F]:
  private val dsl: Http4sClientDsl[F] = Http4sClientDsl[F]
  import dsl.*

  private val universalAuthLoginUri: Uri = baseUri / "api" / "v1" / "auth" / "universal-auth" / "login"

  def universalAuthLogin(request: UniversalAuthLoginRequest): F[UniversalAuthLoginResponse] =
    client.run(POST(request, universalAuthLoginUri)).use(_.as[UniversalAuthLoginResponse])
end AuthApi
