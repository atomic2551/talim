package uz.talim.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import uz.talim.domain.model.Assignment
import java.time.Duration

@Composable
fun DeadlineTimer(
    assignment: Assignment,
    modifier: Modifier = Modifier,
) {
    val timeLeft = remember { mutableStateOf(assignment.timeLeft) }
    val totalDuration = Duration.between(assignment.createdAt, assignment.deadline)

    LaunchedEffect(key1 = assignment.id) {
        while (true) {
            delay(1000)
            timeLeft.value = assignment.timeLeft
        }
    }

    val progress = if (totalDuration.toSeconds() > 0) {
        totalDuration.minus(timeLeft.value).toSeconds().toFloat() / totalDuration.toSeconds()
    } else {
        1f
    }

    Column(modifier = modifier) {
        LinearProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            modifier = Modifier.fillMaxWidth(),
            color = when {
                timeLeft.value.toHours() < 1 -> Color.Red
                timeLeft.value.toHours() < 6 -> Color.Yellow
                else -> Color.Green
            },
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Qolgan vaqt: ${assignment.timeLeftFormatted}",
            style = MaterialTheme.typography.labelSmall,
        )
    }
}
