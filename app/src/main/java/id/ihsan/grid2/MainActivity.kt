package id.ihsan.grid2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.ihsan.grid2.data.DataSource
import id.ihsan.grid2.Model.Card
import id.ihsan.grid2.ui.theme.Grid2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Mengaktifkan fitur edge-to-edge pada layar
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Grid2Theme {// Menerapkan tema Grid2
                Surface(
                    modifier = Modifier
                        .fillMaxSize() // Mengisi seluruh layar
                        .statusBarsPadding(), // Mengatur padding untuk status bar
                    color = MaterialTheme.colorScheme.background // Menggunakan warna latar belakang dari tema
                ) {
                    ValueGrid( //Memanggil fungsi untuk menampilkan grid nilai
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                        )
                    )
                }
            }
        }
    }
}

// Fungsi untuk menampilkan grid dari item nilai
@Composable
fun ValueGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Menentukan dua kolom dalam grid
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        // Mengiterasi item dari DataSource dan menampilkan ValueCard untuk setiap item
        items(DataSource.values) { value ->
            ValueCard(value) // Memanggil fungsi untuk menampilkan kartu nilai
        }
    }
}

@Composable
fun ValueCard(value: Card, modifier: Modifier = Modifier) {
    Card {
        Row {
            Box {
                // Menampilkan gambar berdasarkan sumber daya
                Image(
                    painter = painterResource(id = value.imageRes),
                    contentDescription = null, // Tidak ada deskripsi konten untuk gambar
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp) // Ukuran gambar
                        .aspectRatio(1f), // Memastikan rasio aspek tetap
                    contentScale = ContentScale.Crop // Mengatur skala gambar
                )
            }

            Column {
                // Menampilkan nama dari nilai
                Text(
                    text = stringResource(id = value.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Menampilkan ikon
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null, // Deskripsi konten untuk ikon
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    // Menampilkan jumlah kursus yang tersedia
                    Text(
                        text = value.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

// Fungsi untuk menampilkan preview kartu nilai
@Preview(showBackground = true)
@Composable
fun ValuePreview() {
    Grid2Theme { // Menerapkan tema saat preview
        val value = Card(R.string.pemain1, 321, R.drawable.img1) // Contoh data untuk preview
        Column (
            modifier = Modifier.fillMaxSize(), // Mengisi seluruh ukuran tampilan
            verticalArrangement = Arrangement.Center, // Mengatur penempatan vertikal di tengah
            horizontalAlignment = Alignment.CenterHorizontally // Mengatur penempatan horizontal di tengah
        ) {
            ValueCard(value = value) // Menampilkan kartu nilai sebagai preview
        }
    }
}