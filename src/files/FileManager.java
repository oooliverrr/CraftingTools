package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;

import util.storage.Pair;

public class FileManager {

	public static Pair<ArrayList<String>, ArrayList<String>> getAllFilesAndFoldersAt(String path) {

		ArrayList<String> files = new ArrayList<String>();
		ArrayList<String> folders = new ArrayList<String>();

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i].getPath());
			} else if (listOfFiles[i].isDirectory()) {
				folders.add(listOfFiles[i].getPath());
			}
		}

		return new Pair<ArrayList<String>, ArrayList<String>>(files, folders);
	}

	public static void copyFileFolderToFileFolder(String source, String destination) throws IOException {

		File sourceFileFolder = new File(source);
		File destinationFileFolder = new File(destination);

		if (sourceFileFolder.isDirectory()) {

			if (!destinationFileFolder.exists()) {
				destinationFileFolder.mkdir();
			}

			// list all the directory contents
			String files[] = sourceFileFolder.list();

			for (String file : files) {

				// Is this file a "system file"?
				try {
					File srcFile = new File(sourceFileFolder, file);
					final boolean isSystem = Files
							.readAttributes(Paths.get(srcFile.getAbsolutePath()), DosFileAttributes.class).isSystem();

					if (!isSystem) {
						// construct the src and dest file structure
						File destFile = new File(destinationFileFolder, file);
						// recursive copy
						copyFileFolderToFileFolder(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
					}
				} catch (Exception e) {

				}
			}

		} else {
			if (!destinationFileFolder.exists()) {
				// if file, then copy it
				// Use bytes stream to support all file types
				InputStream in = new FileInputStream(sourceFileFolder);
				OutputStream out = new FileOutputStream(destinationFileFolder);

				byte[] buffer = new byte[1024];

				int length;
				// copy the file content in bytes
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}

				in.close();
				out.close();
			} else {
				// System.out.println(destinationFileFolder.getAbsolutePath()+" already
				// exists");
			}
		}
	}
}
