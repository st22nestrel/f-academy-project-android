package app.futured.academyproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.tools.preview.TanksProvider
import app.futured.academyproject.ui.theme.Grid
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.collections.immutable.PersistentList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TankCardVertical(/*tank: Tank,*/ tankStatistics: TankComparable, modifier: Modifier = Modifier,
             onIconClicked: (tankId: Int) -> Unit = ::onIconClickedPreview ) {
    //TODO convert to lazyColumn maybe?
    Column(
        modifier = modifier
            .padding(vertical = Grid.d2, horizontal = Grid.d4)
            .fillMaxWidth(),
    ) {
//        Card(
//            colors = CardDefaults.cardColors(),
//            modifier = Modifier
//                .size(Grid.d15),
//        ) {
//            Image(
//                painter = rememberAsyncImagePainter(
//                    ImageRequest.Builder(LocalContext.current)
//                        .data(tank.bigIcon.replace("http", "https"))
//                        .build(),
//                ),
//                contentDescription = tank.bigIcon,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .clickable { onIconClicked(tank.id) },
//
//            )
//        }

        Text(
            text = "Basic tank info",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
//        Spacer(modifier = Modifier.height(Grid.d1))
//        Text(
//            text = tank.name,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurface,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//        )
//        Text(
//            text = tank.tankType,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//        )
//        Text(
//            text = tank.tier.toString(),
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurface,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//        )
//        Text(
//            text = tank.nation,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//        )

        Spacer(modifier = Modifier.height(Grid.d1))
        Text(
            text = "Tank statistics",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "Aim Time: ${tankStatistics.gunAimTime}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "Reload Time: ${tankStatistics.gunReloadTime}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

fun onIconClickedPreview(input: Int) : Unit {
    val pls = input
}

@Preview
@Composable
private fun PlaceCardPreview(@PreviewParameter(TanksProvider::class) tanks: PersistentList<Tank>) = Showcase {
    TankCard(tank = tanks.first(), onClick = {})
}

@Preview
@Composable
private fun PlaceCardPreview2(@PreviewParameter(TanksProvider::class) tanks: PersistentList<Tank>) = Showcase {
    TankCard(tank = tanks.first().copy(isFavourite = true), onClick = {})
}
