package others;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;

import model.Movie;

public class FilesManager {//make this generic
	
	public FilesManager(){

	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Creates a
	 * file given a String path. Date Modified:
	 * 
	 * @param path
	 */
	public static void createFile(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Checks
	 * current number of records
	 * 
	 * @param path
	 * @param t
	 * @return
	 */
	public <T extends Serializable> int checkCurrentNumOfRecord(String path, T t) {
		FileInputStream fis = null;
		T obj;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean cont = true;
		int count = 0;
		try {
			ObjectInputStream input = new ObjectInputStream(fis);
			while (cont) {
				obj = (T) input.readObject();
				if (obj != null)
					count++;
				else
					cont = false;
			}
		} catch (Exception e) {

		}
		return count;
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Creates a
	 * record in file given the object and the file path Date Modified:
	 * 1-23-2018 7:30 pm
	 * 
	 * @param t
	 * @param filepath
	 */
	public <T extends Serializable> void saveObjectFirstTime(T t, String filepath) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(t);
			objectOut.close();
			System.out.println("The Object  was succesfully written to a file");

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	public <T extends Serializable> void saveObjectSucceedingTime(T t, String filepath) {
		try {
			ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream(filepath, true)) {
				protected void writeStreamHeader() throws IOException {
					reset();
				}
			};
			os2.writeObject(t);
			os2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public <T extends Serializable> ArrayList<T> readMultipleObjects(String path, ArrayList<T> t_list){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean cont = true;
		try{
		   ObjectInputStream input = new ObjectInputStream(fis);
		   while(cont){
			  Object obj = input.readObject();
		      if(obj == null){		    	  
		    	  cont = false;
		      }else{		    	  
		    	  t_list.add((T) obj);
		      }
		         
		   }
		}catch(Exception e){
		}
		return t_list;
	}

	public void removeRecordInFile(Object object, int type) {

	}
}
