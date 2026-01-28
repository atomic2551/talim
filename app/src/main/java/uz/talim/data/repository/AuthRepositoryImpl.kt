package uz.talim.data.repository

import uz.talim.domain.repository.AuthRepository
import uz.talim.util.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun login(email: String, password: String): Resource<String> {
        return Resource.Error("Login hali ulanmagan")
    }
}
