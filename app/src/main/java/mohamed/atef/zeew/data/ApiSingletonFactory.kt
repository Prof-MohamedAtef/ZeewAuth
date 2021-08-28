package mohamed.atef.zeew.data

import mohamed.atef.zeew.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiSingletonFactory {
    val api: AuthenticationServiceApi =Retrofit.Builder()
        .baseUrl(Constants.base_url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthenticationServiceApi::class.java)
}