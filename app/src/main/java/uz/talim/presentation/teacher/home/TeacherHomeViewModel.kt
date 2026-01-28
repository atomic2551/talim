package uz.talim.presentation.teacher.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.talim.domain.model.Assignment
import uz.talim.domain.usecase.GetAssignmentsUseCase
import uz.talim.util.Resource
import javax.inject.Inject

@HiltViewModel
class TeacherHomeViewModel @Inject constructor(
    private val getAssignmentsUseCase: GetAssignmentsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TeacherHomeState())
    val state: StateFlow<TeacherHomeState> = _state.asStateFlow()

    init {
        loadAssignments()
    }

    private fun loadAssignments() {
        val groupId = "teacher_group_123"
        viewModelScope.launch {
            getAssignmentsUseCase(groupId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                            assignments = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            assignments = result.data ?: emptyList(),
                            error = null,
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message,
                        )
                    }
                }
            }
        }
    }

    fun refresh() {
        loadAssignments()
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}

data class TeacherHomeState(
    val isLoading: Boolean = false,
    val assignments: List<Assignment> = emptyList(),
    val error: String? = null,
)
