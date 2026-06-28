package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class TagId(value: String) extends AnyVal
object TagId:
  given stringCodecTagId[F[_]: Applicative]: Codec[F, String, String, TagId] =
    Codec.map[F, String, String, TagId](_.value)(TagId.apply)
  given codecTagIdS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], TagId] =
    Codec.codecS[F, S, TagId]
  given showTagId: Show[TagId] = Show.fromToString[TagId]
end TagId
