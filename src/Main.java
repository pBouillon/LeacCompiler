import ast.exception.root.BadChildrenCountException;
import ast.exception.root.BadNodeNameException;
import ast.node.RootNode;
import compiler.Compiler;
import org.antlr.runtime.tree.Tree;
import utils.GrammarConstants;

import java.io.File;

 /**
  * compiler.Compiler project's entry point
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

         // Compile the source file and fetch the generated AST
         Tree ast = compiler.compileTarget(source);

         // Extract custom ast
         RootNode customAstRoot = null;
         try {
             customAstRoot = compiler.generateAst(ast);
         } catch (BadChildrenCountException | BadNodeNameException e) {
             e.printStackTrace();
         }

         // Display result
         System.out.println(customAstRoot);
     }

 }
