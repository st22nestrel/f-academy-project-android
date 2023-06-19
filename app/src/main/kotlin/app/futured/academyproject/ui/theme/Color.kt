@file:Suppress("MagicNumber")

package app.futured.academyproject.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val cloud50 = Color(0xffF0F2F5)
val cloud100 = Color(0xffDBE0E5)
val cloud200 = Color(0xffC3CBD4)
val cloud300 = Color(0xffAAB6C3)

val ink900 = Color(0xff0D0F11)
val ink800 = Color(0xff17191C)
val ink700 = Color(0xff1C1F22)
val ink600 = Color(0xff222428)

object CustomColor {
    val divider: Color
        @Composable
        get() = if (isSystemInDarkTheme()) ink800 else cloud100
}

val md_theme_light_primary = Color(0xFF7B5800)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFFFDEA6)
val md_theme_light_onPrimaryContainer = Color(0xFF271900)
val md_theme_light_secondary = Color(0xFF6D5C3F)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFF7DFBB)
val md_theme_light_onSecondaryContainer = Color(0xFF251A04)
val md_theme_light_tertiary = Color(0xFF4C6544)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFCEEBC2)
val md_theme_light_onTertiaryContainer = Color(0xFF0A2007)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF1E1B16)
val md_theme_light_outline = Color(0xFF807567)
val md_theme_light_inverseOnSurface = Color(0xFFF8EFE7)
val md_theme_light_inverseSurface = Color(0xFF34302A)
val md_theme_light_inversePrimary = Color(0xFFF7BD48)
val md_theme_light_surfaceTint = Color(0xFF7B5800)
val md_theme_light_outlineVariant = Color(0xFFD2C4B4)
val md_theme_light_scrim = Color(0xFF000000)
val md_theme_light_surface = Color(0xFFF5ECE3)
val md_theme_light_onSurface = Color(0xFF1E1B16)
val md_theme_light_surfaceVariant = Color(0xFFE6D9C1)
val md_theme_light_onSurfaceVariant = Color(0xFF4E4539)

val md_theme_dark_primary = Color(0xFFF7BD48)
val md_theme_dark_onPrimary = Color(0xFF412D00)
val md_theme_dark_primaryContainer = Color(0xFF5D4200)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFDEA6)
val md_theme_dark_secondary = Color(0xFFD9C4A0)
val md_theme_dark_onSecondary = Color(0xFF3C2E15)
val md_theme_dark_secondaryContainer = Color(0xFF54452A)
val md_theme_dark_onSecondaryContainer = Color(0xFFF7DFBB)
val md_theme_dark_tertiary = Color(0xFFB2CEA7)
val md_theme_dark_onTertiary = Color(0xFF1F361A)
val md_theme_dark_tertiaryContainer = Color(0xFF354D2E)
val md_theme_dark_onTertiaryContainer = Color(0xFFCEEBC2)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF1E1B16)
val md_theme_dark_onBackground = Color(0xFFE9E1D9)
val md_theme_dark_outline = Color(0xFF9B8F80)
val md_theme_dark_inverseOnSurface = Color(0xFF1E1B16)
val md_theme_dark_inverseSurface = Color(0xFFE9E1D9)
val md_theme_dark_inversePrimary = Color(0xFF7B5800)
val md_theme_dark_surfaceTint = Color(0xFFF7BD48)
val md_theme_dark_outlineVariant = Color(0xFF4E4539)
val md_theme_dark_scrim = Color(0xFF000000)
val md_theme_dark_surface = Color(0xFF161411)
val md_theme_dark_onSurface = Color(0xFFCDC5BD)
val md_theme_dark_surfaceVariant = Color(0xFF4E4539)
val md_theme_dark_onSurfaceVariant = Color(0xFFD2C4B4)

val seed = Color(0xFFCFA85F)
