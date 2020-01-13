import utils.GrammarConstants;

 import java.io.File;

 /**
  * Compiler project's entry point
  *
  * @author Florian Vogt
  * @author Pierre Bouillon
  * @author Victor Varnier
  * @version 0.1
  * @url https://github.com/pBouillon/TELECOM_Trad
  */
 public class Main {

     /**
      * Default entry point
      *
      * @param args program arguments
      */
     public static void main(String[] args) {
         // Create the compiler
         Compiler compiler = new Compiler();

         // Locate source file
         File source = new File(GrammarConstants.LANG_SOURCE);

         // Compile the source file
         compiler.compileTarget(source);
     }

 }
