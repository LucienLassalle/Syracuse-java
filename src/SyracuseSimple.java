public class SyracuseSimple {
    /**
     *  Syracuse simple calcul la durée de vol pour un seul et unique nombre.
     */
    public static void syracuse_simple() {
        long nombre=SyracuseFct.demande_nombre();
        long duree = 0;
        long temps=System.currentTimeMillis();

        while (nombre != 1) { // Tant que le nombre est différent de 1 alors on fait une itération
            nombre=SyracuseFct.formule_syracuse(nombre);
            System.out.print("Le vol est de : " + nombre + "\n");
            duree = duree + 1; // Ajouter 1 a la durée de vol.
        }
        System.out.println("La durée de vol est de " + nombre);
        temps = System.currentTimeMillis()-temps;
        System.out.println(" [Temps d'exécution : "+temps+"ms]");
    }
}
