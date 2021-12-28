package mobi.mele.beers.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import mobi.mele.beers.R
import mobi.mele.beers.databinding.ActivityDetailBinding
import mobi.mele.beers.extensions.app
import mobi.mele.beers.extensions.getViewModel
import mobi.mele.beers.extensions.loadUrl
import mobi.mele.beers.extensions.startActivity
import mobi.mele.beers.parcelizeobject.BeerParcelize
import mobi.mele.beers.ui.detail.DetailViewModel.UiModelBeer
import mobi.mele.beers.ui.main.MainViewModel
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class DetailActivity : AppCompatActivity() {

    companion object {
        const val BEER = "DetailActivity:beer"
    }

    private lateinit var binding: ActivityDetailBinding

    private lateinit var component: DetailComponent
    private val detailViewModel: DetailViewModel by lazy {
        getViewModel { component.detailViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component = app.component.plus(DetailModule(intent.getIntExtra(BEER, -1)))

        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            setSupportActionBar(detailToolbar)
            detailToolbar.setNavigationOnClickListener { onBackClicked() }
        }

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel.uiModelBeer.observe(this, Observer(this::updateUI))
    }

    private fun onBackClicked() {
        finish()
        overridePendingTransition(0, R.anim.slide_out)
    }

    private fun updateUI(uiModelBeer: UiModelBeer) {
        binding.progress.visibility =
            if (uiModelBeer is UiModelBeer.Loading) View.VISIBLE else View.GONE

        when (uiModelBeer){
            is UiModelBeer.Content -> {
                with(binding) {
                    collapsingToolbar.title = uiModelBeer.beer[0].name
                    imageBeer.loadUrl(uiModelBeer.beer[0].image_url)
                    abv.text = "ABV: ${uiModelBeer.beer[0].abv}%"
                    description.text = uiModelBeer.beer[0].description
                }
            }
        }
    }
}
