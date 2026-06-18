package com.peknight.infisical

import cats.{Applicative, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class RequestId(value: String) extends AnyVal
object RequestId:
  given stringCodecRequestId[F[_]: Applicative]: Codec[F, String, String, RequestId] =
    Codec.map[F, String, String, RequestId](_.value)(RequestId.apply)
  given codecRequestIdS[F[_]: Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], RequestId] =
    Codec.codecS[F, S, RequestId]
  given showRequestId: Show[RequestId] = Show.fromToString[RequestId]
end RequestId
