package com.peknight.infisical

import com.peknight.codec.config.CodecConfig
import com.peknight.query.config.QueryConfig

package object config:
  given codecConfig: CodecConfig = CodecConfig.default
  given queryConfig: QueryConfig = QueryConfig.default
end config
