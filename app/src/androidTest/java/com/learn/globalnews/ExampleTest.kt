package com.learn.globalnews

import com.learn.globalnews.ui.view.MainActivity
import io.kotest.core.spec.style.FunSpec
import androidx.test.core.app.launchActivity

class ExampleTest : FunSpec({

    test("Hello, Tests!") {
        val scenario = launchActivity<MainActivity>()
        scenario.onActivity {
//            it.tv.text shouldBe "Dummy!"
        }
    }

})
