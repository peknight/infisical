package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*

case class Tag(id: TagId, slug: Option[TagSlug] = None, name: Option[TagName] = None)
object Tag:
  given codecTag[F[_]: Monad, S: {ObjectType, NullType, StringType, Show}]
  : Codec[F, S, Cursor[S], Tag] = Codec.derived[F, S, Tag]
end Tag
