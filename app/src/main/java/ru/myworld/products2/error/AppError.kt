package ru.myworld.products2.error

import java.io.IOException

sealed class AppError(var code: String) : RuntimeException() {
    companion object {
        fun from(e: Throwable): AppError = when (e) {
            is AppError -> e
            is IOException -> NetworkError
            else -> UnknownError
        }
    }
}

object NetworkError : AppError("error_network")
object UnknownError : AppError("error_unknown")