public class SyracuseJava {
    /**
     * Procédure principale, il demande à l'utilisateur quelle partie du programme il veut executer.
     * @param args -
     */
    public static void main (String[]args) {
        long indication=-1;
        while(indication<=0 || indication >= 6) {
            System.out.println("""
                        Quel conjecture de syracuse voulez vous exploitez ?
                        - Indiquez 1 pour tester le vol d'un nombre
                        - Indiquez 2 pour tester l'ensemble des nombres jusqu'à une limite
                        - Indiquez 3 pour tester l'ensemble des nombres sans limite
                        - Indiquez 4 pour tester un ensemble à partir d'un nombre jusqu'à une limite.
                        
                        - Indiquez 5 pour avoir les informations sur le programme
                        """);
            indication = SyracuseFct.demande_nombre();
        }
        if (indication == 1) {
            SyracuseSimple.syracuse_simple();
        }
        if (indication == 2) {
            SyracuseInverted.syracuse_Inverted();
        }
        if (indication == 3){
            SyracuseInfinity.syracuse_Unlimited();
        }
        if (indication == 4){
            SyracuseBorne.syracuse_Value();
        }

        // Afficher les informations
        if (indication == 5){
                SyracuseFct.informations();
        }
    }
}