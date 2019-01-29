package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Customer;
import model.Customers;


public class FileService {// make this generic

	public FileService() {

	}
	
	public void populateCustomers(){
		Customers c_list = new Customers();
		Customer customer;
		String customer_path = "customer_records.xml";
		customer = new Customer(1, "April Joy","B", "Padrigano");
		c_list.add(customer);
		customer = new Customer(2, "Sabs","C", "Sabs");
		c_list.add(customer);
		customer = new Customer(6, "Noel","E", "Garcia");
		c_list.add(customer);
		customer = new Customer(3, "Kriz","D", "Urmeneta");
		c_list.add(customer);
		customer = new Customer(4, "Ferlie","Z", "Penido");
		c_list.add(customer);
		saveObject(c_list, customer_path);
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Creates a
	 * file given a String path. Date Modified:
	 * 
	 * @param path
	 */
	public void createFile(String path) {
		System.out.println("creating.......");
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
	public <T> int checkCurrentNumOfRecord(String path, T t) {
		FileInputStream fis = null;

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
				T obj = (T) input.readObject();
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
	public <T> void saveObject(T t, String filepath) {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(t, new File(filepath));
			marshaller.marshal(t, System.out);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// public <T extends Serializable> void saveObjectSucceedingTime(T t,
	// String filepath) {
	// try {
	// ObjectOutputStream os2 = new ObjectOutputStream(
	// new FileOutputStream(filepath, true)) {
	// protected void writeStreamHeader() throws IOException {
	// reset();
	// }
	// };
	// os2.writeObject(t);
	// os2.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	public <T> T readMultipleObjects(String path, T t_list) {

		try {
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(t_list.getClass());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			t_list = (T) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t_list;
	}

}
