package ort.clases.parcial_22a_tp3.core

import okhttp3.Interceptor
import okhttp3.Response

// cada vez que le pegues a la api este lo "intercepta" y agruega los headers necesarios
object InterceptorCustom : Interceptor {

    private val API_KEY  = Config.apiKey
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder()
            .header("X-Api-Key", API_KEY?:"")
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}