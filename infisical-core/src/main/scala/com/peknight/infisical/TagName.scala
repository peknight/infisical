package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class TagName(value: String) extends AnyVal
object TagName:
  given stringCodecTagName[F[_]: Applicative]: Codec[F, String, String, TagName] =
    Codec.map[F, String, String, TagName](_.value)(TagName.apply)
  given codecTagNameS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], TagName] =
    Codec.codecS[F, S, TagName]
  given showTagName: Show[TagName] = Show.fromToString[TagName]
end TagName
