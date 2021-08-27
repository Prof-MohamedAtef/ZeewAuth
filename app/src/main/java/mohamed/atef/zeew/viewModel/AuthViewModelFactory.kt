package mohamed.atef.zeew.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mohamed.atef.zeew.repository.Repository

class AuthViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(repository)as T
    }
}