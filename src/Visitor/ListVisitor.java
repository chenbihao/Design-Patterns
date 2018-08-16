package Visitor;

import java.util.Iterator;

/**
 * @author CC
 * @Description 表示访问数据结构并显示元素一览
 */
public class ListVisitor extends Visitor{
	private String currentdir="";
	@Override
	public void visit(File file) {//在访问文件时调用
		System.out.println(currentdir+"/"+file);
	}

	@Override
	public void visit(Directory directory) {//在访问目录时调用
		System.out.println(currentdir+"/"+directory);
		String savedir=currentdir;
		currentdir=currentdir+"/"+directory.getName();
		Iterator it=directory.iterator();
		while(it.hasNext()){
			Entry entry=(Entry) it.next();
			entry.accept(this);
		}
		currentdir=savedir;
	}

}
