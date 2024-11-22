# parcial2-programacion- laura - jordi 

Sistema de Biblioteca

1. Una **clase `Libro`** con atributos como título, autor, ISBN, estado (disponible o prestado).
    - Métodos:
        - `prestar()`: Cambia el estado del libro a prestado.
        - `devolver()`: Cambia el estado del libro a disponible.
2. Una **clase `Usuario`** con atributos como nombre, ID y libros prestados.
    - Métodos:
        - `prestarLibro(Libro libro)`: Permite al usuario tomar un libro prestado.
        - `devolverLibro(Libro libro)`: Permite devolver un libro.
3. Una **clase `GestionBiblioteca`** que permita:
    - Registrar libros y usuarios.
    - Verificar la disponibilidad de libros.
    - Mostrar el historial de préstamos.   

