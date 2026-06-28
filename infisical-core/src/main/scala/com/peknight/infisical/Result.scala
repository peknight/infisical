package com.peknight.infisical

import cats.data.Ior
import cats.syntax.applicative.*
import cats.syntax.either.*
import cats.syntax.flatMap.*
import cats.syntax.functor.*
import cats.syntax.show.*
import cats.{Monad, Show}
import com.peknight.api
import com.peknight.api.pagination.None
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.*
import com.peknight.codec.{Codec, Decoder, Encoder}

case class Result[A](private val either: Either[Error, A]) extends api.Result[None.type, A]:
  def ior: com.peknight.error.Error Ior A =
    either match
      case Left(error) => Ior.left(error)
      case Right(data) => Ior.right(data)
end Result
object Result:
  given codecResult[F[_], S, A](using Monad[F], ObjectType[S], NullType[S], ArrayType[S], NumberType[S], BooleanType[S],
                                StringType[S], Encoder[F, S, A], Decoder[F, Cursor[S], A], Show[S])
  : Codec[F, S, Cursor[S], Result[A]] =
    Codec.instance[F, S, Cursor[S], Result[A]](
      result => result.either.fold(Encoder[F, S, Error].encode, Encoder[F, S, A].encode)
    )(cursor => Decoder[F, Cursor[S], A].decode(cursor).flatMap {
      case Left(error) => Decoder[F, Cursor[S], Error].decode(cursor).map {
        case Left(_) => error.asLeft
        case Right(error) => Result(error.asLeft).asRight
      }
      case Right(data) => Result(data.asRight).asRight.pure[F]
    })

  given showResult[A](using Show[A]): Show[Result[A]] = Show.show[Result[A]] {
    case Result(Right(data)) => data.show
    case Result(Left(error)) => error.show
  }

end Result
