package com.jefisu.anlist.core.util

import com.jefisu.anlist.R
import io.ktor.client.plugins.*
import java.io.IOException

suspend fun <T> requestCatch(
    response: suspend () -> T
): Resource<T> = try {
    Resource.Success(response())
} catch (_: ResponseException) {
    Resource.Error(
        UiText.StringResource(R.string.oops_something_went_wrong)
    )
} catch (_: IOException) {
    Resource.Error(
        UiText.StringResource(R.string.error_couldnt_reach_server)
    )
}