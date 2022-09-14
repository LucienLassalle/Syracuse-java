import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyracuseJava {
    private static final Logger LOGGER = Logger.getLogger(SyracuseJava.class.getPackageName());

    /**
     * Application de la formule de syracuse
     * - Si un entier est divisible par 2 sans reste, alors on le divise par 2
     * - Sinon on le multiplie par 3 et on ajoute 1
     * @param nombre divisé par 2 ou multiplié par 3 avec un ajout de 1 conformément a la formule de syracuse
     * @return nombre
     */
    public static long formule_syracuse(long nombre){
        if (nombre % 2 == 0) {
            return nombre / 2;
        } else {
            return nombre * 3 + 1;
        }
    }

    /**
     * Syracuse_simple permet de calculer la durée de vol pour un nombre que l'utilisateur rentre.
     */
    public static void Syracuse_simple() {

        long nombre=0;
        long duree = 0;

        Scanner lecteur = new Scanner(System.in);

        while(nombre<=0) {

            System.out.print("Entrez un réel Entier : ");
            try {
                nombre = lecteur.nextLong();
                lecteur.nextLine();
            } catch (InputMismatchException e) {
                lecteur.nextLine();
                nombre=0;
                LOGGER.log(Level.SEVERE,"UN ENTIER NE PEUT PAS ÊTRE UNE CHAÎNE DE CHARACTER");
            }
        }

        if (nombre < 4) {
            LOGGER.log(Level.FINEST,"Peu intéressant");
        }

        else {
            while (nombre != 1) { // Tant que le nombre est différent de 1 alors on fait une itération
                nombre=formule_syracuse(nombre);
                System.out.print("Le vol est de : " + nombre + "\n");
                duree = duree + 1; // Ajouter 1 a la durée de vol.
            }
        }
        System.out.println("La durée de vol est de " + nombre);
    }


    /**
     * La méthode syracuse inversé permet de lancer toute la procédure pour faire un ensemble de calcul en utilisant syracuse et son théorème
     */
        public static void Syracuse_Inverted() {
            Scanner lecteur = new Scanner(System.in);

            long LIMIT=0;
            long base = 1;
            long nombre;
            long duree;
            long vmax = 0;
            long base_max = base;

            while(LIMIT<1) {
                try {
                    System.out.print("Entrez la limite pour la conjecture de syracuse : ");
                    LIMIT = lecteur.nextLong();
                    if(LIMIT<1){
                        LOGGER.log(Level.WARNING,"Valeur inférieur a 1");
                    }
                } catch (InputMismatchException e){
                    LIMIT = -1;
                    LOGGER.log(Level.SEVERE,"Les lettres ne sont pas acceptés...");
                }
                lecteur.nextLine();
            }
            long debut=System.currentTimeMillis();
            long fin,temps;
            while (base < LIMIT) {
                // System.gc(); // GARBAGE COLLECTOR, VIDER LA RAM
                nombre = base;
                duree = 0;
                while (nombre != 1) { // Tant que le nombre est différent de 1 alors :
                    nombre=formule_syracuse(nombre); // Utilisation de la formule de syracuse
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

    /**
     * Syracuse Unlimited a pour unique objectif de calculer des nombres de la base 1 a la limite d'un long.
     */
    public static void Syracuse_Unlimited() throws IOException {
            long base=1;
            long nombre;

            long duree;
            long vmax=0;

            long baseMax=0;
            long debut=System.currentTimeMillis();
            long fin,alternative,temps;

            File fr = new File("ListTestWbaseNoLim.txt");
            if(!fr.exists()){
                System.out.println("Création d'un fichier : "+ fr.createNewFile());
            }
            FileWriter fw = new FileWriter("ListTestWbaseNoLim.txt");
            Scanner frs = new Scanner(fr);

            String test="";
            while(frs.hasNextLine()){
                test = frs.nextLine();
            }
            System.out.println("La dernière fois, nous avions atteint : "+test);

            try {
                while (base < Long.parseLong("9223372036854775807")) {
                    nombre = base;
                    duree = 0;
                    while (nombre != 1) { // Tant que le nombre est différent de 1 alors :
                        nombre = formule_syracuse(nombre); // Utilisation de la formule de syracuse
                        duree++; // Ajouter 1 a la durée de vol.
                    }
                    base++; // Incrémentation de la nouvelle base
                    if (vmax <= duree) { // Si la VMAX d'avant est supérieur à la durée de vol actuel
                        vmax = duree; // Alors la durée de vol actuelle devient la VMAX
                        baseMax = base - 1; // Donc, il faut la base max
                        LOGGER.log(Level.INFO, "La nouvelle VMAX est désormais de : " + vmax + " pour une valeur de départ de : " + baseMax + "\n");
                        alternative = System.currentTimeMillis();
                        alternative = alternative - debut;
                        fw.write(baseMax + " - "+ vmax + " - " + alternative +"\n");
                    }
                    if (base % 5000000 == 0) {
                        fin = System.currentTimeMillis();
                        temps = fin - debut;
                        LOGGER.log(Level.INFO, "Passage d'un cap : " + base + " pour un temps d'exécution de : " + temps + "ms");
                        fw.write(base + " - " + vmax + " - " + temps + "\n");
                    }
                }
                System.out.print("La durée de vol la plus élevé qui a été atteint : " + vmax + " pour une valeur de départ assigné a " + baseMax);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "ERREUR SEVERE - FIN D'EXECUTION DU PROGRAMME");
                e.printStackTrace();
            } finally {
                System.out.println("Fin du programme");
                fw.close();
            }
        }

    /**
     * Calcul de syracuse entre une borne inférieur et une borne supérieur.
     */
    public static void Syracuse_Value() {
        Scanner lecteur = new Scanner(System.in);
        long borne_basse = -1;
        long borne_haute = -1;
        while (borne_basse <= 0 || borne_haute <= 0 || borne_basse > borne_haute) {
            try {
                System.out.print("Entrez la valeur basse :");
                borne_basse = lecteur.nextLong();
                System.out.print("Entrez la valeur haute :");
                borne_haute = lecteur.nextLong();
                lecteur.nextLine();
            } catch (InputMismatchException e) {
                lecteur.nextLine();
                LOGGER.log(Level.WARNING, "Erreur d'insertion, correction...");
                borne_basse = -1;
                borne_haute = -1;
            }
            System.out.flush();
        }
        long base = borne_basse;
        long limit = borne_haute;
        long nombre;
        long durée;
        long bmax = 0;
        long vmax = 0;

        long debut = System.currentTimeMillis();

        while (base < limit) {
            durée = 0;
            nombre = base;
            while (nombre != 1) {
                nombre = formule_syracuse(nombre);
                durée++;
            }
            System.out.println("Le nombre : " + base + " a comme durée de vol : " + durée);
            base++;
            if (vmax <= durée) { // Si la VMAX d'avant est supérieur à la durée de vol actuel
                vmax = durée; // Alors la durée de vol actuelle devient la VMAX
                bmax = base - 1; // Donc, il faut la base max
            }
        }
        System.out.println("De : " + borne_basse + " à : " + borne_haute + " la durée de vol la plus élevé est : " + vmax + " pour une valeur de base de " + bmax);

        long fin = System.currentTimeMillis();
        long temps = fin - debut;
        System.out.println("Temps d'execution des instruction : " + temps + "ms");
        //File ListTestWbaseLim = new File("ListTestWbaseLim.txt");
        try (FileWriter fw = new FileWriter("ListTestWbaseLim.txt")) {
            fw.write("De : " + borne_basse + " à : " + borne_haute + " la durée de vol la plus élevé est : " + vmax + " pour une valeur de base de " + bmax + "\n");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Erreur d'écriture dans le fichier");
        }
    }

    /**
     * Procédure main, laissant le choix à l'utilisateur de ce qu'il veut démarrer.
     * @param args Procédure main.
     */
    public static void main (String[]args) throws IOException {
            int indication=-1;
            Scanner lecteur = new Scanner(System.in);
            while(indication<=0 || indication >= 5) {
                System.out.println("""
                        Quel conjecture de syracuse voulez vous exploitez ?
                        - Indiquez 1 pour tester le vol d'un nombre
                        - Indiquez 2 pour tester l'ensemble des nombres jusqu'à une limite
                        - Indiquez 3 pour tester l'ensemble des nombres sans limite
                        - Indiquez 4 pour tester un ensemble à partir d'un nombre jusqu'à une limite.
                        """);
                System.out.print("Saisir ici : ");
                try {
                    indication = lecteur.nextInt();
                } catch (InputMismatchException e) {
                    indication = -1;
                    LOGGER.log(Level.SEVERE, "UN ENTIER NE PEUT PAS ÊTRE UNE CHAINE DE CHARACTER");
                    System.out.flush();
                }
                lecteur.nextLine();
            }
            if (indication == 1) {
                Syracuse_simple();
                File myObj = new File("ListTestUNITAITRE.txt");
            }
            if (indication == 2) {
                Syracuse_Inverted();
                File myObj = new File("ListTestwWLimit.txt");
            }
            if (indication == 3){
                Syracuse_Unlimited();
            }
            if (indication == 4){
                Syracuse_Value();
            }



            // TODO : Réparer le problème de fichier qui n'écrit pas quand on lui demande a se connard de merde.
            // TODO : Puis sauvegarder l'état correctement afin de reprendre a l'emplacement souhaité dans le fichier.
            // TODO : Finir la perfection d'allez chercher la plus grande valeur possible pour un long
        }
    }