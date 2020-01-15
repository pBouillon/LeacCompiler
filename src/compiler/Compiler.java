package compiler;

import antlr.assets.GrammarLexer;
import antlr.assets.GrammarParser;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * compiler.Compiler object
 * Core object of the project, used to compile sources
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 * @version 0.1
 * @url https://github.com/pBouillon/TELECOM_Trad
 */
public class Compiler {

    /**
     * Compile the given sources
     *
     * @param source the file to be compiled
     * @return the generated AST if successful
     *         null otherwise
     */
    public Tree compileTarget(File source) {
        Tree generatedAst = null;

        // Compile source from its streaming content
        try (FileInputStream fileInputStream = new FileInputStream(source)) {
            // Perform compilation from the streaming sources
            generatedAst = performCompilation(fileInputStream);
        } catch (IOException | RecognitionException e) {
            e.printStackTrace();
        }

        return generatedAst;
    }

    /**
     * Perform the compilation operation on the given stream
     *
     * @param sourceStream the streamed sources
     * @throws IOException on an unknown or invalid file
     * @throws RecognitionException on a mismatch between the grammar's definition and the source's content
     * @return the generated AST
     */
    private Tree performCompilation(FileInputStream sourceStream) throws IOException, RecognitionException {
        // Perform input from the streamed sources
        ANTLRInputStream inputStream = new ANTLRInputStream(sourceStream);

        // Create the lexer from this stream
        GrammarLexer lexer = new GrammarLexer(inputStream);

        // Create the feed of tokens from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create the parser
        GrammarParser parser = new GrammarParser(tokens);

        // Begin parsing at `prog` rule
        GrammarParser.prog_return result = parser.prog();

        // Return the generated AST
        return (Tree)result.getTree();
    }

}
