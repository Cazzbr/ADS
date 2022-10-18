import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Biblioteca_Main {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        final String PATH_TO_DB = "biblioteca.bin";
        Biblioteca minhaBiblioteca = initBiblioteca(PATH_TO_DB);
        boolean executando = true;
        do{
            printMenu();
            String userInput = bf.readLine();
            if (userInput.equals("0")){
                System.out.println("Salvando Banco de Dados!");
                gravarBancoDados(minhaBiblioteca, PATH_TO_DB);
                executando = false;
            }else if (userInput.equals("1") || userInput.equals("2")|| userInput.equals("3") || userInput.equals("4")){
                switch(userInput){
                    case "1": cadastrarLivro(minhaBiblioteca); break;
                    case "2": cadastrarUser(minhaBiblioteca); break;
                    case "3": procurarLivro(minhaBiblioteca); break;
                    case "4": procurarUser(minhaBiblioteca); break;
                }
            }else{
                System.err.println("\nOpção de menu inválida, tente novamente!\n");
            }
        }while(executando);
    }

    private static void cadastrarLivro(Biblioteca b) throws IOException {
        System.out.print("Digite o título do novo livro: ");
        String titulo = bf.readLine();
        b.cadastrar_Livro(new Biblioteca_Obra(titulo));
        System.out.println("\nLivro cadastrado com sucesso!");
    }

    private static void cadastrarUser(Biblioteca b) throws IOException {
        System.out.print("Digite o nome do novo usuário: ");
        String nome = bf.readLine();
        b.cadastrar_Usuario(new Biblioteca_User(nome));
        System.out.println("\nUsuário cadastrado com sucesso!");
    }

    private static void procurarLivro(Biblioteca b) throws IOException {
        System.out.print("Digite o título do livro para pesquisa: ");
        String nome = bf.readLine();
        Biblioteca_Obra bo = b.search_Obra(nome);
        if (bo == null){
            System.out.println("Não foram encontradas obras com esse título na Biblioteca!");
        }else{
            System.out.println(bo);
        }
    }

    private static void procurarUser(Biblioteca b) throws IOException {
        System.out.print("Digite o nome do usuário para pesquisa: ");
        String nome = bf.readLine();
        Biblioteca_User bu = b.search_User(nome);
        if (bu == null){
            System.out.println("Usuário não encontrado no cadastro da Biblioteca!");
        }else{
            System.out.println(bu);
        }
    }

    private static void printMenu() {
        System.out.println ("\n ------    Menu da Bilbioteca   -----\n" + 
                            "     1 - Cadastrar Livro\n" +
                            "     2 - Cadastrar Usuário\n" +
                            "     3 - Procurar Livro\n" +
                            "     4 - Procurar Usuário\n" +
                            "     0 - Sair");
    }

    public static Biblioteca initBiblioteca(String path) throws IOException{
        Biblioteca b = lerBancoDados(path);
        if (b == null){
            System.out.print("Digite o nome para sua Biblioteca: ");
            String nome = bf.readLine();
            b = new Biblioteca(nome);
        }
        return b;
    }

    public static void gravarBancoDados(Biblioteca b, String path){
        try{
            File f = new File(path);
            if (!f.exists()){
                f.createNewFile();
            }
            try(FileOutputStream fos = new FileOutputStream(f, true);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)){
                        oos.writeObject(b);
                        System.out.println("Banco de dados (arquivo.bin) gravado com sucesso!"); 
                    }
        } catch (IOException e){System.err.println("Erro ao gravar o banco de dados (arquivo.bin)");}
    }

    public static Biblioteca lerBancoDados(String path){
        try{
            File f = new File(path);
            if (f.exists()){
                try(FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis)){
                            Biblioteca b = (Biblioteca) ois.readObject();
                            System.out.println("Banco de dados (arquivo.bin) carregado com sucesso!");
                            return b;
                        } catch (ClassNotFoundException ex){
                            System.err.println("Erro ao ler objeto do banco de dados (arquivo.bin)");
                        }
            }
        } catch (IOException e) {System.err.println("Erro ao ler o banco de dados (arquivo.bin)");}
        return null;
    }
}
