package com.peknight.infisical.api

import com.peknight.infisical.api.secret.*

trait SecretApi[F[_]]:
  def createSecret(request: CreateSecretRequest): F[SecretResponse]
  def getSecret(request: GetSecretRequest): F[SecretResponse]
  def updateSecret(request: UpdateSecretRequest): F[SecretResponse]
  def deleteSecret(request: DeleteSecretRequest): F[DeletedSecretResponse]
end SecretApi
