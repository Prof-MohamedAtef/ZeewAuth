package mohamed.atef.zeew.viewModel

import androidx.lifecycle.ViewModel
import mohamed.atef.zeew.repository.Repository

class AuthenticationViewModel(private val repository: Repository):ViewModel() {
    fun getSignInData()=repository.getSignInData()
    fun getSignUpData()=repository.getSignUpData()
}