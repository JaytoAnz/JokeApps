package com.jayto.jakmall.utils.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original: Request = chain.request()

        val request = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .method(original.method, original.body)

        return chain.proceed(request.build())
    }
}