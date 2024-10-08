import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static String[] todos = new String[3];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showTodoList() {
        System.out.println("TODO LIST");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todo != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {
        resizeArrayIfFull();

        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    public static void resizeArrayIfFull() {
        // cek whether todos is full
        Boolean isFull;
        isFull = isArrayFull();

        // if full, resize current array to two times bigger
        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    public static Boolean isArrayFull() {
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                return false;
            }
        }
        return true;
    }

    public static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    public static boolean removeTodoList(Integer number) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < todos.length; i++) {
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                // replace with the element on the right
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    public static boolean isSelectedTodoNotValid(Integer number) {
        // cek if the number is zero or less then zero
        if (number <= 0) {
            return true;
        }

        // check if the number is greater than the todos size/length
        if (number - 1 > todos.length - 1) {
            return true;
        }

        //check whether the element is already null
        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editTodoList(Integer number, String newTodo) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("Menu: ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("pilih");
            switch (selectedMenu) {
                case "1":
                    showMenuTodoList();
                    break;
                case "2":
                    showMenuRemoveTodoList();
                    break;
                case "3":
                    showMenuEditTodoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
            }
        }
    }

    public static void showMenuRemoveTodoList() {
        System.out.println("MENGHAPUS TODO LIST");
        String number = input("Nomor yang ingin dihapus (x jika batal");
        if (number.equals("x")) {
            //batal
        } else {
            boolean success = removeTodoList(Integer.parseInt(number));
            if (!success) {
                System.out.println("Gagal menghapus todo list : " + number);
            }

        }
    }

    public static void showMenuEditTodoList() {
        System.out.println("MENAMBAH TODO LIST");
        String todo = input("Todo (x jika batal");
        if (todo.equals("x")) {
            //batal
        } else {
            addTodoList(todo);
        }
    }

    public static void showMenuTodoList() {
        System.out.println("EDIT TODO LIST");
        String selectedTodo = input("Nomor todo yang yang akan dihapus (x jika batal) ");
        if (selectedTodo.equals("x")) {
            return;
        }
        String newTodo = input("Masukkan todo yang baru (x jika batal)");
        if (selectedTodo.equals("x")) {
            return;
        }
        boolean isEditTodoSuccess = editTodoList(Integer.parseInt(selectedTodo), newTodo);
        if (isEditTodoSuccess) {
            System.out.println("Berhasil mengedit todo");
        } else {
            System.out.println("Gagal mengedit todo");
        }
    }
}
