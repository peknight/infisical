package com.peknight.infisical.auth.api

trait AuthApi[F[_]]:
  def universalAuthLogin(request: UniversalAuthLoginRequest): F[UniversalAuthLoginResponse]
end AuthApi
