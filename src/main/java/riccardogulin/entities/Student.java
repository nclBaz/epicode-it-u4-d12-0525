package riccardogulin.entities;

import jakarta.persistence.*;

@Entity // Annotazione obbligatoria. Ci serve per definire che questa classe dovrà venir mappata ad una specifica tabella nel DB
// Sarà Hibernate a creare in automatico la tabella in questione (oppure se già esistente proverà a modificarla secondo quanto trova in questa classe)
// se utilizziamo l'impostazione <property name="hibernate.hbm2ddl.auto" value="update"/> nel persistence.xml
// N.B. Non dobbiamo inoltre dimenticare di dichiarare questa classe dentro il persistence.xml <class>riccardogulin.entities.Student</class>
@Table(name = "students") // Annotazione OPZIONALE. Serve per personalizzare il nome della tabella
public class Student {

	@Id // Annotazione OBBLIGATORIA. Dichiaro che questo attributo dovrà corrispondere alla colonna PRIMARY KEY della tabella students
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Annotazione OPZIONALE però molto consigliata. Serve per chiedere al DB di generare lui
	// i valori per la PK. IDENTITY significa che invece di biginteger vogliamo usare un bigserial
	private long id; // long corrisponde al tipo biginteger (a meno di non specificare diversamente)

	@Column(name = "first_name", nullable = false, length = 30)
	// @Column(name = "first_name", nullable = false, columnDefinition = "TEXT") Se voglio che sia di tipo text
	private String name;

	@Column(name = "last_name", nullable = false, length = 30)
	private String surname;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING) // <-- Gli ENUM di default vengono "convertiti" in smallint (che non è quello che probabilmente vogliamo)
	// Tramite @Enumerated(EnumType.STRING) specifico che la colonna dovrà essere di tipo varchar
	private StudentType studentType;

	public Student() { // OBBLIGATORIO PER TUTTE LE ENTITIES AVERE UN COSTRUTTORE VUOTO! Viene usato da JPA per costruire degli oggetti quando
		// leggeremo delle righe dalla tabella
	}


	public Student(String name, String surname, StudentType type) {
		this.name = name;
		this.surname = surname;
		this.studentType = type;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public StudentType getStudentType() {
		return studentType;
	}

	public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", studentType=" + studentType +
				'}';
	}
}
