public class Menu {
    private String nome;
    private int number_acess;
    private Runnable run;
    private Menu submenu[];

    public Menu(String nome, int number_acess){
        this.nome = nome;
        this.number_acess = number_acess;
    }
    public Menu(String nome, int number_acess, Runnable run){
        this(nome, number_acess);
        this.run = run;
    }
    public Menu(String nome, int number_acess, Menu[] submenu){
        this(nome, number_acess);
        this.submenu = submenu;
    }
    public Menu(String nome, int number_acess, Runnable run, Menu[] submenu){
        this(nome, number_acess, run);
        this.submenu = submenu;
    }
    
    public Runnable getRun(){
        return this.run;
    }

    public Menu[] getSubmenu() {
        return submenu;
    }
    public void setSubmenu(Menu[] submenu) {
        this.submenu = submenu;
    }

    public int getNumber_acess() {
        return number_acess;
    }
    public void setNumber_acess(int number_acess) {
        this.number_acess = number_acess;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        String str = "";
        str += "\n *** Menu: " + this.nome + " ***\n";
        if (this.submenu != null){
            for (int i = 0; i < this.submenu.length; i++){
                str += "   " + this.submenu[i].number_acess + " - " + this.submenu[i].nome + "\n";
            }
        }else{
            str += " Este menu não tem submenus\n";
        }
        return str;
    }
}
