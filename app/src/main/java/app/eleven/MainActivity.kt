package app.eleven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.data.EmptyGroup.name
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.eleven.presentation.ui.theme.FirstActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", "John Doe", "AA 123", "05-05-2023", "A1", "1A")
                }
            }
        }
    }
}

@Composable
fun Greeting(
    title: String, passengerName: String,
    flightNumber: String, departureTime: String,
    gate: String, seat: String,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0x6A66A3FF))
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp)

            )
            Image(
                painter = painterResource(id = R.drawable.qrcode_1),
                contentDescription = "Qrcode",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .scale(1.1f)
            )
        }

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White)
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            Text(
                text = "Passenger name: $passengerName",
                modifier = modifier
            )
            Text(
                text = "Flight Number: $flightNumber",
                modifier = modifier
            )
            Text(
                text = "Departure Time: $departureTime",
                modifier = modifier
            )
            Text(
                text = "Gate: $gate",
                modifier = modifier
            )
            Text(
                text = "Seat: $seat",
                modifier = modifier
            )
        }


    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstActivityTheme {
        Greeting("Show at Boarding Gate", "John Doe", "AA 123", "05-05-2023", "A1", "1A")
    }
}