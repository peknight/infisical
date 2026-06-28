package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.app.Environment
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class EnvironmentSlug(value: String) extends AnyVal
object EnvironmentSlug:
  def apply(environment: Environment): EnvironmentSlug = EnvironmentSlug(environment.slug)
  given stringCodecEnvironmentSlug[F[_]: Applicative]: Codec[F, String, String, EnvironmentSlug] =
    Codec.map[F, String, String, EnvironmentSlug](_.value)(EnvironmentSlug.apply)
  given codecEnvironmentSlugS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], EnvironmentSlug] =
    Codec.codecS[F, S, EnvironmentSlug]
  given showEnvironmentSlug: Show[EnvironmentSlug] = Show.fromToString[EnvironmentSlug]
end EnvironmentSlug
