package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.StudentsDAO;
import riccardogulin.entities.Student;
import riccardogulin.entities.StudentType;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d12pu");
	// Per connetterci al DB dobbiamo aggiungere al main un attributo statico di tipo EntityManagerFactory che sfrutterà
	// la Persistence Unit definita in persistence.xml per connettersi al DB (dobbiamo quindi passargli come parametro il nome della PU)

	public static void main(String[] args) {
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // Oggetto speciale che gestisce tutte le interazioni con il DB
		StudentsDAO sd = new StudentsDAO(entityManager);

		Student aldo = new Student("Aldo", "Baglio", StudentType.FULLSTACK);
		Student giovanni = new Student("Giovanni", "Storti", StudentType.FRONTEND);
		Student giacomo = new Student("Giacomo", "Poretti", StudentType.BACKEND);

		// ************************************* SAVE *****************************

//		sd.save(aldo);
//		sd.save(giovanni);
//		sd.save(giacomo);

		// ************************************** FIND BY ID **********************
		try {
			Student studentFromDB = sd.findById(5);
			System.out.println(studentFromDB);

		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		// ************************************** FIND BY ID AND DELETE **********************
		try {
			sd.findByIdAndDelete(2);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


		// Best Practice. Quando finisco di utilizzare delle risorse come Scanner, EntityManager, EntityManagerFactory, ecc è sempre consigliato chiuderle
		// (nel nostro caso è irrilevante perché l'applicazione si avvia e poi si chiude rilasciando tutte le risorse automaticamente)
		entityManager.close();
		entityManagerFactory.close();
	}
}
