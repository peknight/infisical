package com.peknight.infisical.secret.http4s

import cats.effect.Concurrent
import com.peknight.auth.Token
import com.peknight.codec.circe.sum.jsonType.given
import com.peknight.auth.http4s.syntax.token.toHeader
import com.peknight.codec.http4s.circe.instances.entityDecoder.given
import com.peknight.codec.http4s.circe.instances.entityEncoder.given
import com.peknight.codec.http4s.instances.segmentEncoder.given
import com.peknight.infisical.config.given
import com.peknight.infisical.secret.{CreateSecretRequest, DeleteSecretRequest, GetSecretRequest, SecretQuery, UpdateSecretRequest, api}
import com.peknight.infisical.{DeletedSecretResponse, SecretName, SecretResponse}
import com.peknight.query.http4s.syntax.id.uri.withQuery
import org.http4s.Method.{DELETE, GET, PATCH, POST}
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.{Headers, Uri}

class SecretApi[F[_]: Concurrent](baseUri: Uri, token: Token)(using client: Client[F]) extends api.SecretApi[F]:
  private val dsl: Http4sClientDsl[F] = Http4sClientDsl[F]
  import dsl.*

  private val headers: Headers = Headers(token.toHeader)

  private def secretsUri(secretName: SecretName): Uri = baseUri / "api" / "v4" / "secrets" / secretName
  private def secretsUri(secretName: SecretName, query: SecretQuery): Uri = secretsUri(secretName).withQuery[SecretQuery](query)

  def createSecret(request: CreateSecretRequest): F[SecretResponse] =
    client.run(POST(request, secretsUri(request.secretName), headers)).use(_.as[SecretResponse])

  def getSecret(request: GetSecretRequest): F[SecretResponse] =
    client.run(GET(secretsUri(request.secretName, request.query), headers)).use(_.as[SecretResponse])

  def updateSecret(request: UpdateSecretRequest): F[SecretResponse] =
    client.run(PATCH(request, secretsUri(request.secretName), headers)).use(_.as[SecretResponse])

  def deleteSecret(request: DeleteSecretRequest): F[DeletedSecretResponse] =
    client.run(DELETE(secretsUri(request.secretName, request.query), headers)).use(_.as[DeletedSecretResponse])
end SecretApi
