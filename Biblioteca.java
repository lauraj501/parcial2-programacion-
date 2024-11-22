import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Libro {
    String titulo;
    String autor;
    String isbn;
    boolean prestado; // true si está prestado, false si está disponible

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prestado = false; // Inicialmente, el libro está disponible
    }

    public void prestar() {
        prestado = true;
    }

    public void devolver() {
        prestado = false;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Estado: " + (prestado ? "Prestado" : "Disponible");
    }
}

class Usuario {
    String nombre;
    String id;
    List<Libro> librosPrestados;

    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

    public boolean prestarLibro(Libro libro) {
        if (librosPrestados.size() < 3 && !libro.estaPrestado()) { // Límite de 3 libros
            librosPrestados.add(libro);
            libro.prestar();
            return true;
        }
        return false;
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
        libro.devolver();
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (ID: " + id + ")";
    }
}

class GestionBiblioteca {
    List<Libro> libros;
    List<Usuario> usuarios;

    public GestionBiblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void registrarLibro(String titulo, String autor, String isbn) {
        libros.add(new Libro(titulo, autor, isbn));
    }

    public void registrarUsuario(String nombre, String id) {
        usuarios.add(new Usuario(nombre, id));
    }

    public Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.titulo.equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.id.equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public void mostrarLibrosDisponibles() {
        System.out.println("Libros disponibles:");
        for (Libro libro : libros) {
            if (!libro.estaPrestado()) {
                System.out.println(libro);
            }
        }
    }
}

public class Biblioteca {
    public static void main(String[] args) {
        GestionBiblioteca gestion = new GestionBiblioteca();
        Scanner scanner = new Scanner(System.in);

        gestion.registrarLibro("sempiterno", "Johana Marcus", "1617-01");
        gestion.registrarLibro("Asfixia", "Alex Mirez", "1617-02");
        gestion.registrarLibro("alaska", "Alice Kellen", "1617-03");

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Prestar libro");
            System.out.println("2. Devolver libro");
            System.out.println("3. Mostrar libros disponibles");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese su ID: ");
                    String id = scanner.nextLine();
                    Usuario usuario = gestion.buscarUsuario(id);
                    if (usuario == null) {
                        usuario = new Usuario(nombre, id);
                        gestion.registrarUsuario(nombre, id);
                    }
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    Libro libro = gestion.buscarLibro(titulo);
                    if (libro != null && usuario.prestarLibro(libro)) {
                        System.out.println("Libro prestado exitosamente.");
                    } else {
                        System.out.println("No se pudo prestar el libro. Puede que ya esté prestado o ha alcanzado el límite.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese su ID: ");
                    String idDevolucion = scanner.nextLine();
                    Usuario usuarioDevolucion = gestion.buscarUsuario(idDevolucion);
                    if (usuarioDevolucion != null) {
                        System.out.print("Ingrese el título del libro: ");
                        String tituloDevolucion = scanner.nextLine();
                        Libro libroDevolucion = gestion.buscarLibro(tituloDevolucion);
                        if (libroDevolucion != null && libroDevolucion.estaPrestado()) {
                            usuarioDevolucion.devolverLibro(libroDevolucion);
                            System.out.println("Libro devuelto exitosamente.");
                        } else {
                            System.out.println("Libro no encontrado o no está prestado.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 3:
                    gestion.mostrarLibrosDisponibles();
                    break;
                case 4:
                    System.out.println("Salio del programa");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }
}
