import java.util.logging.Level;
import java.util.logging.Logger;

public class SyracuseInverted {
    private static final Logger LOGGER = Logger.getLogger(SyracuseJava.class.getPackageName());
    public static void syracuse_Inverted() {
        // TODO : Demander a l'utilisateur s'il souhaite récupérer les données de sa dernière tentative (si elle existe)
        // TODO : S'il accepte, alors procéder a la continuité
        // TODO : S'il refuse, alors redemander une limite.
        System.out.println("Entrez une limite \n");

        long LIMIT= SyracuseFct.demande_nombre();
        long base = 1, base_max=base;
        long nombre, duree, vmax = 0;
        long debut=System.currentTimeMillis();
        long fin,temps;

        while (base < LIMIT) {
            // System.gc(); // GARBAGE COLLECTOR, VIDER LA RAM
            nombre = base;
            duree = 0;
            while (nombre != 1) { // Tant que le nombre est différent de 1 alors :
                nombre=SyracuseFct.formule_syracuse(nombre); // Utilisation de la formule de syracuse
                duree++; // Ajouter 1 a la durée de vol.
            }
            base++; // Incrémentation de la nouvelle base
            if (vmax <= duree) { // Si la VMAX d'avant est supérieur à la durée de vol actuel
                vmax = duree; // Alors la durée de vol actuelle devient la VMAX
                base_max = base - 1; // Donc, il faut la base max
                LOGGER.log(Level.INFO,"La nouvelle VMAX est désormais de : "+vmax+" pour une valeur de départ de : "+base_max+"\n");
            }
        }
        fin=System.currentTimeMillis();
        temps=fin-debut;
        System.out.print("La durée de vol la plus élevé qui a été atteint : " + vmax + " pour une valeur de départ assigné a " + base_max+"\n [Temps d'exécution : "+temps+"ms]");
    }
}
