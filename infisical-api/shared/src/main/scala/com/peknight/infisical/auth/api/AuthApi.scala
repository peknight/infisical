package com.peknight.infisical.auth.api

import com.peknight.infisical.auth.{UniversalAuthLoginRequest, UniversalAuthLoginResponse}

trait AuthApi[F[_]]:
  def universalAuthLogin(request: UniversalAuthLoginRequest): F[UniversalAuthLoginResponse]
end AuthApi
