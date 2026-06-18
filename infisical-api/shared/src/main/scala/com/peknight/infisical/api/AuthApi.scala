package com.peknight.infisical.api

import com.peknight.infisical.api.auth.{UniversalAuthLoginRequest, UniversalAuthLoginResponse}

trait AuthApi[F[_]]:
  def universalAuthLogin(request: UniversalAuthLoginRequest): F[UniversalAuthLoginResponse]
end AuthApi
