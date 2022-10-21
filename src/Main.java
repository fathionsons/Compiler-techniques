import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {
public static void main(String[] args) throws IOException {
    if (args.length!=1) {
        System.err.println("\n");
        System.err.println("Impl Interpreter\n");
        System.err.println("=================\n\n");
        System.err.println("Please give as input argument a filename\n");
        System.exit(-1);
    }
    String filename=args[0];
    CharStream input = CharStreams.fromFileName(filename);
    // test file name input
    implLexer lex = new implLexer(input);

    CommonTokenStream tokens = new CommonTokenStream(lex);

    implParser parser = new implParser(tokens);
    ParseTree parseTree = parser.start();

    Interpreter interpreter = new Interpreter();
    Expr result=interpreter.visit(parseTree);
    System.out.println("The result is: "+result.eval());

























    }
}
class Interpreter extends implBaseVisitor<T> implements implVisitor<T> {

    public Expr visitStart(implParser.StartContext ctx){
        return visit(ctx.e1);
    };
    public Expr visitMultiplication(implParser.MultiplicationContext ctx){
        if (ctx.op.getText().equals("*"))
            return new Multiplication(visit(ctx.e1),visit(ctx.e2));
        else
            return new Division(visit(ctx.e1),visit(ctx.e2));
    };
    public Expr visitAddition(implParser.AdditionContext ctx){
        if (ctx.op.getText().equals("+"))
            return new Addition(visit(ctx.e1),visit(ctx.e2));
        else
            return new Subtraction(visit(ctx.e1),visit(ctx.e2));
    };
    public Expr visitVariable(implParser.VariableContext ctx){
        return new Variable(ctx.x.getText());
    };
    public Expr visitConstant(implParser.ConstantContext ctx){
        return new Constant(Integer.parseInt(ctx.c.getText()));
    };
    public Expr visitParentheses(implParser.ParenthesesContext ctx){
        return visit(ctx.e1);
    };
}

