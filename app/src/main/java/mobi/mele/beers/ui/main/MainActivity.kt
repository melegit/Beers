package mobi.mele.beers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mobi.mele.beers.databinding.ActivityMainBinding
import mobi.mele.beers.ui.main.adapter.BeersAdapter
import mobi.mele.domain.dto.Beer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var beersAdapter: BeersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        beersAdapter = BeersAdapter(emptyList()){ beer ->
            Toast.makeText(this@MainActivity, beer.name, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerBeers.adapter = beersAdapter
        beersAdapter.beers = listOf(
            Beer(id = 1, name = "Buzz", abv = 4.5,
                description = "A light, crisp and bitte…batch brewed only once.", image_url = "https://images.punkapi.com/v2/keg.png" ),
            Beer(id = 2, name = "Trashy Blonde", abv = 4.1,
                description = "A titillating, neurotic,… and imaginative twist.", image_url = "https://images.punkapi.com/v2/2.png" ),
            Beer(id = 3, name = "Berliner Weisse With Yuzu - B-Sides", abv = 4.2,
                description = "Japanese citrus fruit in…of this German classic.", image_url = "https://images.punkapi.com/v2/keg.png" ),
            Beer(id = 4, name = "Pilsen Lager", abv = 6.3,
                description = "Our Unleash the Yeast se…a hint of butterscotch.", image_url = "https://images.punkapi.com/v2/4.png" ),
            Beer(id = 5, name = "Avery Brown Dredge", abv = 3.5,
                description = "An Imperial Pilsner in c…e people who make them." , image_url = "https://images.punkapi.com/v2/5.png" )
        )

        beersAdapter.notifyDataSetChanged()
    }
}