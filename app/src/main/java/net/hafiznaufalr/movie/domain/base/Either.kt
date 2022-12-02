package net.hafiznaufalr.movie.domain.base

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
}

suspend fun <V> either(block: suspend () -> V): Either<Throwable, V> = runCatching {
    Either.Value(block())
}.getOrElse {
    it.printStackTrace()
    Either.Error(it)
}

fun <T> Either<Throwable, T>.toResult() = when (this) {
    is Either.Error -> ResultData.Failure(this.error)
    is Either.Value -> ResultData.Success(this.value)
}
