package com.peknight.infisical.secret.api

import com.peknight.infisical.{EnvironmentSlug, ProjectId, SecretName, SecretPath}

case class GetSecretRequest(secretName: SecretName, projectId: ProjectId, environment: EnvironmentSlug,
                            secretPath: Option[SecretPath] = None):
  def query: SecretQuery = SecretQuery(projectId, environment, secretPath)
end GetSecretRequest
