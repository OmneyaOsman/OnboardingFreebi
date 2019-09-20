package me.inassar.onboardingfreebi.feature

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.onboarding_view.view.*
import me.inassar.onboardingfreebi.R
import me.inassar.onboardingfreebi.feature.onboarding.OnBoardingPage
import me.inassar.onboardingfreebi.feature.onboarding.OnBoardingPagerAdapter
import setParallaxTransformation

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class OnBoardingView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.values().size }

    init {
        val view  = LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
        setUpSlider(view)
    }

    private fun setUpSlider(view :View) {
        with(slider) {
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page , position)
            }

            addSlideChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }


    private fun addSlideChangeListener () {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)

                    onboardingRoot.progress = newProgress
                    Log.d("Progress" , onboardingRoot.progress.toString())
                }
            }
        })
        nextBtn.setOnClickListener { }
        skipBtn.setOnClickListener { }
        startBtn.setOnClickListener { }
    }

}