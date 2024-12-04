import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.omidPayTechTask.R

@Composable
fun ImageAndStarSection(modifier: Modifier, image: String?, rate: Float, count: Int) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        AsyncImage(
            model = image,
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Adjust the height as needed
                .clip(RoundedCornerShape(16.dp)), // Clip to the desired shape
            onLoading = { }
        )

        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "star",
                modifier = Modifier.size(18.dp),
                tint = DarkGray
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "$rate (${count})",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = DarkGray, fontSize = 13.sp, textAlign = TextAlign.Center
                )
            )
        }
    }
}