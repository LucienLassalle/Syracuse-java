public class SyracuseBorne {
    public static void syracuse_Value() {
        System.out.println("Entrez la borne basse \n");
        long borne_basse = SyracuseFct.demande_nombre();
        System.out.println("Entrez la borne haute \n");
        long borne_haute = SyracuseFct.demande_nombre();
        if(borne_basse>=borne_haute){
            System.out.flush();
            syracuse_Value();
        } else {
            long base = borne_basse;
            long nombre, duree;
            long bmax = 0;
            long vmax = 0;

            long debut = System.currentTimeMillis();

            while (base < borne_haute) {
                duree = 0;
                nombre = base;
                while (nombre != 1) {
                    nombre = SyracuseFct.formule_syracuse(nombre);
                    duree++;
                }
                System.out.println("Le nombre : " + base + " a comme durée de vol : " + duree);
                base++;
                if (vmax <= duree) { // Si la VMAX d'avant est supérieur à la durée de vol actuel
                    vmax = duree; // Alors la durée de vol actuelle devient la VMAX
                    bmax = base - 1; // Donc, il faut la base max
                }
            }
            System.out.println("De : " + borne_basse + " à : " + borne_haute + " la durée de vol la plus élevé est : " + vmax + " pour une valeur de base de " + bmax);

            long fin = System.currentTimeMillis();
            long temps = fin - debut;
            System.out.println("Temps d'execution des instruction : " + temps + "ms");
        }
    }
}
