package net.hafiznaufalr.movie.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<Entity> {
    protected var params = mapOf<String, Any?>()

    abstract suspend fun build(): Entity

    suspend fun invoke(context: CoroutineContext = Dispatchers.IO) = withContext(context) {
        either(::build)
    }
}