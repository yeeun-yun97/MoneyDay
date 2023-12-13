package app.importu.moneyday

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.importu.moneyday.ui.theme.MoneyDayTheme

val activities = listOf(
    MainActivity::class.java,
    MainActivityAliasTomorrow::class.java,
    MainActivityAliasDayAfterTomorrow::class.java,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyDayTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            for (a in activities) {
                                if (isSettingEnabled(a)) {
                                    disableSetting(a)
                                }
                            }
                            enableSetting(activities.random())
                        }, color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }


    private fun isSettingEnabled(
        activityClass: Class<out Activity>,
    ): Boolean {
        return packageManager.getComponentEnabledSetting(ComponentName(applicationContext, activityClass)) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    }

    private fun enableSetting(
        activityClass: Class<out Activity>,
    ) {
        packageManager.setComponentEnabledSetting(
            ComponentName(applicationContext, activityClass),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    private fun disableSetting(
        activityClass: Class<out Activity>,
    ) {
        packageManager.setComponentEnabledSetting(
            ComponentName(applicationContext, activityClass),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyDayTheme {
        Greeting("Android")
    }
}