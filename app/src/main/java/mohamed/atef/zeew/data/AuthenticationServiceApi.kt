package mohamed.atef.zeew.data

import io.reactivex.Single
import mohamed.atef.zeew.models.AuthenticationResponse
import retrofit2.http.POST

interface AuthenticationServiceApi {
    @POST("/v1/CustomerLogin")
    fun signIn():Single<List<SignInResponse>>

    @POST("/v1/CustomerSignUp")
    fun signUp():Single<List<AuthenticationResponse>>
}