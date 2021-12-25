package mobi.mele.beers.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobi.mele.beers.databinding.BeerItemBinding
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 25/12/21
 * e-mail : meleappdev@gmail.com
 */
class BeersAdapter(var beers: List<Beer>,
                   private val beerClickedListener: (Beer) -> Unit)  :
    RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BeerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.bind(beer)
        holder.itemView.setOnClickListener { beerClickedListener(beer) }
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    class ViewHolder(private val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(beer: Beer) {
            binding.tvBeerNameBeerName.text = beer.name
            Glide
                .with(binding.root.context)
                .load(beer.image_url)
                .into(binding.imageBeer)
        }
    }
}