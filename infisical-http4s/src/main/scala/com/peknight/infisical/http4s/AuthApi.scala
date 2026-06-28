package com.peknight.infisical.http4s

import cats.effect.Concurrent
import com.peknight.codec.circe.sum.jsonType.given
import com.peknight.codec.http4s.circe.instances.entityDecoder.given
import com.peknight.codec.http4s.circe.instances.entityEncoder.given
import com.peknight.infisical.api.auth.{UniversalAuthLoginRequest, UniversalAuthLoginResponse}
import com.peknight.infisical.{Result, api}
import org.http4s.Method.POST
import org.http4s.Uri
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl

class AuthApi[F[_]](baseUri: Uri)(using client: Client[F], concurrent: Concurrent[F]) extends api.AuthApi[F]:
  private val dsl: Http4sClientDsl[F] = Http4sClientDsl[F]
  import dsl.*

  def universalAuthLogin(request: UniversalAuthLoginRequest): F[Result[UniversalAuthLoginResponse]] =
    val uri: Uri = baseUri / "api" / "v1" / "auth" / "universal-auth" / "login"
    client.run(POST(request, uri)).use(_.as[Result[UniversalAuthLoginResponse]])
end AuthApi
