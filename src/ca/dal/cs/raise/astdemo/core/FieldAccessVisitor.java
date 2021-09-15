package ca.dal.cs.raise.astdemo.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class FieldAccessVisitor extends VoidVisitorAdapter {

	ArrayList<String> fieldAccess;
	HashMap<String, String> fieldMap;
	ArrayList<String> javadocComments;

	public FieldAccessVisitor() {
		this.fieldAccess = new ArrayList();
		this.fieldMap = new HashMap<>();
		this.javadocComments = new ArrayList<>();
	}

	@Override
	public void visit(FieldAccessExpr fieldExpr, Object arg) {
		// System.out.println(fieldExpr.getField());
		String fieldParent = fieldExpr.getScope().toString();
		if (this.fieldMap.containsKey(fieldParent)) {
			String type = this.fieldMap.get(fieldParent);
			String pair = type + "." + fieldExpr.getField();
			fieldAccess.add(pair);
		} else {
			String pair = fieldParent + "." + fieldExpr.getField();
			fieldAccess.add(pair);
		}
	}

	@Override
	public void visit(FieldDeclaration fdec, Object arg) {
		Type fType = fdec.getType();
		List<VariableDeclarator> varDecs = fdec.getVariables();
		for (VariableDeclarator varDec : varDecs) {
			this.fieldMap.put(varDec.getId().toString(), fType.toString());
		}
	}

	@Override
	public void visit(MethodDeclaration methodDec, Object arg) {
		BlockStmt methodBody = methodDec.getBody();
		List<Statement> stmts = methodBody.getStmts();
		for (Statement stmt : stmts) {
			stmt.accept(this, null);
		}
	}
	

	@Override
	public void visit(JavadocComment jdComment, Object args) {
		//System.out.println(jdComment.getContent());
		this.javadocComments.add(jdComment.getContent());
	}

}
