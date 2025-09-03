abstract class Inventor{
    private String nombre;
    private String edad;
    protected int creatividad;

    protected Inventor (String nombre, String edad, int creatividad){
        this.nombre = nombre;
        this.edad = edad;
        this.creatividad = creatividad;
    }

    protected void SubirCreatividad(int puntos) {
        if (puntos > 0) {
            this.creatividad += puntos;
            System.out.println(nombre + "Ha ganado" + puntos + "de creatividad. Total: " + creatividad);
        }
    }

    protected int getcreatividad(){
        return creatividad;
    }

    public String getnombre(){
        return this.nombre;
    }

    public String getedad(){
        return this.edad;
    }
        
    public abstract void mostrarDatos();
}

class Mecanico extends Inventor {
    private String especialidad;
    private String presentacion;

    public Mecanico(String nombre, String edad, int creatividad, String especialidad, String presentacion) {
        super(nombre, edad, creatividad);
        this.especialidad = especialidad;
        this.presentacion = presentacion;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Inventor: " + getnombre());
        System.out.println("Edad: " + getedad());
        System.out.println("Creatividad: " + creatividad);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Presentacion: " + presentacion);
    }
}

class Programador extends Inventor {
    private String especialidad;
    private String presentacion;

    public Programador(String nombre, String edad, int creatividad, String especialidad, String presentacion) {
        super(nombre, edad, creatividad);
        this.especialidad = especialidad;
        this.presentacion = presentacion;
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("Inventor: " + getnombre());
        System.out.println("Edad: " + getedad());
        System.out.println("Creatividad: " + creatividad);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Presentacion: " + presentacion);
    }
}

class Quimico extends Inventor {
    private String especialidad;
    private String presentacion;

    public Quimico(String nombre, String edad, int creatividad, String especialidad, String presentacion) {
        super(nombre, edad, creatividad);
        this.especialidad = especialidad;
        this.presentacion = presentacion;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Inventor: " + getnombre());
        System.out.println("Edad: " + getedad());
        System.out.println("Creatividad: " + creatividad);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Presentacion: " + presentacion);
    }
}

public class Competencia{
    
    public static void main(String[] args) {
        System.out.println("Bienvenido a la competencia \n");

        Mecanico santiago = new Mecanico("Santiago", "18", 45, "Mecanico", "Ingresa con una maquina nunca antes vista");
        Programador estefany = new Programador("Estefany", "18", 55, "Programador", "Ingresa con un programa increible");
        Quimico jersson = new Quimico("Jersson", "18", 50, "Quimico", "Ingresa con quimica de alto nivel");

        Inventor[] inventores = {santiago, estefany, jersson};

        System.out.println("INVENTORES \n");
        for (Inventor d : inventores) {
            d.mostrarDatos();
            System.out.println();
        }
    }
}