package com.peknight.infisical.api

import com.peknight.infisical.Result
import com.peknight.infisical.api.secret.*

trait SecretApi[F[_]]:
  def createSecret(request: CreateSecretRequest): F[Result[SecretResponse]]
  def getSecret(request: GetSecretRequest): F[Result[SecretResponse]]
  def updateSecret(request: UpdateSecretRequest): F[Result[SecretResponse]]
  def deleteSecret(request: DeleteSecretRequest): F[Result[DeletedSecretResponse]]
end SecretApi
