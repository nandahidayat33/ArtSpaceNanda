package id.utdi.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.utdi.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // Surface container yang menggunakan warna 'latar belakang' dari tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProyekNandaCard()
                }
            }
        }
    }
}

data class Artwork(
    val title: String,
    val year: String,
    val imageResource: Int
)

@Composable
fun ProyekNandaCard() {
    var currentImageIndex by remember { mutableStateOf(0) }

    // Daftar gambar yang akan ditampilkan
    val artworks = listOf(
        Artwork("Gambar 1: Abstact Face", "Tahun Pembuatan: 2023", R.drawable.gambar1),
        Artwork("Gambar 2: Dream Guitar", "Tahun Pembuatan: 2001", R.drawable.gambar2),
        Artwork("Gambar 3: Seni Bungkam", "Tahun Pembuatan: 1998", R.drawable.gambar3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.White)
    ) {
        // Column 1: Gambar
        MyImage(imageResource = artworks[currentImageIndex].imageResource)

        // Spacer
        Spacer(modifier = Modifier.height(16.dp))

        // Column 2: Judul Gambar dan Tahun Pembuatan (Berada di tengah secara horizontal)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = artworks[currentImageIndex].title,
                    fontSize = 24.sp,
                    color = Color.Black // Ubah warna teks judul
                )

                Text(
                    text = artworks[currentImageIndex].year,
                    fontSize = 16.sp,
                    color = Color.Gray // Ubah warna teks tahun pembuatan
                )
            }
        }

        // Column 3: Tombol Tindakan (Next dan Previous)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Aksi untuk tombol Previous
                    currentImageIndex = (currentImageIndex - 1).coerceIn(0, artworks.size - 1)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    // Aksi untuk tombol Next
                    currentImageIndex = (currentImageIndex + 1).coerceIn(0, artworks.size - 1)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun MyImage(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier
            .size(600.dp) // Ini adalah perintah untuk mengatur panjang dan lebar gambar
            .padding(1.dp) // Ini adalah perintah untuk menambahkan margin sekitar gambar
    )
}

@Preview(showBackground = true)
@Composable
fun ProyekNandaCardPreview() {
    ArtSpaceTheme {
        ProyekNandaCard()
    }
}
