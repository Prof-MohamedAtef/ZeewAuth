package mohamed.atef.zeew.data

import io.reactivex.Single
import mohamed.atef.zeew.models.requestBody.SignInModel
import mohamed.atef.zeew.models.requestBody.SignUpModel
import mohamed.atef.zeew.models.responses.AuthenticationResponse
import mohamed.atef.zeew.models.responses.LogInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationServiceApi {
    @POST("/v1/CustomerLogin")
    fun signIn(@Body signInModel:SignInModel):Single<LogInResponse>

    @POST("/v1/CustomerSignUp")
    fun signUp(@Body singUpModel: SignUpModel):Single<AuthenticationResponse>
}