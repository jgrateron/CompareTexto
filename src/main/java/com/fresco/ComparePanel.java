package com.fresco;

import java.awt.BorderLayout;
import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class ComparePanel extends JComponent {

	private static final long serialVersionUID = 1L;

	public void start() {
		setLayout(new BorderLayout());

		try {
			var generator = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(true)
					.oldTag(f -> f ? "<font color='red'>" : "</font>") // introduce html style for
																		// strikethrough
					.newTag(f -> f ? "<font color='green'>" : "</font>") // introduce html style for bold
					.build();

			var classLoader = ClassLoader.getSystemClassLoader();
			var url1 = classLoader.getResource("ansi1.txt");
			var path1 = Paths.get(url1.toURI());
			var allLines1 = Files.readAllLines(path1);
			var url2 = classLoader.getResource("ansi2.txt");
			var path2 = Paths.get(url2.toURI());
			var allLines2 = Files.readAllLines(path2);

			var rows = generator.generateDiffRows(allLines1, allLines2);

			var text = "";
			for (DiffRow row : rows) {
				text = text + row.getOldLine() + "<br>";
			}
			text = "<tt>" + text + "</tt>";
			text = text.replaceAll(" ", "&nbsp;");
			JEditorPane editor = new JEditorPane();
			editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			editor.setContentType("text/html");
			editor.setText(text);
			editor.setEditable(false);
			text = "";
			for (DiffRow row : rows) {
				text = text + row.getNewLine() + "<br>";
			}
			text = "<tt>" + text + "</tt>";
			text = text.replaceAll(" ", "&nbsp;");
			JEditorPane editor2 = new JEditorPane();
			editor2.setContentType("text/html");
			editor2.setText(text);
			editor2.setEditable(false);
			
			JPanel pContainer = new JPanel();
			pContainer.add(editor);
			pContainer.add(editor2);
			
			JScrollPane scrollPane2 = new JScrollPane(pContainer);
			add(scrollPane2);

		} catch (Exception e) {
		}

	}

}
