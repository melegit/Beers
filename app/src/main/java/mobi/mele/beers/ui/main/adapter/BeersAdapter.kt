package mobi.mele.beers.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobi.mele.beers.R
import mobi.mele.beers.databinding.BeerItemBinding
import mobi.mele.beers.extensions.basicDiffUtil
import mobi.mele.beers.extensions.inflate
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 25/12/21
 * e-mail : meleappdev@gmail.com
 */
class BeersAdapter(private val beerClickedListener: (Beer) -> Unit)  :
    RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    var beers: List<Beer> by basicDiffUtil(emptyList(), areContentsTheSame = {
        old, new -> old.id == new.id
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.beer_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.bind(beer)
        holder.itemView.setOnClickListener { beerClickedListener(beer) }
    }

    override fun getItemCount(): Int =beers.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = BeerItemBinding.bind(view)
        fun bind(beer: Beer) {
            binding.tvBeerNameBeerName.text = beer.name
            Glide
                .with(binding.root.context)
                .load(beer.image_url)
                .into(binding.imageBeer)
        }
    }
}