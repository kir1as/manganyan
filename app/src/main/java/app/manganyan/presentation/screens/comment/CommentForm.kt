package app.manganyan.presentation.screens.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.manganyan.domain.model.Comment
import app.manganyan.presentation.ui.theme.BlueYellowGradient

@Composable
fun CommentForm(onPublishComment: (Comment) -> Unit) {
    var commentText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = commentText,
            onValueChange = {
                if (it.length <= 150) {
                    commentText = it
                } else {
                    commentText = it.take(150)
                }
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            placeholder = { Text(text = "Ajouter un Commentaire") },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(brush = BlueYellowGradient)
                .clickable(onClick = {
                    val newComment =
                        Comment(userId = "userId", chapterId = "chapterId", content = commentText)
                    onPublishComment(newComment)
                }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Publier",
                color = Color.White,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
                style = MaterialTheme.typography.button
            )
        }
    }
}
