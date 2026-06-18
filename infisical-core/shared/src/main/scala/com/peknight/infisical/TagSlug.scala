package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class TagSlug(value: String) extends AnyVal
object TagSlug:
  given stringCodecTagSlug[F[_]: Applicative]: Codec[F, String, String, TagSlug] =
    Codec.map[F, String, String, TagSlug](_.value)(TagSlug.apply)
  given codecTagSlugS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], TagSlug] =
    Codec.codecS[F, S, TagSlug]
  given showTagSlug: Show[TagSlug] = Show.fromToString[TagSlug]
end TagSlug
