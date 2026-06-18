package com.peknight.infisical.http4s.instances

import cats.effect.Concurrent
import com.peknight.codec.reader.Reader
import com.peknight.commons.text.cases.{ScreamingSnakeCase, split}
import com.peknight.infisical.SecretContext
import com.peknight.infisical.http4s.reader.apply as applyReader
import org.http4s.client.Client

trait ReaderInstances:
  given reader[F[_]](using context: SecretContext)(using Client[F], Concurrent[F]): Reader[F, String] =
    applyReader(key => ScreamingSnakeCase.join(key.keys.toSeq.flatMap(split)))
end ReaderInstances
object ReaderInstances extends ReaderInstances
