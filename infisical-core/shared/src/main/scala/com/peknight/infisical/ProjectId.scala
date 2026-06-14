package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class ProjectId(value: String) extends AnyVal
object ProjectId:
  given stringCodecProjectId[F[_]: Applicative]: Codec[F, String, String, ProjectId] =
    Codec.map[F, String, String, ProjectId](_.value)(ProjectId.apply)
  given codecProjectIdS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], ProjectId] =
    Codec.codecS[F, S, ProjectId]
  given showProjectId: Show[ProjectId] = Show.fromToString[ProjectId]
end ProjectId
