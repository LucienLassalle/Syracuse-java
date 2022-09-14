import java.util.logging.Level;
import java.util.logging.Logger;

public class SyracuseInfinity {
    private static final Logger LOGGER = Logger.getLogger(SyracuseJava.class.getPackageName());
    public static void syracuse_Unlimited() {
        long base=1;
        long nombre, duree, vmax=0, baseMax=0;

        long debut=System.currentTimeMillis();
        long fin,temps;


        while (base < Long.parseLong("9223372036854775807")) {
            nombre = base;
            duree = 0;
            while (nombre != 1) { // Tant que le nombre est différent de 1 alors :
                nombre = SyracuseFct.formule_syracuse(nombre); // Utilisation de la formule de syracuse
                duree++; // Ajouter 1 a la durée de vol.
            }
            base++; // Incrémentation de la nouvelle base
            if (vmax <= duree) { // Si la VMAX d'avant est supérieur à la durée de vol actuel
                vmax = duree; // Alors la durée de vol actuelle devient la VMAX
                baseMax = base - 1; // Donc, il faut la base max
                LOGGER.log(Level.INFO, "La nouvelle VMAX est désormais de : " + vmax + " pour une valeur de départ de : " + baseMax + "\n");
            }
            if (base % 5000000 == 0) {
                fin = System.currentTimeMillis();
                temps = fin - debut;
                LOGGER.log(Level.INFO, "Passage d'un cap : " + base + " pour un temps d'exécution de : " + temps + "ms");
            }
        }
        System.out.print("La durée de vol la plus élevé qui a été atteint : " + vmax + " pour une valeur de départ assigné a " + baseMax);
    }
}
