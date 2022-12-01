package net.hafiznaufalr.movie.domain.base

sealed class ResultData<out T> {
    object Loading : ResultData<Nothing>()
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Failure(val error: Throwable) : ResultData<Nothing>()
}

fun <T> ResultData<T>.getResult() = this.takeIf { it.isSuccess() }
    ?.let { it as ResultData.Success }
    ?.data

fun <T> ResultData<T>.isSuccess() = this is ResultData.Success
