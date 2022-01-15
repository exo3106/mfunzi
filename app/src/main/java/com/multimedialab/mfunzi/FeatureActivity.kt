package com.multimedialab.mfunzi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.multimedialab.mfunzi.ui.theme.MfunziTheme

class FeatureActivity : ComponentActivity() {

   private val feature: Feature by lazy{
      intent?.getSerializableExtra(FEATURE_ID)  as Feature
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MfunziTheme {

                    Text("Hello ${feature.title}")
            }
        }
    }
    companion object{
        private const val FEATURE_ID = "feature_id"
        fun newIntent(context: Context, feature:Feature)=
            Intent(context, FeatureActivity::class.java).apply{
                putExtra(FEATURE_ID,feature)
            }
    }
}
