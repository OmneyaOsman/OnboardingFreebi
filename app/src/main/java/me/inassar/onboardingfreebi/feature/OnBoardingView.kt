package me.inassar.onboardingfreebi.feature

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.onboarding_view.view.*
import me.inassar.onboardingfreebi.R
import me.inassar.onboardingfreebi.feature.onboarding.OnBoaradingPagerAdapter
import me.inassar.onboardingfreebi.feature.onboarding.OnBoardingPage
import me.inassar.onboardingfreebi.hide
import me.inassar.onboardingfreebi.show
import setParallaxTransformation

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class OnBoardingView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.values().size }

    init {
        LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
        setUpListeners()
        setUpSlider()
    }

    private fun setUpSlider() {
        with(slider) {
            adapter = OnBoaradingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page , position)
            }
        }
    }


    private fun setUpListeners() {
//        page_indicator.setViewPager(slider)
        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == numberOfPages.minus(1)) {
                    nextBtn.hide()
                    skipBtn.hide()
                    startBtn.show()
                } else {
                    nextBtn.show()
                    skipBtn.show()
                    startBtn.hide()
                }
            }
        })
        nextBtn.setOnClickListener { }
        skipBtn.setOnClickListener { }
        startBtn.setOnClickListener { }
    }

}