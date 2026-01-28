package uz.talim.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Duration
import java.time.LocalDateTime

@Parcelize
data class Assignment(
    val id: String,
    val title: String,
    val description: String,
    val groupId: String,
    val deadline: LocalDateTime,
    val maxScore: Int,
    val createdAt: LocalDateTime,
    val isSubmitted: Boolean = false,
    val score: Int? = null,
) : Parcelable {

    val isOverdue: Boolean
        get() = LocalDateTime.now().isAfter(deadline)

    val timeLeft: Duration
        get() = Duration.between(LocalDateTime.now(), deadline)

    val timeLeftFormatted: String
        get() {
            val duration = timeLeft
            return when {
                duration.isNegative -> "Muddati o'tgan"
                duration.toDays() > 0 -> "${duration.toDays()} kun"
                duration.toHours() > 0 -> "${duration.toHours()} soat"
                else -> "${duration.toMinutes()} daqiqa"
            }
        }
}
