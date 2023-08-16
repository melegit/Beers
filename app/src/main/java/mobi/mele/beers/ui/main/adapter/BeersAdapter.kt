package mobi.mele.beers.ui.main.adapter

import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import coil.compose.rememberAsyncImagePainter
import mobi.mele.beers.extensions.basicDiffUtil
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 25/12/21
 * e-mail : meleappdev@gmail.com
 */
class BeersAdapter(private val beerClickedListener: (Beer) -> Unit) :
    RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    var beers: List<Beer> by basicDiffUtil(emptyList(), areContentsTheSame = { old, new ->
        old.id == new.id
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ComposeView(parent.context), beerClickedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.bind(beer)
        holder.itemView.setOnClickListener { beerClickedListener(beer) }
    }

    override fun getItemCount(): Int = beers.size

    override fun onViewRecycled(holder: ViewHolder) {
        holder.composeView.disposeComposition()
    }

    class ViewHolder(
        val composeView: ComposeView,
        private val beerClickedListener: (Beer) -> Unit
    ) : RecyclerView.ViewHolder(composeView) {
        init {
            composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }

        fun bind(beer: Beer) {
            composeView.setContent {
                MaterialTheme {
                    BeerItem(
                        modifier = Modifier.clickable { beerClickedListener(beer) },
                        beer = beer
                    )
                }
            }
            /*binding.tvBeerNameBeerName.text = beer.name
            Glide
                .with(binding.root.context)
                .load(beer.image_url)
                .into(binding.imageBeer)*/
        }
    }
}

@Composable
fun BeerItem(modifier: Modifier = Modifier, beer: Beer) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .padding(top = 20.dp),
                painter = rememberAsyncImagePainter(model = beer.image_url),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                text = beer.name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }
    }
}