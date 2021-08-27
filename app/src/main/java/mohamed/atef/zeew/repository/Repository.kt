package mohamed.atef.zeew.repository

import mohamed.atef.zeew.data.AuthenticationServiceApi

class Repository(private val api:AuthenticationServiceApi) {
    fun getSignInData()=api.signIn()
    fun getSignUpData()=api.signUp()
}