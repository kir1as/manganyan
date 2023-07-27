package app.manganyan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.manganyan.presentation.navigation.NavigationGraph
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationGraph()
        }
    }
}