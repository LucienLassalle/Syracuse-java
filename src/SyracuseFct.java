import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyracuseFct {
    private static final Logger LOGGER = Logger.getLogger(SyracuseJava.class.getPackageName());
    /**
     * Application de la formule de syracuse
     * @param nombre qui subira la formule de syracuse
     * @return le nombre obtenu
     */
    public static long formule_syracuse(long nombre){
        if (nombre % 2 == 0) {
            return nombre / 2;
        } else {
            return nombre * 3 + 1;
        }
    }

    /**
     * Méthode permettant de demander à l'utilisateur un nombre en ayant utilisé un Scanner
     * @return le nombre choisi par l'utilisateur
     */
    public static long demande_nombre() {
        long nombre=0;
        Scanner lecteur = new Scanner(System.in);
        while(nombre<=0) {
            System.out.print("Entrez un entier positif : ");
            try {
                nombre = lecteur.nextLong();
                lecteur.nextLine();
            } catch (InputMismatchException e) {
                lecteur.nextLine();
                nombre=0;
                LOGGER.log(Level.SEVERE,"UN ENTIER NE PEUT PAS ÊTRE UNE CHAÎNE DE CHARACTER");
            }
        }
        return nombre;
    }




    /**
     * Méthode de sauvegarde de l'état actuelle du programme de syracuse
     */
    public static void sauvegarde_syracuse(long nombre, long duree){
        // TODO : Sauvegarde nombre et duree
        // TODO : Suivre le format : BASE - VMAX
        // TODO : Afficher le dernier vol et la base, puis reprendre
    }



    /**
     * Récupération de l'état de syracuse
     * @param nomFichier prends en paramètre le nom du fichier
     * @return la base
     */
    public static long recuperation_syracuse(String nomFichier) {
        if(recuperation_exist(nomFichier)){
            // TODO : Récupération du fichier
        } else {
                if (creationFichierSauvegarde(nomFichier)) {
                    LOGGER.log(Level.INFO, "Création d'un nouveau fichier de sauvegarde");
                } else {
                    LOGGER.log(Level.SEVERE, "ERREUR INCONNU... - CREATION D'UN FICHIER DE SAUVEGARDE EN ERREUR");
                }
        }
        return 1;
    }




    /**
     * Vérification si le fichier existe ou pas.
     * @param nomFichier permettant de savoir si le fichier existe
     * @return boolean si le fichier existe ou pas.
     */
    public static boolean recuperation_exist(String nomFichier){
        File fichier = new File(nomFichier);
        return(fichier.exists());
    }



    /**
     * Création d'un fichier de sauvegarde grâce au nom du fichier fourni par le programme
     * @param nomFichier qui permettra de créer un fichier portant ce nom
     * @return si l'action a bel et bien était accomplis.
     */
    public static boolean creationFichierSauvegarde(String nomFichier) {
        File create = new File(nomFichier);
        try {
            return create.createNewFile();
        } catch (IOException e){
            return false;
        }
    }




    /**
     * Affichage de quelques informations pour l'utilisateur
     */
    public static void informations(){
        System.out.println("- - - - - - - - - - \n");
        System.out.println("Programme crée par : Lucien Lassalle");
        System.out.println("Disponible sur : Github");
        System.out.println("Langue : français");
        System.out.println("Amélioration régulière et optimisation");
        System.out.println("- - - - - - - - - - \n");
    }
}
