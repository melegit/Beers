package mobi.mele.beers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import mobi.mele.beers.R
import mobi.mele.beers.databinding.ActivityMainBinding
import mobi.mele.beers.extensions.app
import mobi.mele.beers.extensions.getViewModel
import mobi.mele.beers.ui.main.adapter.BeersAdapter
import mobi.mele.beers.ui.main.MainViewModel.UIModelBeers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var beersAdapter: BeersAdapter

    private lateinit var component: MainComponent
    private val mainViewModel: MainViewModel by lazy {
        getViewModel { component.mainViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //include component before call super in onCreate
        component = app.component.plus(MainModule())
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        beersAdapter = BeersAdapter(emptyList()){ beer ->
            Toast.makeText(this@MainActivity, beer.name, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerBeers.adapter = beersAdapter
        /*beersAdapter.beers = listOf(
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
        )*/

        mainViewModel.uiModelBeers.observe(this, ::updateUI)
        mainViewModel.navigation.observe(this,{ event ->
            event.getContentIfNotHandled()?.let {
                if(it.id != null){

                }else{
                    Toast.makeText(this, R.string.recipe_not_available,Toast.LENGTH_LONG).show()
                }
            }

        })

        beersAdapter.notifyDataSetChanged()
    }

    private fun updateUI(uiModelBeers: UIModelBeers) {

        binding.progress.visibility =
            if (uiModelBeers is UIModelBeers.Loading) View.VISIBLE else View.GONE

        if (uiModelBeers is UIModelBeers.Content) {
            beersAdapter.beers = uiModelBeers.beers
        }
        notifyChangesToAdapter()
    }

    private fun findBeers(query: String) {
        mainViewModel.doSearch(query)
    }

    private fun setRecipesInitialState() {
        mainViewModel.refresh()
    }

    private fun notifyChangesToAdapter(){
        beersAdapter.notifyDataSetChanged()
    }

}