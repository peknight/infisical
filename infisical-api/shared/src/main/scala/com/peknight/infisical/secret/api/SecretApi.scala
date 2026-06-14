package com.peknight.infisical.secret.api

import com.peknight.infisical.secret.{CreateSecretRequest, DeleteSecretRequest, GetSecretRequest, UpdateSecretRequest}
import com.peknight.infisical.{DeletedSecretResponse, SecretResponse}

trait SecretApi[F[_]]:
  def createSecret(request: CreateSecretRequest): F[SecretResponse]
  def getSecret(request: GetSecretRequest): F[SecretResponse]
  def updateSecret(request: UpdateSecretRequest): F[SecretResponse]
  def deleteSecret(request: DeleteSecretRequest): F[DeletedSecretResponse]
end SecretApi
