import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cursos {
    private static final int CADASTRAR = 1;
    private static final int CADASTRAR_CURSO = 1;
    private static final int CASDASTRAR_DISCIPLINA = 2;
    private static final int CASDASTRAR_PROFESSOR = 3;
    private static final int CASDASTRAR_ALUNO = 4;

    private static final int MOSTRAR = 2;
    private static final int MOSTRAR_CURSOS = 1;
    private static final int MOSTRAR_DISCIPLINAS = 2;
    private static final int MOSTRAR_PROFESSORES = 3;
    private static final int MOSTRAR_ALUNOS = 4;
    
    private static final int ATUALIZAR = 3;
    private static final int ATUALIZAR_CURSO = 1;
    private static final int ATUALIZAR_DISCIPLINAS = 2;
    private static final int ATUALIZAR_PROFESSORES = 3;
    private static final int ATUALIZAR_ALUNOS = 4;

    private static final int REMOVER = 4;
    private static final int REMOVER_CURSO = 1;
    private static final int REMOVER_DISCIPLINAS = 2;
    private static final int REMOVER_PROFESSORES = 3;
    private static final int REMOVER_ALUNOS = 4;

    private static final int SAIR = 0;

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    private static ArrayList<Curso> cursos = new ArrayList<>();
    private static ArrayList<Disciplina> disciplinas = new ArrayList<>();
    private static ArrayList<Professor> professores = new ArrayList<>();
    private static ArrayList<Aluno> alunos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        boolean usando_sistema = true;
        do{
            Menu menu_principal = build_menu_principal();
            Menu retorno = get_menu_input(menu_principal);
            if(retorno.getNumber_acess() == 0){
                usando_sistema = false;
            }else{
                switch (retorno.getNumber_acess()){
                    case CADASTRAR:{case_cadastrar(get_menu_input(retorno)); break;}
                    case MOSTRAR:{case_mostrar(get_menu_input(retorno)); break;}
                    case ATUALIZAR:{case_atualizar(get_menu_input(retorno)); break;}
                    case REMOVER:{case_remover(get_menu_input(retorno)); break;}
                }
            }

        }while(usando_sistema);
    }
    
    public static void case_cadastrar(Menu retorno) throws Exception {
        switch (retorno.getNumber_acess()){
            case CADASTRAR_CURSO:{criar_curso(); break;}
            case CASDASTRAR_DISCIPLINA:{criar_disciplina(); break;}
            case CASDASTRAR_PROFESSOR:{criar_professor(); break;}
            case CASDASTRAR_ALUNO:{criar_aluno(); break;}
        }
    }
    
    private static void case_mostrar(Menu retorno) throws Exception {
        switch (retorno.getNumber_acess()){
            case MOSTRAR_CURSOS:{mostrar_cursos(); break;}
            case MOSTRAR_DISCIPLINAS:{mostrar_disciplinas(); break;}
            case MOSTRAR_PROFESSORES:{mostrar_professores(); break;}
            case MOSTRAR_ALUNOS:{mostrar_alunos(); break;}
        }
    }
    
    private static void case_atualizar(Menu retorno) throws Exception {
        switch (retorno.getNumber_acess()){
            case ATUALIZAR_CURSO:{atualizar_curso(); break;}
            case ATUALIZAR_DISCIPLINAS:{atualizar_disciplina(); break;}
            case ATUALIZAR_PROFESSORES:{atualizar_professor(); break;}
            case ATUALIZAR_ALUNOS:{atualizar_aluno(); break;}
        }
    }
    
    private static void case_remover(Menu retorno) throws Exception {
        switch (retorno.getNumber_acess()){
            case REMOVER_CURSO:{remover_curso(); break;}
            case REMOVER_DISCIPLINAS:{remover_disciplina(); break;}
            case REMOVER_PROFESSORES:{remover_professor(); break;}
            case REMOVER_ALUNOS:{remover_aluno(); break;}
        }
    }
    
    public static void criar_curso() throws Exception{
        System.out.print("Digite o nome do curso: ");
        String nome = bf.readLine();
        if (nome.isEmpty()){
            System.out.println("\nNome inválido! Cadastro abrotado!\n");
        }else{
            Curso c = new Curso(nome);
            cursos.add(c);
            System.out.println("\n Cadastro do curso realizado com sucesso!\n Acesse o menu 'Atualizar' para adicionar mais informações!\n");
        }
    }
    
    private static void criar_disciplina() throws Exception{
        System.out.print("Digite o nome da disciplina: ");
        String nome = bf.readLine();
        System.out.print("Digite a carga horária da disciplina: ");
        String carga = bf.readLine();
        System.out.print("Digite o eixo temático da disciplina, escolha uma opção abaixo: ");
        System.out.println(print_eixo_tematico());
        String eixo = bf.readLine();
        if (nome.isEmpty() || carga.isEmpty() || !is_int(carga)){
            System.out.println("\nNome ou Carga horária inválidos! Cadastro abrotado!\n");
        }else{
            Disciplina d = new Disciplina(nome, Integer.parseInt(carga), EixoTematico.valuesOfLabel(eixo));
            disciplinas.add(d);
            System.out.println("\n Cadastro da disciplina realizado com sucesso!\n Acesse o menu 'Atualizar' para adicionar mais informações!\n");
        }
    }
    
    private static void criar_professor() throws Exception{
        System.out.print("Digite o nome do professor: ");
        String nome = bf.readLine();
        System.out.print("Digite a área de atuação do professor: ");
        System.out.println(print_eixo_tematico());
        String eixo = bf.readLine();
        System.out.print("Digite o e-mail do professor: ");
        String email = bf.readLine();
        System.out.print("Digite o salário do professor: ");
        String salario = bf.readLine();
        if (nome.isEmpty() || !is_float(salario) || email.isEmpty() ){
            System.out.println("\nDados inválidos! Cadastro abrotado!\n");
        }else{
            Professor p = new Professor(nome, EixoTematico.valuesOfLabel(eixo), email, Float.parseFloat(salario));
            professores.add(p);
            System.out.println("\n Cadastro do professor realizado com sucesso!\n");
        }
    }
    
    private static void criar_aluno() throws Exception{
        System.out.print("Digite o nome do aluno: ");
        String nome = bf.readLine();
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = bf.readLine();
        System.out.print("Digite o telefone do aluno: ");
        String telefone = bf.readLine();
        System.out.print("Digite a cidade natal do aluno: ");
        String cidade_natal = bf.readLine();
        if (nome.isEmpty() || !is_int(matricula) || telefone.isEmpty() || cidade_natal.isEmpty()){
            System.out.println("\nDados inválidos! Cadastro abrotado!\n");
        }else{
            Aluno a = new Aluno(nome, Integer.parseInt(matricula), telefone, cidade_natal);
            alunos.add(a);
            System.out.println("\n Cadastro do aluno realizado com sucesso!\n");
        }
    }
    
    public static void mostrar_cursos() throws Exception{
        for (Curso c : cursos) {
            System.out.println(c);
        }
        System.out.print("Prescione enter para voltar ao menu principal:");
        bf.readLine();
    }

    private static void mostrar_disciplinas() throws Exception{
        for (Disciplina d : disciplinas) {
            System.out.println(d);
        }
        System.out.print("Prescione enter para voltar ao menu principal:");
        bf.readLine();
    }

    private static void mostrar_professores() throws Exception {
        for (Professor p: professores) {
            System.out.println(p);
        }
        System.out.print("Prescione enter para voltar ao menu principal:");
        bf.readLine();
    }
    
    private static void mostrar_alunos() throws Exception {
        for (Aluno a : alunos) {
            System.out.println(a);
        }
        System.out.print("Prescione enter para voltar ao menu principal:");
        bf.readLine();
    }
    
    private static void atualizar_curso() {
    }

    private static void atualizar_disciplina() {
    }

    private static void atualizar_professor() {
    }

    private static void atualizar_aluno() throws Exception {
        System.out.println(" *** Qual Aluno vamos atualizar? ***");
        for (int i =0; i < alunos.size(); i++) {
            System.out.print("" + i + ")" + alunos.get(i));
        }
        System.out.print(" Digite o número correspondente: ");
        String user = bf.readLine();
        if (user.isEmpty() || !is_int(user)){
            System.out.println("\n Aluno  inválido! Atualização abrotada!\n");
        }else{

            String [] input = aluno_input();

        }
    }
    
    private static void remover_curso() {
    }
    
    private static void remover_disciplina() {
    }

    private static void remover_professor() {
    }

    private static void remover_aluno() {
    }

    public static String[] aluno_input() throws Exception {
        String [] return_values = new String[4];

        System.out.print("Digite o nome do aluno: ");
        String nome = bf.readLine();
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = bf.readLine();
        System.out.print("Digite o telefone do aluno: ");
        String telefone = bf.readLine();
        System.out.print("Digite a cidade natal do aluno: ");
        String cidade_natal = bf.readLine();
        if (nome.isEmpty() || !is_int(matricula) || telefone.isEmpty() || cidade_natal.isEmpty()){
            System.out.println("\nDados inválidos! Cadastro abrotado!\n");
            return null;
        }else{
            return_values[0] = nome;
            return_values[1] = matricula;
            return_values[2] = telefone;
            return_values[3] = cidade_natal;
            return return_values;
        }

    }

    public static Menu get_menu_input(Menu m) throws Exception{ 
        do{
            System.out.println(m);
            String user_input = bf.readLine();
            if (m.getSubmenu() != null){
                for (Menu menu: m.getSubmenu()) {
                    if ((menu.getNumber_acess() + "").equals(user_input)){
                        return menu;
                    }
                }
                System.out.println();
                System.out.println("Erro: (" + user_input + ") Menu inválido! Tente novamente!");
            }
        }while(true);
    }

    public static boolean is_float(String txt){
        try{
            Float.parseFloat(txt);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean is_int(String txt){
        try{
            Integer.parseInt(txt);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static String print_eixo_tematico(){
        String str = "";
        str += " *** Eixos Temáticos ***\n";
        str += "   " + EixoTematico.EXATAS.getClassificacao() + " - " + EixoTematico.EXATAS + "\n";
        str += "   " + EixoTematico.BIOLOGICAS.getClassificacao() + " - " + EixoTematico.BIOLOGICAS + "\n";
        str += "   " + EixoTematico.SAUDE.getClassificacao() + " - " + EixoTematico.SAUDE + "\n";
        str += "   " + EixoTematico.AGRARIAS.getClassificacao() + " - " + EixoTematico.AGRARIAS + "\n";
        str += "   " + EixoTematico.HUMANAS.getClassificacao() + " - " + EixoTematico.HUMANAS + "\n";
        str += "   " + EixoTematico.SOCIAIS.getClassificacao() + " - " + EixoTematico.SOCIAIS + "\n";
        str += "   " + EixoTematico.ENGENHARIAS.getClassificacao() + " - " + EixoTematico.ENGENHARIAS + "\n";
        str += "   " + EixoTematico.LINGUISTICA.getClassificacao() + " - " + EixoTematico.LINGUISTICA + "\n";
        return str;
    }

    public static Menu build_menu_principal(){
        Menu inner_menu_principal[] = {build_menu_cadastrar()
                                    , build_menu_mostrar()
                                    , build_menu_atualizar()
                                    , build_menu_remover()
                                    , new Menu("SAIR", SAIR)
                                    };
        return new Menu("Menu Principal - Cursos", 0, inner_menu_principal);
    }

    
    public static Menu build_menu_cadastrar(){
        Menu inner_menu_cadastrar[] = {new Menu("Cadastrar novo Curso", CADASTRAR_CURSO, () -> {try{criar_curso();}catch(Exception e){}})
                                    , new Menu("Cadastrar nova Disciplina", CASDASTRAR_DISCIPLINA)
                                    , new Menu("Cadastrar novo Professor", CASDASTRAR_PROFESSOR)
                                    , new Menu("Cadastrar novo Aluno", CASDASTRAR_ALUNO)
                                    , new Menu("SAIR", SAIR)
                                    };
        return new Menu("Cadastrar", CADASTRAR, inner_menu_cadastrar);
    }

    private static Menu build_menu_mostrar() {
        Menu inner_menu_mostrar[] = {new Menu("Mostar Cursos cadastrados", MOSTRAR_CURSOS)
                                    , new Menu("Mostrar Disciplinas cadastrados", MOSTRAR_DISCIPLINAS)
                                    , new Menu("Mostrar Professores cadastrados", MOSTRAR_PROFESSORES)
                                    , new Menu("Mostrar ALunos cadastrados", MOSTRAR_ALUNOS)
                                    , new Menu("SAIR", SAIR)
                                    };
        return new Menu("Mostrar", MOSTRAR, inner_menu_mostrar);
    }

    public static Menu build_menu_atualizar(){
        Menu inner_menu_atualizar[] = {new Menu("Atualizar Curso existente", ATUALIZAR_CURSO)
                                    , new Menu("Atualizar Disciplina existente", ATUALIZAR_DISCIPLINAS)
                                    , new Menu("Atualizar Professor existente", ATUALIZAR_PROFESSORES)
                                    , new Menu("Atualizar Aluno existente", ATUALIZAR_ALUNOS)
                                    , new Menu("SAIR", SAIR)
                                    };
        return new Menu("Atualizar", ATUALIZAR, inner_menu_atualizar);
    }

    public static Menu build_menu_remover(){
        Menu inner_menu_remover[] = {new Menu("Remover Curso existente", REMOVER_CURSO)
                                    , new Menu("Remover Disciplina existente", REMOVER_DISCIPLINAS)
                                    , new Menu("Remover Professor existente", REMOVER_PROFESSORES)
                                    , new Menu("Remover Alunos existente", REMOVER_ALUNOS)
                                    , new Menu("SAIR", SAIR)
                                    };
        return new Menu("Remover", REMOVER, inner_menu_remover);
    }
}