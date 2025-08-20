import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Libro {
    private String titulo;
    private String autor;
    private String codigo;
    private boolean disponible;
    
    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true;
    }
    
    public void mostrarDatos() {
        System.out.println("DATOS DEL LIBRO");
        System.out.println("Código: " + codigo);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Estado: " + (disponible ? "Disponible" : "Prestado"));
        System.out.println("========================");
    }
    
    public void marcarPrestado() {
        this.disponible = false;
    }
    
    public void marcarDisponible() {
        this.disponible = true;
    }
    
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCodigo() { return codigo; }
    public boolean isDisponible() { return disponible; }
}

class Usuario {
    private String nombre;
    private String idUsuario;
    private List<Libro> librosPrestados;
    private final int LIMITE_PRESTAMOS = 3;
    
    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }
    
    public void mostrarDatos() {
        System.out.println("DATOS DEL USUARIO");
        System.out.println("ID: " + idUsuario);
        System.out.println("Nombre: " + nombre);
        System.out.println("Libros prestados: " + librosPrestados.size() + "/" + LIMITE_PRESTAMOS);
        
        if (!librosPrestados.isEmpty()) {
            System.out.println("--- Libros actuales ---");
            for (Libro libro : librosPrestados) {
                System.out.println("- " + libro.getTitulo() + " (Código: " + libro.getCodigo() + ")");
            }
        }
        System.out.println("==========================");
    }
    
    public void agregarPrestamo(Libro libro) {
        if (librosPrestados.size() < LIMITE_PRESTAMOS) {
            librosPrestados.add(libro);
            System.out.println("Libro agregado al préstamo del usuario: " + nombre);
        } else {
            System.out.println("Error: El usuario ya tiene el máximo de libros prestados (" + LIMITE_PRESTAMOS + ")");
        }
    }
    
    public void devolverLibro(Libro libro) {
        if (librosPrestados.remove(libro)) {
            System.out.println("Libro removido de los préstamos del usuario: " + nombre);
        } else {
            System.out.println("Error: El libro no estaba en la lista de préstamos de este usuario");
        }
    }
    
    public String getNombre() { return nombre; }
    public String getIdUsuario() { return idUsuario; }
    public List<Libro> getLibrosPrestados() { return librosPrestados; }
    public boolean puedePrestar() { return librosPrestados.size() < LIMITE_PRESTAMOS; }
}

class RegistroPrestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaLimite;
    private boolean devuelto;
    
    public RegistroPrestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaInicio = LocalDate.now();
        this.fechaLimite = fechaInicio.plusDays(15);
        this.devuelto = false;
    }
    
    public long calcularDiasRetraso() {
        if (devuelto) return 0;
        LocalDate hoy = LocalDate.now();
        return hoy.isAfter(fechaLimite) ? ChronoUnit.DAYS.between(fechaLimite, hoy) : 0;
    }
    
    public double calcularMulta() {
        long diasRetraso = calcularDiasRetraso();
        return diasRetraso * 500; // $500 por día de retraso
    }
    
    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public boolean isDevuelto() { return devuelto; }
    public void marcarDevuelto() { this.devuelto = true; }
}

class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<RegistroPrestamo> registroPrestamos;
    private Scanner scanner;
    
    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.registroPrestamos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarDatos();
    }
    
    private void inicializarDatos() {
        // Libros de ejemplo
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "LIB001"));
        libros.add(new Libro("Don Quijote", "Miguel de Cervantes", "LIB002"));
        libros.add(new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", "LIB003"));
        
        // Usuarios de ejemplo
        usuarios.add(new Usuario("Ana García", "USR001"));
        usuarios.add(new Usuario("Carlos López", "USR002"));
    }
    
    public void registrarLibro() {
        System.out.println("\nREGISTRAR NUEVO LIBRO");
        System.out.print("Ingrese el título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine();
        
        // Verificar que el código no exista
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                System.out.println("Error: Ya existe un libro con ese código.");
                return;
            }
        }
        
        libros.add(new Libro(titulo, autor, codigo));
        System.out.println("¡Libro registrado exitosamente!");
    }
    
    public void registrarUsuario() {
        System.out.println("\nREGISTRAR NUEVO USUARIO");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el ID de usuario: ");
        String idUsuario = scanner.nextLine();
        
        // Verificar que el ID no exista
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                System.out.println("Error: Ya existe un usuario con ese ID.");
                return;
            }
        }
        
        usuarios.add(new Usuario(nombre, idUsuario));
        System.out.println("¡Usuario registrado exitosamente!");
    }
    
    public void prestarLibro() {
        System.out.println("\nPRESTAR LIBRO");
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la biblioteca.");
            return;
        }
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en la biblioteca.");
            return;
        }
        
        // Mostrar libros disponibles
        System.out.println("Libros disponibles:");
        boolean hayDisponibles = false;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).isDisponible()) {
                System.out.println((i + 1) + ". " + libros.get(i).getTitulo() + 
                                 " - " + libros.get(i).getCodigo());
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles para préstamo.");
            return;
        }
        
        System.out.print("Ingrese el código del libro: ");
        String codigoLibro = scanner.nextLine();
        
        Libro libroSeleccionado = null;
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigoLibro) && libro.isDisponible()) {
                libroSeleccionado = libro;
                break;
            }
        }
        
        if (libroSeleccionado == null) {
            System.out.println("Libro no encontrado o no disponible.");
            return;
        }
        
        // usuarios
        System.out.println("\nUsuarios registrados:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usr = usuarios.get(i);
            System.out.println((i + 1) + ". " + usr.getNombre() + " - " + usr.getIdUsuario() +
                             " (Libros: " + usr.getLibrosPrestados().size() + "/3)");
        }
        
        System.out.print("Ingrese el ID del usuario: ");
        String idUsuario = scanner.nextLine();
        
        Usuario usuarioSeleccionado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                usuarioSeleccionado = usuario;
                break;
            }
        }
        
        if (usuarioSeleccionado == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        
        if (!usuarioSeleccionado.puedePrestar()) {
            System.out.println("El usuario ya tiene el máximo de libros prestados (3).");
            return;
        }
        
        // Realizar prestamo
        libroSeleccionado.marcarPrestado();
        usuarioSeleccionado.agregarPrestamo(libroSeleccionado);
        RegistroPrestamo registro = new RegistroPrestamo(libroSeleccionado, usuarioSeleccionado);
        registroPrestamos.add(registro);
        
        System.out.println("\n¡Préstamo realizado exitosamente!");
        System.out.println("Fecha de préstamo: " + registro.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Fecha límite de devolución: " + registro.getFechaLimite().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    
    public void devolverLibro() {
        System.out.println("\nDEVOLVER LIBRO");
        
        // Prestamos
        List<RegistroPrestamo> prestamosActivos = new ArrayList<>();
        for (RegistroPrestamo registro : registroPrestamos) {
            if (!registro.isDevuelto()) {
                prestamosActivos.add(registro);
            }
        }
        
        if (prestamosActivos.isEmpty()) {
            System.out.println("No hay libros prestados para devolver.");
            return;
        }
        
        System.out.println("Préstamos activos:");
        for (int i = 0; i < prestamosActivos.size(); i++) {
            RegistroPrestamo reg = prestamosActivos.get(i);
            System.out.println((i + 1) + ". " + reg.getLibro().getTitulo() + 
                             " - Usuario: " + reg.getUsuario().getNombre() +
                             " - Código: " + reg.getLibro().getCodigo());
        }
        
        System.out.print("Ingrese el código del libro a devolver: ");
        String codigoLibro = scanner.nextLine();
        
        RegistroPrestamo prestamoEncontrado = null;
        for (RegistroPrestamo registro : prestamosActivos) {
            if (registro.getLibro().getCodigo().equals(codigoLibro)) {
                prestamoEncontrado = registro;
                break;
            }
        }
        
        if (prestamoEncontrado == null) {
            System.out.println("No se encontró un préstamo activo para ese libro.");
            return;
        }
        
        // Calcular multa
        long diasRetraso = prestamoEncontrado.calcularDiasRetraso();
        double multa = prestamoEncontrado.calcularMulta();
        
        //Marcar Devuelto
        prestamoEncontrado.marcarDevuelto();
        prestamoEncontrado.getLibro().marcarDisponible();
        prestamoEncontrado.getUsuario().devolverLibro(prestamoEncontrado.getLibro());
        
        System.out.println("\n¡Libro devuelto exitosamente!");
        System.out.println("Libro: " + prestamoEncontrado.getLibro().getTitulo());
        System.out.println("Usuario: " + prestamoEncontrado.getUsuario().getNombre());
        
        if (diasRetraso > 0) {
            System.out.println("*** MULTA POR RETRASO ***");
            System.out.println("Días de retraso: " + diasRetraso);
            System.out.println("Multa a pagar: $" + String.format("%.0f", multa));
            System.out.println("************************");
        } else {
            System.out.println("Devolución a tiempo. No hay multas.");
        }
    }
    
    public void mostrarLibrosDisponibles() {
        System.out.println("\nLIBROS DISPONIBLES");
        boolean hayDisponibles = false;
        
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                libro.mostrarDatos();
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles en este momento.");
        }
    }
    
    public void mostrarUsuarios() {
        System.out.println("\nUSUARIOS REGISTRADOS");
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        for (Usuario usuario : usuarios) {
            usuario.mostrarDatos();
        }
    }
    
    public void mostrarHistorialPrestamos() {
        System.out.println("\nHISTORIAL DE PRÉSTAMOS");
        
        if (registroPrestamos.isEmpty()) {
            System.out.println("No hay historial de préstamos.");
            return;
        }
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (RegistroPrestamo registro : registroPrestamos) {
            System.out.println("----------------------------");
            System.out.println("Libro: " + registro.getLibro().getTitulo());
            System.out.println("Usuario: " + registro.getUsuario().getNombre());
            System.out.println("Fecha préstamo: " + registro.getFechaInicio().format(formato));
            System.out.println("Fecha límite: " + registro.getFechaLimite().format(formato));
            System.out.println("Estado: " + (registro.isDevuelto() ? "Devuelto" : "Activo"));
            
            if (!registro.isDevuelto()) {
                long diasRetraso = registro.calcularDiasRetraso();
                if (diasRetraso > 0) {
                    System.out.println("*** RETRASO: " + diasRetraso + " días ***");
                    System.out.println("*** MULTA: $" + String.format("%.0f", registro.calcularMulta()) + " ***");
                }
            }
        }
        System.out.println("----------------------------");
    }
}

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("BIENVENIDO AL SISTEMA DE GESTIÓN DE BIBLIOTECA");
        
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = 0;
            }
            
            if (opcion != 9) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 9);
        
        System.out.println("¡Gracias por usar el Sistema de Gestión de Biblioteca!");
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           MENÚ PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Mostrar libros disponibles");
        System.out.println("6. Mostrar usuarios");
        System.out.println("7. Mostrar historial de préstamos");
        System.out.println("9. Salir");
        System.out.println("=".repeat(50));
    }
    
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                biblioteca.registrarLibro();
                break;
            case 2:
                biblioteca.registrarUsuario();
                break;
            case 3:
                biblioteca.prestarLibro();
                break;
            case 4:
                biblioteca.devolverLibro();
                break;
            case 5:
                biblioteca.mostrarLibrosDisponibles();
                break;
            case 6:
                biblioteca.mostrarUsuarios();
                break;
            case 7:
                biblioteca.mostrarHistorialPrestamos();
                break;
            case 9:
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}