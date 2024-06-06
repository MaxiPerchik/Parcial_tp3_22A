package ort.clases.parcial_22a_tp3

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ort.clases.parcial_22a_tp3.core.Config

@HiltAndroidApp
class Parical22ATP3:Application (){

    override fun onCreate() {
        super.onCreate()
        Config.baseUrl = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
        Config.apiKey = "123"
    }
}