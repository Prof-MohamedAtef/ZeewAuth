package mohamed.atef.zeew.viewModel

import androidx.lifecycle.ViewModel
import mohamed.atef.zeew.models.requestBody.SignInModel
import mohamed.atef.zeew.models.requestBody.SignUpModel
import mohamed.atef.zeew.repository.Repository

class AuthenticationViewModel(private val repository: Repository):ViewModel() {
    fun getSignInData(signInModel: SignInModel) =repository.getSignInData(signInModel)
    fun getSignUpData(signUpModel: SignUpModel) =repository.getSignUpData(signUpModel)
}