package uz.talim.domain.repository

import uz.talim.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<String>
}
