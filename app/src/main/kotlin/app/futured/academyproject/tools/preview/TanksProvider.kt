package app.futured.academyproject.tools.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.futured.academyproject.data.model.local.Tank
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

class TanksProvider : PreviewParameterProvider<PersistentList<Tank>> {
    override val values = sequenceOf(
        persistentListOf(
            Tank(
                id = 1,
                isFavourite = true,
                isPremium = false,
                tankType = "mediumTank",
                tier = 5,
                nation = "ussr",
                name = "T-34",
                shortName = "T-34",
                description = "The legend of the Soviet armored forces and the most widely-produced Soviet tank of World War II, with a total of 33,805 vehicles manufactured. Three variants of this model were produced at several Soviet factories from 1940 through 1944.",

                smallIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/small/ussr-R04_T-34.png",
                contourIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/contour/ussr-R04_T-34.png",
                bigIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/ussr-R04_T-34.png",
            ),
            Tank(
                id = 3137,
                isFavourite = false,
                isPremium = false,
                tankType = "heavyTank",
                tier = 8,
                nation = "france",
                name = "AMX 50 100",
                shortName = "AMX 50 100",
                description = "The AMX 50 100 heavy tank was a further development of the M4 project. The first prototype was built in 1949. The 55-ton vehicle featured a 100-mm gun in the oscillating turret. The prototype was tested with gasoline and diesel engines from 1950 through 1952. The vehicle never entered mass production nor saw service.",

                smallIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/small/france-F08_AMX_50_100.png",
                contourIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/contour/france-F08_AMX_50_100.png",
                bigIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/france-F08_AMX_50_100.png",
            ),
            Tank(
                id = 2049,
                isFavourite = true,
                isPremium = false,
                tankType = "lightTank",
                tier = 5,
                nation = "ussr",
                name = "A-20",
                shortName = "A-20",
                description = "Development of the A-20 tank started at Kharkov Factory No. 138 in December 1937. The project was a further development of the BT-7 tank and became a predecessor of the legendary T-34. Work on the vehicle was led by Mikhail Koshkin. On May 18, 1938, technical characteristics of the tank designated BT-20 were approved. Only a few experimental prototypes, with a wheeled caterpillar suspension, were built for training purposes.",

                smallIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/small/ussr-R12_A-20.png",
                contourIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/contour/ussr-R12_A-20.png",
                bigIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/ussr-R12_A-20.png",
            ),
            Tank(
                id = 273,
                isFavourite = false,
                isPremium = false,
                tankType = "SPG",
                tier = 6,
                nation = "ussr",
                name = "Hummel",
                shortName = "Hummel",
                description = "In the autumn of 1942 the Alkett company started the development of a new SPG with a 150-mm gun. To boost new vehicle production, it was planned to use the readily available components of Pz. III and Pz. IV vehicles. The SPG was made on the same chassis as the Nashorn heavy tank destroyer. A total of 714 vehicles and 150 ammunition carriers that used the same chassis were built.",

                smallIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/small/germany-G02_Hummel.png",
                contourIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/contour/germany-G02_Hummel.png",
                bigIcon = "http://api.worldoftanks.eu/static/2.71.0/wot/encyclopedia/vehicle/germany-G02_Hummel.png",
            ),
        ),
    )
}
