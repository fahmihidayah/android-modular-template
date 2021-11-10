package com.widsons.core.remote.conf

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created on : November/08/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
class AuthInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        var responseLogObject = ResponseLogObject()
        var request = chain.request()
//        responseLogObject.header = request.headers().names().toString()
//        responseLogObject.httpMethod = request.method()
//        responseLogObject.url = request.url().uri()?.toString()?:""
//        responseLogObject.parameters = request.url().encodedQuery()?:""
        val requestBuilder = request.newBuilder()
            .addHeader("Accept-Version", "2")
            .addHeader("Content-Type", "application/json")
//            .addHeader("Accept-Language", appManager.locale)
//        println("token ${appManager.user?.authKey}")
//        if(appManager.user?.authKey?.isNotEmpty() == true)
//            requestBuilder.addHeader("Authorization","Bearer ${appManager.user?.authKey}")
        var buildRequest = requestBuilder.build()
        var response = chain.proceed(buildRequest)
//        responseLogObject.header = buildRequest.headers()?.toString()?:""
//        responseLogObject.body = response.peekBody(Long.MAX_VALUE).string()
//        checkCode(response)
//        exceptionManager.setCurrentResponse(responseLogObject)
        return response
    }

//    fun checkCode(response : Response){
//        if(response.code() == 401) {
//            appManager.logout()
//        }
//    }
}