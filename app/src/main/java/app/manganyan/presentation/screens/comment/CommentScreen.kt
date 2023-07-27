package app.manganyan.presentation.screens.comment

import android.widget.Toast

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun CommentScreen(
    viewModel: CommentViewModel = hiltViewModel(),
    navController: NavController,
) {

    val comments = remember { mutableStateListOf<String>() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.commentState.collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
        CommentForm(onPublishComment = { comment ->
            scope.launch {
                viewModel.postComment(comment)
            }

            comments.add(comment.content)
        })
        CommentList(comments)
        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}