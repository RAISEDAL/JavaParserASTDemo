package ca.dal.cs.raise.astdemo.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.TokenMgrError;
import com.github.javaparser.ast.CompilationUnit;

public class FieldAccessDemo {

	String fileURL;

	public FieldAccessDemo(String fileURL) {
		this.fileURL = fileURL;
	}

	protected ArrayList<String> parseFieldAccess() {
		ArrayList<String> fieldAccess = new ArrayList<>();
		try {
			CompilationUnit cu = JavaParser.parse(new File(this.fileURL));
			FieldAccessVisitor visitor = new FieldAccessVisitor();
			cu.accept(visitor, null);
			fieldAccess = visitor.fieldAccess;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TokenMgrError err) {
			err.printStackTrace();
		}
		return fieldAccess;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String classAURL = "./src/ca/dal/cs/raise/astdemo/core/A.java";
		FieldAccessDemo demo = new FieldAccessDemo(classAURL);
		ArrayList<String> fAccess = demo.parseFieldAccess();
		System.out.println(fAccess);
	}

}
