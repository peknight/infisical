package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.http4s.instances.status.given
import com.peknight.codec.sum.*
import com.peknight.infisical.RequestId
import org.http4s.Status

case class Error(override val message: String, reqId: Option[RequestId] = None, statusCode: Option[Status] = None,
                 error: Option[String] = None)
  extends com.peknight.error.Error:
  override def errorType: String = error.orElse(statusCode.map(_.toString)).getOrElse("infisical")
end Error
object Error:
  given codecError[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]: Codec[F, S, Cursor[S], Error] =
    Codec.derived[F, S, Error]

  given showError: Show[Error] = Show.fromToString[Error]
end Error
