package com.peknight.infisical

import cats.{Monad, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.infisical.config.given

case class Error(override val message: String, statusCode: Option[Int] = None, error: Option[String] = None) extends com.peknight.error.Error:
  override def errorType: String = error.orElse(statusCode.map(_.toString)).getOrElse("infisical")
end Error
object Error:
  given codecError[F[_]: Monad, S: {ObjectType, NullType, NumberType, StringType, Show}]
  : Codec[F, S, Cursor[S], Error] = Codec.derived[F, S, Error]
end Error
