package mobi.mele.beers.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import mobi.mele.beers.R
import mobi.mele.beers.databinding.ActivityDetailBinding
import mobi.mele.beers.extensions.app
import mobi.mele.beers.extensions.getViewModel
import mobi.mele.beers.extensions.loadUrl
import mobi.mele.beers.parcelizeobject.BeerParcelize

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var component: DetailComponent
    private val detailViewModel: DetailViewModel by lazy {
        getViewModel { component.detailViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component = app.component.plus(DetailModule(intent.getParcelableExtra<BeerParcelize>("BEER")))

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

    private fun updateUI(uiModelBeer: DetailViewModel.UiModelBeer) {
        val beer= uiModelBeer.beer

        with(binding) {
            collapsingToolbar.title = beer?.name
            beer?.image_url.let {
                if (it != null) {
                    imageBeer?.loadUrl(it)
                }
            }
            abv.text = "ABV: ${beer?.abv}%"
            description.text = beer?.description
        }
    }
}