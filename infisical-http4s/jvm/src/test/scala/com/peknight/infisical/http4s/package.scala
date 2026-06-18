package com.peknight.infisical

import cats.syntax.option.*
import com.peknight.auth.Token
import org.http4s.implicits.uri

package object http4s:
  given secretContext: SecretContext = SecretContext(
    Token.Bearer("st.ac2c3ca4-8d34-4cc5-938c-9d07138d2acd.c6b15a5f5cf92ead736cfa6ab33b8601.76a1fd2d52a5e0ad6c75b880a40fd89e"),
    uri"https://infisical.peknight.com",
    ProjectId("e1bd9423-2ba2-456f-96a8-e78893991237"),
    EnvironmentSlug("dev"),
    SecretPath("/test").some
  )
end http4s