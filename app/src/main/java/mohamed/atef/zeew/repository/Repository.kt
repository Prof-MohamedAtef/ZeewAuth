package mohamed.atef.zeew.repository

import mohamed.atef.zeew.data.AuthenticationServiceApi
import mohamed.atef.zeew.models.requestBody.SignInModel
import mohamed.atef.zeew.models.requestBody.SignUpModel

class Repository(private val api:AuthenticationServiceApi) {
    fun getSignInData(signInModel: SignInModel) =api.signIn(signInModel)
    fun getSignUpData(signUpModel: SignUpModel) =api.signUp(signUpModel)
}