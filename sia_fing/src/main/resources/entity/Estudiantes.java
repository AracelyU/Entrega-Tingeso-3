


@Entity
@
public class Estudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String rut;
    String nombre;
    String apellido;
    String email;
    int cod_carr;
    
}